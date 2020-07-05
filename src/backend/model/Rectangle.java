package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends DrawableFigure {

    private final Point topLeft, bottomRight;

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
    public void draw(GraphicsContext gc) {
        // fill rect fills a rectangle using the current fill paint fillRect(x,y,w,h) x upper left corner, y upper left corner, w width, h height)
        gc.fillRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
        // stroke rect strokes a rectangle using the current stroke paint strokeRect(x,y,w,h)
        gc.strokeRect(this.topLeft.getX(), this.topLeft.getY(),
                Math.abs(this.topLeft.getX() - this.bottomRight.getX()), Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
    }
}
