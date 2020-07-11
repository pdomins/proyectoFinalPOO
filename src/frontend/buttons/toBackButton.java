package frontend.buttons;

import frontend.CanvasState;
import frontend.Drawable.Drawable;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class toBackButton extends regularButtons{

    public toBackButton() {
        super("Al Fondo");
        this.setCursor(new ImageCursor(new Image("file:cursores/toBackCursor.png")));

    }

    @Override
    public void action(List<Drawable> selectedFigures, CanvasState canvasState) {
        List<Drawable> selectedCopy = new ArrayList<>(selectedFigures); //Copia para invertir y preservar el orden
        Collections.reverse(selectedCopy);
        for (Drawable figure : selectedCopy) {
            canvasState.setToBottom(figure);
        }
    }
}
