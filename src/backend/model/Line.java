package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Line extends DrawableFigure{

    private final Point topLeft, bottomRight;

    public Line(Point topLeft, Point bottomRight) {
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
        return String.format("Linea [ %s , %s ]", topLeft, bottomRight);
    }


    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public boolean containsPoint(Point point) {
        return false;
    }
}
