package frontend.buttons;

import frontend.CanvasState;
import frontend.Drawable.Drawable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

import java.util.List;

public abstract class regularButtons extends Button {

    public regularButtons(String text) {
        super(text);
        this.setMinWidth(90);
        this.setCursor(Cursor.HAND);
    }

    public abstract void action(List<Drawable> selectedFigures, CanvasState canvasState);
}
