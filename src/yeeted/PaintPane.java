package yeeted;

import backend.CanvasState;
import backend.model.*;
import frontend.StatusPane;
import javafx.scene.control.ToggleButton;
import trash.buttons.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class PaintPane extends BorderPane {

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;

	// Botones Barra Izquierda
	toggleGroup myTools = new toggleGroup(); //NUEVO
	regularButtons deletionButton = new deletionButton();
	regularButtons toFrontButton = new toFrontButton();
	regularButtons toBackButton = new toBackButton();

	// Dibujar una figura
	Point startPoint;

	// StatusBar
	StatusPane statusPane;

	// Seleccionar una o varias figuras
	List<DrawableFigure> selectedFigures = new LinkedList<>();


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		myTools.toggleGroup(); //NUEVO
		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(myTools.getToolsArr());//NUEVO
		buttonsBox.getChildren().add(deletionButton);//NUEVO
		buttonsBox.getChildren().add(toBackButton);//NUEVO
		buttonsBox.getChildren().add(toFrontButton);//NUEVO
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);


		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		canvas.setOnMouseReleased(event -> {
			selectedFigures.clear();
			Point endPoint = new Point(event.getX(), event.getY());
			selectedFigures.clear();
			ToggleButton activeButton = myTools.isOn();
			if (activeButton.equals(new selectionButton())){ //criterio seleccion multiple
				selectionButton selection = new selectionButton();
				selectedFigures.addAll(selection.selectMultipleFigures(startPoint,endPoint,canvasState));
			}else if(activeButton instanceof figuresToggleButtons){
				figuresToggleButtons auxiliarButton = (figuresToggleButtons) activeButton;
				DrawableFigure newFigure = auxiliarButton.newFigure(startPoint, endPoint);
				canvasState.addFigure(newFigure);
				startPoint = null;
				redrawCanvas();
			}
		});

		//aca son las etiquetas que aparecen abajo
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figure.containsPoint(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(myTools.isOn().equals(new selectionButton())) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (DrawableFigure figure : canvasState.figures()) {
					if(figure.containsPoint(eventPoint)) {
						found = true;
						selectedFigures.add(figure);
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
			deletionButton.setOnAction(event1 -> {
				deletionButton.action(selectedFigures,canvasState);
				redrawCanvas();
			} );
			toBackButton.setOnAction(event1 -> {
				toBackButton.action(selectedFigures,canvasState);
				redrawCanvas();
			});
			toFrontButton.setOnAction(event1 -> {
				toFrontButton.action(selectedFigures,canvasState);
				redrawCanvas();
			});
		});

		canvas.setOnMouseDragged(event -> {
			if(myTools.isOn().equals(new selectionButton())) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(!selectedFigures.isEmpty()) {
					for (DrawableFigure figure : selectedFigures) {
						figure.move(diffX, diffY);
					}
				}
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(DrawableFigure figure : canvasState.figures()) {
			if(selectedFigures.contains(figure)) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(lineColor);
			}
			gc.setFill(fillColor);
			figure.draw(gc);
		}
	}

}
