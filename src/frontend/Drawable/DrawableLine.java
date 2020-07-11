package frontend.Drawable;

import backend.model.Line;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableLine extends Line implements Drawable {

    private Color strokeColor;
    private double strokeWidth;

    public DrawableLine(Point topLeft, Point bottomRight, Color strokeColor, double strokeWidth) {
        super(topLeft, bottomRight);
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    public void draw(GraphicsContext gc) {
        gc.strokeLine(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }
    public Color getFillColor() {
        return Color.TRANSPARENT;
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
