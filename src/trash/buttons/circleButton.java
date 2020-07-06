package trash.buttons;

import backend.model.Point;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableCircle;
import javafx.scene.paint.Color;

public class circleButton extends figuresToggleButtons {

    public circleButton() {
        super("Círculo");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
            return new DrawableCircle(startPoint, circleRadius, fillColor, strokeColor);
        }
        return null;
    }
}
