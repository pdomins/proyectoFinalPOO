package trash.buttons;

import frontend.CanvasState;
import frontend.Drawable.Drawable;

import java.util.List;

public class toBackButton extends regularButtons{

    public toBackButton() {
        super("Al Fondo");
    }

    @Override
    public void action(List<Drawable> selectedFigures, CanvasState canvasState) {
        for (Drawable figure : selectedFigures) {
            canvasState.setToBottom(figure);
        }
    }
}
