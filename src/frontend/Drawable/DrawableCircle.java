package frontend.Drawable;

import backend.model.Point;
import javafx.scene.paint.Color;


public class DrawableCircle extends DrawableEllipse {
    public DrawableCircle(Point centerPoint, double radius, Color fillColor, Color strokeColor, double strokeWidth) {
        super(centerPoint, radius, radius, fillColor, strokeColor, strokeWidth);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, xAxisRadius);
    }
}
