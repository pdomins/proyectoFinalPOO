package frontend.Drawable;

import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawableEllipse extends Ellipse implements Drawable {
    public DrawableEllipse(Point centerPoint, double xAxisRadius, double yAxisRadius) {
        super(centerPoint, xAxisRadius, yAxisRadius);
    }

    public void draw(GraphicsContext gc) {
        double widthDiameter = this.getxAxisRadius() * 2;
        double heightDiameter = this.getyAxisRadius() * 2;
        //fillOval(x,y,w,h) y strokeOval(x,y,w,h)  x coordinate of the upper left bound, y coordinate of the upper left bound, width at the center of the oval, height at the center of the oval
        gc.fillOval(this.centerPoint.getX() - this.getxAxisRadius(), this.centerPoint.getY() - this.getyAxisRadius(), widthDiameter, heightDiameter);
        gc.strokeOval(this.centerPoint.getX() - this.getxAxisRadius(), this.centerPoint.getY() - this.getyAxisRadius(), widthDiameter, heightDiameter);

    }
}
