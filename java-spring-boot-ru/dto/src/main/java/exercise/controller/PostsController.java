package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDTO> getCommentsDTOList (long postId) {
        return commentRepository.
                findByPostId(postId)
                .stream()
                .map(c -> {
                    var commentDTO = new CommentDTO();
                    commentDTO.setBody(c.getBody());
                    commentDTO.setId(c.getId());

                    return commentDTO;
                }).toList();
    }

    @GetMapping
    public List<PostDTO> index(){
        var posts = postRepository.findAll();
        var result = posts.stream().map(p -> {
            var postDTO = new PostDTO();

            postDTO.setId(p.getId());
            postDTO.setBody(p.getBody());
            postDTO.setTitle(p.getTitle());
            postDTO.setComments(getCommentsDTOList(p.getId()));

            return postDTO;
        }).toList();
        return result;
    }

    @GetMapping("{id}")
    public PostDTO show(@PathVariable("id") long id){
        var maybePost = postRepository.findById(id);
        if (maybePost.isPresent()){
            var post = maybePost.get();
            var postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            postDTO.setComments(getCommentsDTOList(post.getId()));
            return postDTO;
        }
        else {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        }

    }
}

// END
