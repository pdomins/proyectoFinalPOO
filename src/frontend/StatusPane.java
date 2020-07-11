package frontend;

import frontend.Drawable.Drawable;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.util.List;
import java.util.Optional;

public class StatusPane extends BorderPane {

	private final Label statusLabel;

	public StatusPane() {
		setStyle("-fx-background-color: #FBCAFF");
		statusLabel = new Label("Paint 1.0");
		statusLabel.setAlignment(Pos.CENTER);
		statusLabel.setStyle("-fx-font-size: 16");
		setCenter(statusLabel);
		this.setCursor(new ImageCursor(new Image("file:cursores/statusLabelCursor.png")));

	}
	
	public void updateStatus(String text) {
		statusLabel.setText(text);
	}

	public void updateOrDefault(String label, String def) {
		if (label.isEmpty()) updateStatus(def);
		else updateStatus(label);
	}

	public void showStatus(List<Drawable> figure){
		if (figure.isEmpty()){
			this.updateStatus("Ninguna figura encontrada");
		}else{
		StringBuilder label = new StringBuilder("Se seleccionó: ");
		for (Drawable lastFigure:figure) {
			label.append(lastFigure.toString());
			this.updateStatus(label.toString());
		}
		}
	}

	public void showStatusNewFigure(Drawable figure){
		if (Optional.ofNullable(figure).isPresent()){
			this.updateStatus("Se insertó figura: " + figure);
		}else{
			this.updateStatus("Coordenadas Incorrectas: no se ha podido crear la figura");
		}
	}

}