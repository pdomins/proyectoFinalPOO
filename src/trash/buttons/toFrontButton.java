package trash.buttons;

import frontend.CanvasState;
import backend.model.DrawableFigure;
import frontend.Drawable.Drawable;

import java.util.List;

public class toFrontButton extends regularButtons{

    public toFrontButton() {
        super("Al Frente");
    }

    @Override
    public void action(List<Drawable> selectedFigures, CanvasState canvasState) {
        for (Drawable figure : selectedFigures) {
            canvasState.setToTop(figure);
        }
    }
}
