package frontend.buttons;


import frontend.CanvasState;
import frontend.Drawable.Drawable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.util.List;

public class deletionButton extends regularButtons {

    public deletionButton() {
        super("Eliminar");
        this.setCursor(new ImageCursor(new Image("file:cursores/torchic.png")));

    }
    public void action(List<Drawable> selectedFigures, CanvasState canvasState){
        for (Drawable figure : selectedFigures) {
            canvasState.removeFigure(figure);
        }
        selectedFigures.clear();
    }

}
