package exercise.controller.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {

    private static final List<Post> additionalPosts = new ArrayList<>();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable int id) {
        return Stream.concat(Data.getPosts().stream(), additionalPosts.stream())
                .filter(p -> p.getUserId() == id)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable int id, @RequestBody Post post) {

        post.setUserId(id);

        Post responsePost = new Post();
        responsePost.setUserId(post.getUserId());
        responsePost.setSlug(post.getSlug());
        responsePost.setTitle(post.getTitle());
        responsePost.setBody(post.getBody());

        additionalPosts.add(post);

        return responsePost;
    }
}