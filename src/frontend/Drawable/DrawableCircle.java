package frontend.Drawable;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawableCircle extends DrawableEllipse {
    public DrawableCircle(Point centerPoint, double radius) {
        super(centerPoint, radius, radius);
    }
}
