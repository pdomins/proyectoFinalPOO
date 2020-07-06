package trash.buttons;

import backend.CanvasState;
import backend.model.DrawableFigure;

import java.util.List;

public class toBackButton extends regularButtons{

    public toBackButton() {
        super("Al Fondo");
    }


    @Override
    public void action(List<DrawableFigure> selectedFigures, CanvasState canvasState) {
        for (DrawableFigure figure : selectedFigures) {
            canvasState.setToBottom(figure);
        }
    }
}
