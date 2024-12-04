package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        // Определение маршрута для постов с параметром page
        app.get(NamedRoutes.postsPath(), PostsController::index);

        // Определение маршрута для конкретного поста с параметром id
        app.get(NamedRoutes.postPath("{id}"), PostsController::show);

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
