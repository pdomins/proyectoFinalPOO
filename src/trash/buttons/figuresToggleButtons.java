package trash.buttons;

import backend.model.DrawableFigure;
import backend.model.Point;
import javafx.scene.control.ToggleButton;

public abstract class figuresToggleButtons extends ToggleButton implements toggleButtons{

    public figuresToggleButtons(String text){
        super(text);
    }

    public static boolean areValidCoord(Point startPoint, Point endPoint){
        return endPoint.getX() >= startPoint.getX() || endPoint.getY() >= startPoint.getY();
    }

    public static boolean areValidPoints (Point startPoint, Point endPoint){
        return startPoint != null && endPoint!=null;
        //por que el end point no lo chequea en paint pane?
    }

    public abstract DrawableFigure newFigure(Point startPoint, Point endPoint);

}
