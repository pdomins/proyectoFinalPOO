package trash.buttons;


import frontend.CanvasState;
import frontend.Drawable.Drawable;

import java.util.List;

public class deletionButton extends regularButtons {

    public deletionButton() {
        super("Eliminar");
    }
    public void action(List<Drawable> selectedFigures, CanvasState canvasState){
        for (Drawable figure : selectedFigures) {
            canvasState.removeFigure(figure);
        }
        selectedFigures.clear();
    }

}
