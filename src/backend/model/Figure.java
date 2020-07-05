package backend.model;

public abstract class Figure {

    public abstract boolean containsPoint(Point point);
    public abstract boolean isContained(Rectangle rectangle);
    public abstract void move(double diffX, double diffY);

}
