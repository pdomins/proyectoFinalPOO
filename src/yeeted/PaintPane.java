package yeeted;

import frontend.CanvasState;
import backend.model.*;
import frontend.Drawable.Drawable;
import frontend.StatusPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
	regularButtons deletionButton = new deletionButton();
	regularButtons toFrontButton = new toFrontButton();
	regularButtons toBackButton = new toBackButton();
	selectionButton selectionButton= new selectionButton();
	figuresToggleButtons rectangleButton = new rectangleButton();
	figuresToggleButtons squareButton = new squareButton();
	figuresToggleButtons lineButton = new lineButton();
	figuresToggleButtons circleButton = new circleButton();
	figuresToggleButtons ellipseButton = new ellipseButton();

	// Dibujar una figura
	Point startPoint;

	// StatusBar
	StatusPane statusPane;

	// Seleccionar una o varias figuras
	List<Drawable> selectedFigures = new LinkedList<>();

	//toggleGroup
	ToggleGroup myTools = new ToggleGroup(); //NUEVO
	ToggleButton[] toolsArr = {selectionButton,rectangleButton,squareButton,lineButton,circleButton,ellipseButton};


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		for (ToggleButton tool : toolsArr) {
			tool.setToggleGroup(myTools);
		}

		ColorPicker fillingPicker = new ColorPicker(fillColor);
		fillingPicker.setOnAction(e -> {
			Color c = fillingPicker.getValue();
			fillColor = c;
			redrawCanvas();
		});


		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);//NUEVO
		buttonsBox.getChildren().add(deletionButton);//NUEVO
		buttonsBox.getChildren().add(toBackButton);//NUEVO
		buttonsBox.getChildren().add(toFrontButton);//NUEVO
		buttonsBox.getChildren().add(fillingPicker);//COLOR PICKER
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);


		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		canvas.setOnMouseReleased(event -> {
			selectedFigures.clear();
			Point endPoint = new Point(event.getX(), event.getY());
			Toggle activeButton = myTools.getSelectedToggle();
			if (activeButton == (selectionButton)){ //criterio seleccion multiple
				selectedFigures.addAll(selectionButton.selectMultipleFigures(startPoint,endPoint,canvasState));
			}else if(activeButton instanceof figuresToggleButtons){
				figuresToggleButtons auxiliarButton = (figuresToggleButtons) activeButton;
				Drawable newFigure = auxiliarButton.newFigure(startPoint, endPoint, fillColor, lineColor);
				if (newFigure != null) canvasState.addFigure(newFigure);
				startPoint = null;
				redrawCanvas();
			}
		});

		//aca son las etiquetas que aparecen abajo
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for (Drawable figure: canvasState.figures()){
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
			if( myTools.getSelectedToggle() == selectionButton) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Drawable figure : canvasState.figures()) {
					if(figure.containsPoint(eventPoint)) {
						found = true;
						selectedFigures.add(figure);
						label.append(figure.toString());
						break;
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
			if(myTools.getSelectedToggle() == selectionButton) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				for (Drawable figure : selectedFigures) {
					figure.move(diffX, diffY);
				}
				redrawCanvas();
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Drawable figure : canvasState.figures()) {
			if(selectedFigures.contains(figure)) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(figure.getStrokeColor());
			}
			gc.setFill(figure.getFillColor());
			figure.draw(gc);
		}
	}

}
