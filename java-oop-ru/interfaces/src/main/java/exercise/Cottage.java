package exercise;
import lombok.Getter;
// BEGIN
@Getter
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    public String toString() {
        var meters = String.format("%.1f", area);
        return String.format("%d этажный коттедж площадью %s метров", floorCount, meters);
    }
}
// END
