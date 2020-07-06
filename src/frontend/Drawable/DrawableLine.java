package frontend.Drawable;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawableLine extends Line implements Drawable {

    private Color strokeColor;
    private Color fillColor;
    private double strokeWidth;

    public DrawableLine(Point topLeft, Point bottomRight, Color strokeColor, double strokeWidth) {
        super(topLeft, bottomRight);
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
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

    @Override
    public void setFillColor(Color fillColor) {
    }

    @Override
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public double getStrokeWidth() {
        return strokeWidth;
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }
}
