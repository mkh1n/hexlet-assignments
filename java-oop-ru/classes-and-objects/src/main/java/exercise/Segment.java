package exercise;
// BEGIN
import lombok.Getter;

@Getter
public class Segment{
    private Point beginPoint;
    private Point endPoint;

    public Point(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }
    public Point getMidPoint(){
        var midX = (beginPoint.getX() + endPoint.getX()) / 2;
        var midY = (beginPoint.getY() + endPoint.getY()) / 2;

        return new Point(midX, midY)
    }
}
// END
