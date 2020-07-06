package trash.buttons;

import backend.CanvasState;
import backend.model.DrawableFigure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.control.ToggleButton;

import java.util.LinkedList;
import java.util.List;

public class selectionButton extends ToggleButton implements toggleButtons {

    public selectionButton() {
        super("Seleccionar");
    }

    public List<DrawableFigure> selectMultipleFigures(Point startPoint, Point endPoint,CanvasState canvasState){
        List<DrawableFigure> selectedFigures = new LinkedList<>();
        Rectangle selectionFigure = new Rectangle(startPoint, endPoint);
        for (DrawableFigure figure : canvasState.figures())
            if (figure.isContained(selectionFigure)) {
                selectedFigures.add(figure);
            }
        return selectedFigures;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof selectionButton)) return false;
        selectionButton button = (selectionButton) obj;
        return (button.getLabel()).equals(this.getLabel());
    }
    
    public String getLabel() {
        return this.getText();
    }
}
