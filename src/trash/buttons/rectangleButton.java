package trash.buttons;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableRectangle;

public class rectangleButton extends figuresToggleButtons {

    public rectangleButton() {
        super("Rectángulo");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            return new DrawableRectangle(startPoint, endPoint);
        }
        return null; // a pensarlo no?
    }

}
