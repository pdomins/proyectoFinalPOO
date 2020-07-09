package frontend.buttons;

import frontend.CanvasState;
import frontend.Drawable.Drawable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.List;

public class toFrontButton extends regularButtons{

    public toFrontButton() {
        super("Al Frente");
        this.setCursor(new ImageCursor(new Image("file:cursores/treecko.png")));

    }

    @Override
    public void action(List<Drawable> selectedFigures, CanvasState canvasState) {
        for (Drawable figure : selectedFigures) {
            canvasState.setToTop(figure);
        }
    }
}
