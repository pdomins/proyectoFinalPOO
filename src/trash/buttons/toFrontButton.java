package trash.buttons;

import frontend.CanvasState;
import backend.model.DrawableFigure;

import java.util.List;

public class toFrontButton extends regularButtons{

    public toFrontButton() {
        super("Al Frente");
    }

    @Override
    public void action(List<DrawableFigure> selectedFigures, CanvasState canvasState) {
        for (DrawableFigure figure : selectedFigures) {
            canvasState.setToTop(figure);
        }
    }
}
