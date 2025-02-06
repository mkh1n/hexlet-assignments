package exercise;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {


    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties admins;

    @GetMapping("/admins")
    public List<String> indexAdmins() {
        List<String> adminNames = admins.getAdmins();
        return users.stream().filter(u -> adminNames.contains(u.getEmail())).map(u -> u.getName()).toList();
    }

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
