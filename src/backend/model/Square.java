package backend.model;

public class Square extends Rectangle{

    public Square(Point topLeft, double width) {
        super(topLeft,new Point(topLeft.getX()+width,topLeft.getY()+width));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", this.getTopLeft(), this.getBottomRight());
    }
}
