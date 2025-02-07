package exercise.controller;

import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post indexById(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@RequestBody Post data, @PathVariable Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        post.setBody(data.getBody());
        post.setTitle(data.getTitle());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
