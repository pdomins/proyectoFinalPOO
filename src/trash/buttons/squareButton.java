package trash.buttons;


import backend.model.Point;
import backend.model.Square;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableSquare;
import javafx.scene.paint.Color;

public class squareButton extends figuresToggleButtons{
    public squareButton() {
        super("Cuadrado");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            return new DrawableSquare(startPoint, Math.abs(startPoint.getX() - endPoint.getX()), fillColor, strokeColor);
        }
        return null; // a pensarlo no?
    }

}