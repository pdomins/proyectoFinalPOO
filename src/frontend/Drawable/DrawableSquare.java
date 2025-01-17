package frontend.Drawable;

import backend.model.Point;
import javafx.scene.paint.Color;

public class DrawableSquare extends DrawableRectangle {
    public DrawableSquare(Point topLeft, double width, Color fillColor, Color strokeColor, double strokeWidth) {
        super(topLeft, new Point(topLeft.getX()+width,topLeft.getY()+width), fillColor, strokeColor, strokeWidth);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", this.getTopLeft(), this.getBottomRight());
    }
}
