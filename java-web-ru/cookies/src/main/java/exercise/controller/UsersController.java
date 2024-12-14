package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;
import org.eclipse.jetty.server.Authentication;

public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    public static void show(Context ctx) throws Exception {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var userOptional = UserRepository.find(id);
        if (userOptional.isPresent()){
            var user = userOptional.get();
            var presentToken = ctx.cookie("token");
            if (user.getToken().equals(presentToken)) {
                var page = new UserPage(user);
                ctx.render("users/show.jte", model("page", page));
            } else {
                ctx.render("users/build.jte");
            }
        }
    }

    public static void signIn(Context ctx) throws Exception {
        var firstName = ctx.formParamAsClass("firstName", String.class).get();
        var lastName = ctx.formParamAsClass("lastName", String.class).get();
        var email = ctx.formParamAsClass("email", String.class).get();
        var password = ctx.formParamAsClass("password", String.class).get();

        var token = Security.generateToken();
        ctx.cookie("token", token);

        var user = new User(firstName, lastName, email, password, token);

        UserRepository.save(user);

        var id = user.getId();

        ctx.redirect(String.format("/users/%s", id));
    }
}
