package frontend.Drawable;

import backend.model.Point;
import backend.model.Square;
import javafx.scene.paint.Color;

public class DrawableSquare extends DrawableRectangle {
    public DrawableSquare(Point topLeft, double width, Color fillColor, Color strokeColor, double strokeWidth) {
        super(topLeft, new Point(topLeft.getX()+width,topLeft.getY()+width), fillColor, strokeColor, strokeWidth);
    }
}
