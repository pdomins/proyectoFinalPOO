package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Line extends DrawableFigure{

    private final Point topLeft, bottomRight;
    private final int lambda = 100;

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
        //strokeLine(x1,y1,x2,y2)
        gc.strokeLine(topLeft.getX(), topLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }

    @Override
    public boolean containsPoint(Point point) {
        // the value of the cross product between the three points must be 0 if the point is inside the line
        double value = ((bottomRight.getX()*point.getY()-bottomRight.getY()*point.getX())-
                            (topLeft.getX()*point.getY()-topLeft.getX()*point.getX())+
                                (topLeft.getX()*bottomRight.getY()-topLeft.getY()*bottomRight.getX()));
        return (value + lambda >= 0) && (value - lambda <= 0);
    }

    @Override
    public boolean isContained(Rectangle rectangle) {
        return rectangle.containsPoint(topLeft) && containsPoint(bottomRight);
    }
}
