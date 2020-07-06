package frontend.Drawable;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawableCircle extends DrawableEllipse {
    public DrawableCircle(Point centerPoint, double radius, Color fillColor, Color strokeColor) {
        super(centerPoint, radius, radius, fillColor, strokeColor);
    }
}
