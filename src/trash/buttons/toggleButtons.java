package trash.buttons;

import javafx.scene.Cursor;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

public abstract class toggleButtons extends ToggleButton implements Toggle {

    public toggleButtons(String text) {
        super(text);
        this.setMinWidth(90);
        this.setCursor(Cursor.HAND);
    }
}
