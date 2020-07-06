package trash.buttons;

import backend.model.Line;
import backend.model.Point;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableLine;

public class lineButton extends figuresToggleButtons {

    public lineButton() {
        super("Linea");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint)){
            return new DrawableLine(startPoint, endPoint);
        }
        return null;
    }
}
