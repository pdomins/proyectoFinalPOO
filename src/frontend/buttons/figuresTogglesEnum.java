package frontend.buttons;

import backend.model.Point;
import frontend.Drawable.*;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public enum figuresTogglesEnum implements PointValidator {

    RECTANGLE("Rectángulo","file:cursores/rectangleCursor.png"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            return new DrawableRectangle(startPoint, endPoint, fillColor, strokeColor, strokeWidth);
        }
        return null; }},
    SQUARE("Cuadrado","file:cursores/squareCursor.png"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            return new DrawableSquare(startPoint, Math.abs(startPoint.getX() - endPoint.getX()), fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    CIRCLE("Círculo","file:cursores/circleCursor.png"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)){
            double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
            return new DrawableCircle(startPoint, circleRadius, fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    ELLIPSE("Elipse","file:cursores/ellipseCursor.png"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (pointValidations(startPoint,endPoint)) {
            double xRadius = Math.abs(endPoint.getX() - startPoint.getX())/2;
            double yRadius = Math.abs(endPoint.getY() - startPoint.getY())/2;
            startPoint.addToX(xRadius);
            startPoint.addToY(yRadius);
            return new DrawableEllipse(startPoint, xRadius, yRadius, fillColor, strokeColor, strokeWidth);
        }
        return null;
    }},
    LINE("Línea","file:cursores/lineCursor.png"){
        @Override
        public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth) {
        if (areValidPoints(startPoint,endPoint)){
            return new DrawableLine(startPoint, endPoint, strokeColor, strokeWidth);
        }
        return null;
    }};


    private String name;
    ImageCursor cursorImage;

    public abstract Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor, double strokeWidth);

    figuresTogglesEnum(String name, String imagePath) {
        this.name = name;
        this.cursorImage = new ImageCursor(new Image(imagePath));
    }

    public ImageCursor getCursorImage() {
        return cursorImage;
    }

    public String getName() {
        return name;
    }

    public static figuresTogglesEnum matchAndGetButtonName(String name) {
        for (figuresTogglesEnum figuresTog : figuresTogglesEnum.values()) {
            if (figuresTog.getName().equals(name)){
                return figuresTog;
            }
        }
        return null;
    }

}
