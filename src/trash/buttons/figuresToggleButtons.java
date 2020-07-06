package trash.buttons;

import backend.model.Point;
import frontend.Drawable.Drawable;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public abstract class figuresToggleButtons extends toggleButtons{

    public figuresToggleButtons(String text) {
        super(text);
    }

    public static boolean areValidCoord(Point startPoint, Point endPoint){
        return endPoint.getX() >= startPoint.getX() || endPoint.getY() >= startPoint.getY();
    }

    public static boolean areValidPoints (Point startPoint, Point endPoint){
        return startPoint != null && endPoint!=null;
        //por que el end point no lo chequea en paint pane?
    }

    public abstract Drawable newFigure(Point startPoint, Point endPoint, Color fillColor, Color strokeColor);

}
