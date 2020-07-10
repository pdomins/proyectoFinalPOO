package backend.model;

public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addToX(double value){
        this.x += value;
    }

    public void addToY(double value){
        this.y += value;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Point p = (Point) o;
        if (p.getX() == x) {
            if (p.getY() == y) {
                return true;
            }
        }
        return false;
    }

}
