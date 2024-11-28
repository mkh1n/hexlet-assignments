package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            var filter = ctx.queryParamAsClass("term", String.class).getOrDefault("");
            List<User> filteredUsers;
            if (filter.isEmpty()) {
                filteredUsers = USERS; // Return all users if term is empty
            } else {
                filteredUsers = USERS.stream()
                        .filter(u -> u.getFirstName().toLowerCase().contains(filter.toLowerCase()))
                        .collect(Collectors.toList());
            }

            logger.info("Filter: {}", filter);
            logger.info("Filtered Users: {}", filteredUsers);

            UsersPage page = new UsersPage(filteredUsers, filter);
            logger.info("UsersPage: {}", page);

            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
