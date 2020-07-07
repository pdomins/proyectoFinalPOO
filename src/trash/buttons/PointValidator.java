package trash.buttons;

import backend.model.Point;

public interface PointValidator {

    default boolean pointValidations(Point startPoint, Point endPoint){
        return areValidPoints(startPoint, endPoint) && areValidCoord(startPoint, endPoint);
    }

    static boolean areValidCoord(Point startPoint, Point endPoint){
        return endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY();
    }

    default boolean areValidPoints (Point startPoint, Point endPoint){
        return startPoint != null && endPoint!=null;
    }

}
