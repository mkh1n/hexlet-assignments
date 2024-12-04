package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        var currentPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var begin = (currentPage - 1) * 5;
        if (currentPage <= 0 || (begin >= PostRepository.getEntities().size())){
            currentPage = 1;
        }
        var posts = PostRepository.findAll(currentPage, 5);

        var page = new PostsPage(currentPage, posts);

        ctx.render("posts/index.jte", model( "page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id);

        if (post.isPresent()) {
            var page = new PostPage(post.get());
            ctx.render("posts/show.jte", model("page", page));
        } else {
            ctx.status(404);
            ctx.result("Page not found");
        }
    }
}
