package trash.buttons;

import backend.model.Line;
import backend.model.Point;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableLine;
import javafx.scene.paint.Color;

public class lineButton extends figuresToggleButtons {

    public lineButton() {
        super("Linea");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor) {
        if (areValidPoints(startPoint,endPoint)){
            return new DrawableLine(startPoint, endPoint, strokeColor);
        }
        return null;
    }
}
