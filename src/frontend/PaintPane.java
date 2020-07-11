package frontend;

import backend.model.*;
import frontend.Drawable.Drawable;
import frontend.buttons.*;
import javafx.scene.ImageCursor;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaintPane extends BorderPane {

	private CanvasState canvasState;

	// Canvas y relacionados
	private Canvas canvas = new Canvas(800, 600);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Color lineColor = Color.CORNFLOWERBLUE;
	private Color fillColor = Color.MISTYROSE;
	private double strokeWidth = 1;

	private Point previousMouse;

	// Botones Barra Izquierda
	private regularButtons deletionButton = new deletionButton();
	private regularButtons toFrontButton = new toFrontButton();
	private regularButtons toBackButton = new toBackButton();
	private selectionButton selectionButton= new selectionButton();

	// Punto Inicial para dibujar una figura
	private Point startPoint;

	// Lista para la seleccion de figuras
	private List<Drawable> selectedFigures = new ArrayList<>();

	//ToggleGroup de botones
	private ToggleGroup toggleGroup = new ToggleGroup();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {

		this.canvasState = canvasState;

		ColorPicker fillingPicker = new ColorPicker(fillColor); //color picker para el relleno
		ColorPicker strokePicker = new ColorPicker(lineColor); //color picker para el contorno
		Slider strokeSlider = new Slider(1, 50, strokeWidth); //slider para grosor de contorno


		//Seteamos los botones tipo Toggle
		List<ToggleButton> auxList = new ArrayList<>();
		selectionButton.setToggleGroup(toggleGroup);
		auxList.add(selectionButton);

		for (figuresTogglesEnum figuresTog: figuresTogglesEnum.values()) {
			ToggleButton aux = new ToggleButton(figuresTog.getName());
			auxList.add(aux);
			aux.setToggleGroup(toggleGroup);
			aux.setMinWidth(90);
			aux.setCursor(figuresTog.getCursorImage());
		}

		//Seteamos los demas botones y sus acciones
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


		//Seteamos los ColorPicker y el Slider
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


		//Creamos el layout del panel izquierdo y agregamos todos los elementos
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
		buttonsBox.setStyle("-fx-background-color: #FFABF2");
		buttonsBox.setPrefWidth(100);

		//Comportamiento del canvas al presionar el mouse
		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

		//Comportamiento del canvas al soltar el mouse
		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			ToggleButton activeButton = (ToggleButton) toggleGroup.getSelectedToggle(); //Boton seleccionado
			if (activeButton == selectionButton) {
				List<Drawable> found = selectionButton.selectMultipleFigures(startPoint,endPoint,canvasState); //Lista de figuras encontradas en el area del drag
				if (!found.isEmpty() && selectedFigures.isEmpty()) selectedFigures.addAll(found); //Si encontre figuras pero tengo otras seleccionadas, no las selecciono
				else selectedFigures.clear(); // Si no encontre nada, vacio selectedFigures (Click o drag sobre zona vacia)
				statusPane.showStatus(selectedFigures);
			}else if (Optional.ofNullable(activeButton).isPresent()) { //Si es un boton de Figuras
					figuresTogglesEnum auxButton = figuresTogglesEnum.matchAndGetButtonName(activeButton.getText()); //Obtenemos el boton
						if (Optional.ofNullable(auxButton).isPresent()) {
							Drawable newFigure = auxButton.newFigure(startPoint, endPoint, fillColor, lineColor, strokeWidth); //Creamos un nueva figura
							if (Optional.ofNullable(newFigure).isPresent()) canvasState.addFigure(newFigure); //agregamos la figura
							statusPane.showStatusNewFigure(newFigure);
						}
						startPoint = null;
						redrawCanvas();
			}
		});

		//Comportaiento del canvas al mover el mouse
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			StringBuilder label = new StringBuilder();
			for (Drawable figure: canvasState.figures()){
				if(figure.containsPoint(eventPoint)) {
					label.append(figure.toString());
				}
			}
			statusPane.updateOrDefault(label.toString(), eventPoint.toString()); //Actualizamos el statusPane con la posicion del mouse
			previousMouse = eventPoint;
		});

		//Comportamiento del canvas al hacer click
		canvas.setOnMouseClicked(event -> {
			//Si el boton de seleccion esta activado, recorre todas las figuras para ver cual es la ultima que se encuentra en ese punto
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

		//Comportamiento del canvas al hacer un drag
		canvas.setOnMouseDragged(event -> {
			//Si el boton de seleccion esta activado, mueve las figuras seleccionadas
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

		//Imagen del cursor
		ImageCursor cursorImage = new ImageCursor(new Image("file:cursores/canvasCursor.png"));
		canvas.setCursor(cursorImage);
		buttonsBox.setCursor(cursorImage);

		setLeft(buttonsBox);
		setRight(canvas);
	}

	//Dibujado del canvas
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

