package exercise.controller;

import exercise.dto.LoginPage;
import exercise.util.Generator;
import io.javalin.http.Context;

import static exercise.util.Security.encrypt;
import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", model("page", page));    }

    public static void create(Context ctx) {
        var formName = ctx.formParam("name");
        var formPassword = ctx.formParam("password");

        // Проверка пароля
        var users = Generator.getUsers();
        var userOptional = users.stream().filter(u -> u.getName().equals(formName)).findFirst();

        if (userOptional.isPresent()) {
            var user = userOptional.get();
            if (user.getPassword().equals(encrypt(formPassword))) {
                ctx.sessionAttribute("currentUser", formName);
                ctx.redirect("/");
            }
        }
        var page = new LoginPage(formName, "Wrong username or password.");
        ctx.render("build.jte", model("page", page));
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
}