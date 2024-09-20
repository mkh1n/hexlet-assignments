package exercise;
import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> list, int number) {
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List<Home> sortedList = new ArrayList<>();
        sortedList.addAll(list);
        Collections.sort(sortedList, (home1, home2) -> home1.compareTo(home2));
        List<String> result = sortedList.subList(0, number).stream()
                .map(Home::toString)
                .collect(Collectors.toList());
        return result;
    }
}
// END
