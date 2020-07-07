package trash.buttons;

import backend.model.Point;
import frontend.Drawable.*;
import javafx.scene.paint.Color;


public enum figuresTogglesEnum {

    Rectangulo("Rectangulo"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            return new DrawableRectangle(startPoint, endPoint, fillColor, strokeColor, strokeWidth);
        }
        return null; }},
    Cuadrado("Cuadrado"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            return new DrawableSquare(startPoint, Math.abs(startPoint.getX() - endPoint.getX()), fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    Circulo("Circulo"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
            return new DrawableCircle(startPoint, circleRadius, fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    Elipse("Elipse"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)) {
            double xRadius = Math.abs(endPoint.getX() - startPoint.getX());
            double yRadius = Math.abs(endPoint.getY() - startPoint.getY());
            return new DrawableEllipse(startPoint, xRadius, yRadius, fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    Linea("Linea"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (areValidPoints(startPoint,endPoint)){
            return new DrawableLine(startPoint, endPoint, strokeColor, strokeWidth);
        }
        return null;
    }};


    private String name;
    public abstract Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth);

    figuresTogglesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static boolean pointValidations(Point startPoint, Point endPoint){
        return areValidPoints(startPoint, endPoint) && areValidCoord(startPoint, endPoint);
    }
    private static boolean areValidCoord(Point startPoint, Point endPoint){
        return endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY();
    }

    private static boolean areValidPoints (Point startPoint, Point endPoint){
        return startPoint != null && endPoint!=null;
    }
}
