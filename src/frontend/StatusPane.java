package frontend;

import frontend.Drawable.Drawable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class StatusPane extends BorderPane {

	private final Label statusLabel;

	public StatusPane() {
		setStyle("-fx-background-color: #4EBCF8");
		statusLabel = new Label("Paint 1.0");
		statusLabel.setAlignment(Pos.CENTER);
		statusLabel.setStyle("-fx-font-size: 16");
		setCenter(statusLabel);
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
		if (figure==null){
			this.updateStatus("Coordenadas Incorrectas: no se ha podido crear la figura");
		}else{
			this.updateStatus("Se insertó figura: " + figure);
		}
	}

}