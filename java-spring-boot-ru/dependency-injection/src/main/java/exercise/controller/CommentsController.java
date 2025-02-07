package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment indexById(@PathVariable Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment update(@RequestBody Comment data, @PathVariable Long id) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        comment.setBody(data.getBody());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        commentRepository.deleteById(id);
    }
}
