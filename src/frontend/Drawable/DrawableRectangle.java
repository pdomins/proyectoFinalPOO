package frontend.Drawable;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class DrawableRectangle extends Rectangle implements Drawable {
    Color fillColor;
    Color strokeColor;
    double strokeWidth;

    public DrawableRectangle(Point topLeft, Point bottomRight, Color fillColor, Color strokeColor, double strokeWidth) {
        super(topLeft, bottomRight);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public void draw(GraphicsContext gc) {

        // fill rect fills a rectangle using the current fill paint fillRect(x,y,w,h) x upper left corner, y upper left corner, w width, h height)
        gc.fillRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
        // stroke rect strokes a rectangle using the current stroke paint strokeRect(x,y,w,h)
        gc.strokeRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
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
