package exercise;
// BEGIN
import lombok.Getter;

@Getter
public class Point{
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
// END
