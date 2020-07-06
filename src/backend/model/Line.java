package backend.model;


public class Line extends Figure{

    protected Point topLeft, bottomRight;
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
    public boolean containsPoint(Point point) {
        return isBetween(bottomRight,topLeft,point);
    }

    @Override
    public boolean isContained(Rectangle rectangle) {
        return rectangle.containsPoint(topLeft) && containsPoint(bottomRight);
    }

    @Override
    public void move(double diffX, double diffY) {
        this.getTopLeft().addToX(diffX);
        this.getBottomRight().addToX(diffX);
        this.getTopLeft().addToY(diffY);
        this.getBottomRight().addToY(diffY);
    }

    private boolean isBetween(Point a, Point b, Point c){
        //to know if c is between a and b
        //the crossproduct(b-a)(c-a) must be 0 (or near in case of using doubles) if the points are aligned
        double crossproduct = (c.getY()-a.getY())*(b.getX()-a.getX())-(c.getX()-a.getX())*(b.getY()-a.getY());
        if ( Math.abs(crossproduct) > lambda) return false;
        //if the dotproduct(b-a)(c-a) is positive and less than the square of the distance(ab) then c is between a and b
        double dotproduct = (c.getX()-a.getX())*(b.getX()-a.getX())+(c.getY()-a.getY())*(b.getY()-a.getY());
        if (dotproduct < 0) return false;
        double squaredlengthba = (b.getX()-a.getX())*(b.getX()-a.getX())+(b.getY()-a.getY())*(b.getY()-a.getY());
        return !(dotproduct > squaredlengthba);

    }
}
