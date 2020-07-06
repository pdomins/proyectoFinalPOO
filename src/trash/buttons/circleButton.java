package trash.buttons;

import backend.model.Point;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableCircle;

public class circleButton extends figuresToggleButtons {

    public circleButton() {
        super("Círculo");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
            return new DrawableCircle(startPoint, circleRadius);
        }
        return null;
    }
}
