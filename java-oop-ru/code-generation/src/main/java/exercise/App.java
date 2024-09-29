package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws IOException {
        String json = car.serialize();
        Files.write(path, json.getBytes());
    }

    public static Car extract(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        String json = String.join("", lines);
        return Car.deserialize(json);
    }
}
// END
