package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends DrawableFigure {

    protected final Point centerPoint;
    protected final double xAxisRadius;
    protected final double yAxisRadius;

    public Ellipse(Point centerPoint, double xAxisRadius, double yAxisRadius) {
        this.centerPoint = centerPoint;
        this.xAxisRadius = xAxisRadius;
        this.yAxisRadius = yAxisRadius;
    }
    public Point getCenterPoint() {
        return centerPoint;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, Radio Horizontal: %.2f, Radio Vertical : %.2f]", centerPoint, xAxisRadius,yAxisRadius);
    }

    public double getxAxisRadius() {
        return xAxisRadius;
    }

    public double getyAxisRadius() {
        return yAxisRadius;
    }

    @Override
    public boolean containsPoint(Point point) {
        double aux1 = Math.pow(point.getX() - centerPoint.getX(),2) / Math.pow(xAxisRadius,2);
        double aux2 = Math.pow(point.getY() - centerPoint.getY(),2) / Math.pow(yAxisRadius,2);
        return aux1 + aux2 <= 1;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
