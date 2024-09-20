package exercise;
import lombok.Getter;

// BEGIN
@Getter
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area + balconyArea;
    }

    public String toString() {
        var meters = String.format("%.1f", area + balconyArea);
        return String.format("Квартира площадью %s метров на %d этаже", meters, floor);
    }
}
// END
