package trash.buttons;

import backend.model.Point;
import backend.model.Rectangle;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableRectangle;
import javafx.scene.paint.Color;

public class rectangleButton extends figuresToggleButtons {

    public rectangleButton() {
        super("Rectángulo");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (areValidPoints(startPoint,endPoint) && areValidCoord(startPoint,endPoint)){
            return new DrawableRectangle(startPoint, endPoint, fillColor, strokeColor, strokeWidth);
        }
        return null; // a pensarlo no?
    }

}
