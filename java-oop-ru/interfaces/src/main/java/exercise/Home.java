package exercise;

// BEGIN
public interface Home {
     double getArea();
    default int compareTo(Home object) {
        return Double.compare(this.getArea(), object.getArea());
    }
}
// END
