package frontend.Drawable;

import backend.model.Point;
import backend.model.Square;

public class DrawableSquare extends DrawableRectangle {
    public DrawableSquare(Point topLeft, double width) {
        super(topLeft, new Point(topLeft.getX()+width,topLeft.getY()+width));
    }
}
