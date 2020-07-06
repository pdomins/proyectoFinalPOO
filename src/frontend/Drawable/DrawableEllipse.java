package frontend.Drawable;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse extends Ellipse implements Drawable {
    private Color fillColor;
    private Color strokeColor;

    public DrawableEllipse(Point centerPoint, double xAxisRadius, double yAxisRadius, Color fillColor, Color strokeColor) {
        super(centerPoint, xAxisRadius, yAxisRadius);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    public void draw(GraphicsContext gc) {
        double widthDiameter = this.getxAxisRadius() * 2;
        double heightDiameter = this.getyAxisRadius() * 2;
        //fillOval(x,y,w,h) y strokeOval(x,y,w,h)  x coordinate of the upper left bound, y coordinate of the upper left bound, width at the center of the oval, height at the center of the oval
        gc.fillOval(this.centerPoint.getX() - this.getxAxisRadius(), this.centerPoint.getY() - this.getyAxisRadius(), widthDiameter, heightDiameter);
        gc.strokeOval(this.centerPoint.getX() - this.getxAxisRadius(), this.centerPoint.getY() - this.getyAxisRadius(), widthDiameter, heightDiameter);

    }
    public Color getFillColor() {
        return fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }
}
