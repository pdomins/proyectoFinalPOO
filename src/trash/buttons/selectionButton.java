package trash.buttons;

import frontend.CanvasState;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.Drawable.Drawable;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;

import java.util.LinkedList;
import java.util.List;

public class selectionButton extends ToggleButton implements PointValidator {

    public selectionButton() {
        super("Seleccionar");
        this.setMinWidth(90);
        this.setCursor(Cursor.HAND);

    }

    public List<Drawable> selectMultipleFigures(Point startPoint, Point endPoint, CanvasState canvasState){
        List<Drawable> selectedFigures = new LinkedList<>();
        if (pointValidations(startPoint, endPoint)) {
            Rectangle selectionFigure = new Rectangle(startPoint, endPoint);
            for (Drawable figure : canvasState.figures())
                if (figure.isContained(selectionFigure)) {
                    selectedFigures.add(figure);
                }
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
