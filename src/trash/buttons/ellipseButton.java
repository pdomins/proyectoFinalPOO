package trash.buttons;


import backend.model.Ellipse;
import backend.model.Point;
import frontend.Drawable.Drawable;
import frontend.Drawable.DrawableEllipse;
import javafx.scene.paint.Color;

public class ellipseButton extends figuresToggleButtons {

    public ellipseButton() {
        super("Elipse");
    }

    @Override
    public Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor) {
        double xRadius = Math.abs(endPoint.getX() - startPoint.getX());
        double yRadius = Math.abs(endPoint.getY() - startPoint.getY());
        return new DrawableEllipse(startPoint, xRadius, yRadius, fillColor, strokeColor);
    }

}