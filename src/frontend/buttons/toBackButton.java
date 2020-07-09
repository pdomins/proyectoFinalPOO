package frontend.buttons;

import frontend.CanvasState;
import frontend.Drawable.Drawable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.List;

public class toBackButton extends regularButtons{

    public toBackButton() {
        super("Al Fondo");
        this.setCursor(new ImageCursor(new Image("file:cursores/mudkip.png")));

    }

    @Override
    public void action(List<Drawable> selectedFigures, CanvasState canvasState) {
        for (Drawable figure : selectedFigures) {
            canvasState.setToBottom(figure);
        }
    }
}
