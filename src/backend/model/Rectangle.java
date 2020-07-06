package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {

    protected Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean containsPoint(Point point) {
        return point.getX() > this.getTopLeft().getX() && point.getX() < this.getBottomRight().getX() &&
                point.getY() > this.getTopLeft().getY() && point.getY() < this.getBottomRight().getY();
    }

    @Override
    public boolean isContained(Rectangle rectangle) {
        return rectangle.containsPoint(topLeft) && rectangle.containsPoint(bottomRight);
    }

    @Override
    public void move(double diffX, double diffY) {
        this.getTopLeft().addToX(diffX);
        this.getBottomRight().addToX(diffX);
        this.getTopLeft().addToY(diffY);
        this.getBottomRight().addToY(diffY);
    }
}
