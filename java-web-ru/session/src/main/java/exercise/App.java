package exercise;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import io.javalin.Javalin;
import exercise.controller.SessionsController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

import static exercise.util.NamedRoutes.*;
import static io.javalin.rendering.template.TemplateUtil.model;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get(rootPath(), ctx -> {
            var page = new MainPage(ctx.sessionAttribute("currentUser"));
            ctx.render("index.jte", model("page", page));
        });

        app.get(buildSessionPath(), SessionsController::build);

        app.post(loginPath(), SessionsController::create);

        app.post(logoutPath(), SessionsController::destroy);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
