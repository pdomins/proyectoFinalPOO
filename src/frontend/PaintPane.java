package frontend;

import backend.model.*;
import frontend.Drawable.Drawable;
import frontend.buttons.*;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaintPane extends BorderPane {

	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;
	Color fillColor = Color.YELLOW;
	double strokeWidth = 1;

	Point previousMouse;

	// Botones Barra Izquierda
	regularButtons deletionButton = new deletionButton();
	regularButtons toFrontButton = new toFrontButton();
	regularButtons toBackButton = new toBackButton();
	frontend.buttons.selectionButton selectionButton= new selectionButton();

	// Dibujar una figura
	Point startPoint;

	// StatusBar
	StatusPane statusPane;

	// Seleccionar una o varias figuras
	List<Drawable> selectedFigures = new ArrayList<>();

	//toggleGroup
	ToggleGroup toggleGroup = new ToggleGroup();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {

		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ColorPicker fillingPicker = new ColorPicker(fillColor);
		ColorPicker strokePicker = new ColorPicker(lineColor);
		Slider strokeSlider = new Slider(1, 50, strokeWidth);
		List<ToggleButton> auxList = new ArrayList<>();

		selectionButton.setToggleGroup(toggleGroup);
		auxList.add(selectionButton);

		for (figuresTogglesEnum figuresTog: figuresTogglesEnum.values()) {
			ToggleButton aux = new ToggleButton(figuresTog.getName());
			auxList.add(aux);
			aux.setToggleGroup(toggleGroup);
			aux.setMinWidth(90);
			aux.setCursor(Cursor.HAND);
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


		strokeSlider.setShowTickLabels(true);
		strokeSlider.setOnMouseDragged(event -> {
			double value = strokeSlider.getValue();
			strokeWidth = value;
			for (Drawable figure : selectedFigures) {
				figure.setStrokeWidth(value);
			}
			redrawCanvas();
		});
		strokePicker.setOnAction(event -> {
			Color c = strokePicker.getValue();
			lineColor = c;
			for (Drawable figure : selectedFigures) {
				figure.setStrokeColor(c);
			}
			redrawCanvas();
		});
		fillingPicker.setOnAction(e -> {
			Color c = fillingPicker.getValue();
			fillColor = c;
			for (Drawable figure : selectedFigures) {
				figure.setFillColor(c);
			}
			redrawCanvas();
		});


		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(auxList);
		buttonsBox.getChildren().add(deletionButton);
		buttonsBox.getChildren().add(toBackButton);
		buttonsBox.getChildren().add(toFrontButton);
		buttonsBox.getChildren().add(new Label("Linea"));
		buttonsBox.getChildren().add(strokeSlider);
		buttonsBox.getChildren().add(strokePicker);
		buttonsBox.getChildren().add(new Label("Relleno"));
		buttonsBox.getChildren().add(fillingPicker);//COLOR PICKER
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(strokeWidth);


		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));
		canvas.setOnMouseReleased(event -> {
			selectedFigures.clear();
			Point endPoint = new Point(event.getX(), event.getY());
			ToggleButton activeButton = (ToggleButton) toggleGroup.getSelectedToggle();
			if (activeButton == selectionButton){ //criterio seleccion multiple
				selectedFigures.addAll(selectionButton.selectMultipleFigures(startPoint,endPoint,canvasState));
				statusPane.showStatus(selectedFigures);
			}else{
				if (Optional.ofNullable(activeButton).isPresent()) {
					Drawable newFigure = figuresTogglesEnum.valueOf(activeButton.getText())
							.newFigure(startPoint, endPoint, fillColor, lineColor, strokeWidth);
					if (Optional.ofNullable(newFigure).isPresent()) canvasState.addFigure(newFigure);
					startPoint = null;
					redrawCanvas();
				}
			}
		});


		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			StringBuilder label = new StringBuilder();
			for (Drawable figure: canvasState.figures()){
				if(figure.containsPoint(eventPoint)) {
					label.append(figure.toString());
				}
			}
			statusPane.updateOrDefault(label.toString(), eventPoint.toString());
			previousMouse = eventPoint;
		});

		canvas.setOnMouseClicked(event -> {
			if( toggleGroup.getSelectedToggle() == selectionButton) {
				Point eventPoint = new Point(event.getX(), event.getY());
				Drawable lastFigure = null;
				for (Drawable figure : canvasState.figures()) {
					if(figure.containsPoint(eventPoint)) {
						lastFigure=figure;
					}
				}
				if (Optional.ofNullable(lastFigure).isPresent()) {
					selectedFigures.add(lastFigure);
					statusPane.showStatus(selectedFigures);
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(toggleGroup.getSelectedToggle() == selectionButton) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - previousMouse.getX());
				double diffY = (eventPoint.getY() - previousMouse.getY());
				for (Drawable figure : selectedFigures) {
					figure.move(diffX, diffY);
				}
				redrawCanvas();
				previousMouse = eventPoint;
			}
		});
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Drawable figure : canvasState.figures()) {
			gc.setLineWidth(figure.getStrokeWidth());
			if (selectedFigures.contains(figure)) {
				gc.setStroke(Color.RED);
			} else {
				gc.setStroke(figure.getStrokeColor());
			}
			gc.setFill(figure.getFillColor());
			figure.draw(gc);
		}
		gc.setLineWidth(strokeWidth);
		gc.setStroke(lineColor);
		gc.setFill(fillColor);
	}
}

