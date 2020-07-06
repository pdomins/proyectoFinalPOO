package trash.buttons;

import backend.model.DrawableFigure;
import backend.model.Point;
import backend.model.Rectangle;

public class rectangleButton extends figuresToggleButtons {

    public rectangleButton() {
        super("Rectángulo");
    }

    @Override
    public DrawableFigure newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            return new Rectangle(startPoint, endPoint);
        }
        return null; // a pensarlo no?
    }

}
