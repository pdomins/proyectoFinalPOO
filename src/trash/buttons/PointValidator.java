package trash.buttons;

import backend.model.Point;

public interface PointValidator {

    public default boolean pointValidations(Point startPoint, Point endPoint){
        return areValidPoints(startPoint, endPoint) && areValidCoord(startPoint, endPoint);
    }
    private static boolean areValidCoord(Point startPoint, Point endPoint){
        return endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY();
    }

    public default boolean areValidPoints (Point startPoint, Point endPoint){
        return startPoint != null && endPoint!=null;
    }

}
