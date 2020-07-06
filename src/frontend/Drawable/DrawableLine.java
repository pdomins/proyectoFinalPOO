package frontend.Drawable;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawableLine extends Line implements Drawable {

    private Color strokeColor;
    private Color fillColor;

    public DrawableLine(Point topLeft, Point bottomRight, Color strokeColor) {
        super(topLeft, bottomRight);
        this.strokeColor = strokeColor;
    }
    public void draw(GraphicsContext gc) {
        //strokeLine(x1,y1,x2,y2)
        gc.strokeLine(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }
    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }
}
