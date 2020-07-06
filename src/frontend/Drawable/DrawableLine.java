package frontend.Drawable;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawableLine extends Line implements Drawable {
    public DrawableLine(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }
    public void draw(GraphicsContext gc) {
        //strokeLine(x1,y1,x2,y2)
        gc.strokeLine(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }
}
