package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import exercise.service.BookService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDTO> index(){
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO show(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@RequestBody AuthorCreateDTO authorData) {
        return authorService.create(authorData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO update(@PathVariable Long id, @RequestBody @Valid AuthorUpdateDTO authorData){
        return authorService.update(authorData, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
    // END
}
