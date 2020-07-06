package trash.buttons;


import backend.CanvasState;
import backend.model.DrawableFigure;
import javafx.scene.control.Button;

import java.util.List;

public class deletionButton extends regularButtons {

    public deletionButton() {
        super("Eliminar");
    }
    public void action(List<DrawableFigure> selectedFigures, CanvasState canvasState){
        for (DrawableFigure figure : selectedFigures) {
            canvasState.removeFigure(figure);
        }
        selectedFigures.clear();
    }

}
