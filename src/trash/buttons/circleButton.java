package trash.buttons;

import backend.model.Circle;
import backend.model.DrawableFigure;
import backend.model.Point;

public class circleButton extends figuresToggleButtons {

    public circleButton() {
        super("Círculo");
    }

    @Override
    public DrawableFigure newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
            return new Circle(startPoint, circleRadius);
        }
        return null;
    }
}
