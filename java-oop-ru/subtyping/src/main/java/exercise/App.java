package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var map = storage.toMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            storage.set(entry.getValue(), entry.getKey());
            storage.unset(entry.getKey());
        }
    }
}
// END
