package trash.buttons;

import backend.model.DrawableFigure;
import backend.model.Point;
import backend.model.Square;

public class squareButton extends figuresToggleButtons{
    public squareButton() {
        super("Cuadrado");
    }

    @Override
    public DrawableFigure newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            return new Square(startPoint, Math.abs(startPoint.getX() - endPoint.getX()));
        }
        return null; // a pensarlo no?
    }

}