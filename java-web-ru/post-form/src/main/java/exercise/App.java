package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Locale;

import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var firstName = ctx.formParam("firstName")
                    .substring(0, 1).toUpperCase() +  ctx.formParam("firstName").substring(1);
            var lastName = ctx.formParam("lastName")
                    .substring(0, 1).toUpperCase() +  ctx.formParam("lastName").substring(1);
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = Security.encrypt(ctx.formParam("password"));

            var user = new User(firstName, lastName, email, password);
            UserRepository.save(user);

            ctx.redirect("/users");

        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
