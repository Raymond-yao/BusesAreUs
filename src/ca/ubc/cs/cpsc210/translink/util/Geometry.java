package ca.ubc.cs.cpsc210.translink.util;

/**
 * Compute relationships between points, lines, and rectangles represented by LatLon objects
 */
public class Geometry {
    /**
     * Return true if the point is inside of, or on the boundary of, the rectangle formed by northWest and southeast
     *
     * @param northWest the coordinate of the north west corner of the rectangle
     * @param southEast the coordinate of the south east corner of the rectangle
     * @param point     the point in question
     * @return true if the point is on the boundary or inside the rectangle
     */
    // TODO: Task 5: Implement this method
    public static boolean rectangleContainsPoint(LatLon northWest, LatLon southEast, LatLon point) {
        if (northWest.getLatitude() < point.getLatitude() || southEast.getLatitude() > point.getLatitude())
            return false;
        if (northWest.getLongitude() > point.getLongitude() || southEast.getLongitude() < point.getLongitude())
            return false;

        return true;
    }

    /**
     * Return true if the rectangle intersects the line
     *
     * @param northWest the coordinate of the north west corner of the rectangle
     * @param southEast the coordinate of the south east corner of the rectangle
     * @param src       one end of the line in question
     * @param dst       the other end of the line in question
     * @return true if any point on the line is on the boundary or inside the rectangle
     */
    // TODO: Tasks 5: Implement this method
    public static boolean rectangleIntersectsLine(LatLon northWest, LatLon southEast, LatLon src, LatLon dst) {

        LatLon southWest = new LatLon(southEast.getLatitude(), northWest.getLongitude());
        LatLon northEast = new LatLon(northWest.getLatitude(), southEast.getLongitude());

        if (rectangleContainsPoint(northWest, southEast, src) || rectangleContainsPoint(northWest, southEast, dst))
            return true;
        if (differentSide(src, dst, northWest, northEast) && differentSide(northWest, northEast, src, dst))
            return true;
        if (differentSide(src, dst, southWest, southEast) && differentSide(southWest, southEast, src, dst))
            return true;
        if (differentSide(src, dst, southWest, northWest) && differentSide(southWest, northWest, src, dst))
            return true;
        if (differentSide(src, dst, southEast, northEast) && differentSide(southEast, northEast, src, dst))
            return true;
        if (goThroughpoint(src, dst, northEast) || goThroughpoint(src, dst, northWest) || goThroughpoint(src, dst, southEast) || goThroughpoint(src, dst, southWest))
            return true;

        return false;
    }

    public static boolean goThroughpoint(LatLon src, LatLon dst, LatLon point) {
        double y1 = src.getLatitude();
        double y2 = dst.getLatitude();
        double x1 = src.getLongitude();
        double x2 = dst.getLongitude();
        double y = point.getLatitude();
        double x = point.getLongitude();

        if (x1 - x2 != 0.0) {
            return y - (((y1 - y2) / (x1 - x2)) * (x - x1) + y1) == 0.0;
        } else {
            return x - x1 == 0;
        }

    }

    public static boolean differentSide(LatLon src, LatLon dst, LatLon point1, LatLon point2) {
        double y1 = src.getLatitude();
        double y2 = dst.getLatitude();
        double x1 = src.getLongitude();
        double x2 = dst.getLongitude();
        double ypoint1 = point1.getLatitude();
        double xpoint1 = point1.getLongitude();
        double ypoint2 = point2.getLatitude();
        double xpoint2 = point2.getLongitude();

        double result1;
        double result2;

        if (x1 - x2 != 0.0) {
            result1 = ypoint1 - (((y1 - y2) / (x1 - x2)) * (xpoint1 - x1) + y1);
            result2 = ypoint2 - (((y1 - y2) / (x1 - x2)) * (xpoint2 - x1) + y1);
        } else {
            result1 = xpoint1 - x1;
            result2 = xpoint2 - x1;
        }

        return result1 * result2 < 0.0;
    }

    /**
     * A utility method that you might find helpful in implementing the two previous methods
     * Return true if x is >= lwb and <= upb
     *
     * @param lwb the lower boundary
     * @param upb the upper boundary
     * @param x   the value in question
     * @return true if x is >= lwb and <= upb
     */
    private static boolean between(double lwb, double upb, double x) {
        return lwb <= x && x <= upb;
    }
}
