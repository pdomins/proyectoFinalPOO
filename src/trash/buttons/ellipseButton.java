package trash.buttons;

import backend.model.DrawableFigure;
import backend.model.Ellipse;
import backend.model.Point;

public class ellipseButton extends figuresToggleButtons {

    public ellipseButton() {
        super("Elipse");
    }

    @Override
    public DrawableFigure newFigure(Point startPoint, Point endPoint) {
        double xRadius = Math.abs(endPoint.getX() - startPoint.getX());
        double yRadius = Math.abs(endPoint.getY() - startPoint.getY());
        return new Ellipse(startPoint, xRadius, yRadius);
    }

}
