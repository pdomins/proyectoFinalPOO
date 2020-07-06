package trash.buttons;

import backend.model.DrawableFigure;
import backend.model.Line;
import backend.model.Point;

public class lineButton extends figuresToggleButtons {

    public lineButton() {
        super("Linea");
    }

    @Override
    public DrawableFigure newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint)){
            return new Line(startPoint, endPoint);
        }
        return null;
    }
}
