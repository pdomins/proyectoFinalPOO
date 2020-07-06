package frontend.Drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public interface Drawable {
    void draw(GraphicsContext gc);
    boolean isContained(Rectangle rectangle);
    void move(double diffX, double diffY);
    boolean containsPoint(Point point);
}
