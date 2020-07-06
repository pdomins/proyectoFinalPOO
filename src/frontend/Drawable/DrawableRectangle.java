package frontend.Drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle extends Rectangle implements Drawable {
    public DrawableRectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

    public void draw(GraphicsContext gc) {
        // fill rect fills a rectangle using the current fill paint fillRect(x,y,w,h) x upper left corner, y upper left corner, w width, h height)
        gc.fillRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
        // stroke rect strokes a rectangle using the current stroke paint strokeRect(x,y,w,h)
        gc.strokeRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
    }
}
