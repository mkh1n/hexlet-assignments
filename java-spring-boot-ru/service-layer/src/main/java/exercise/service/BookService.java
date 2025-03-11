package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private AuthorMapper authorMapper;


    public List<BookDTO> getAll() {
        var books = repository.findAll();
        var result = books.stream().map(mapper::map).toList();
        return result;
    }

    public BookDTO getById(Long id) {
        var book = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Book with id " + id + " is not found"));
        var dto = mapper.map(book);
        return dto;
    }

    public BookDTO create(BookCreateDTO postData) {
    var book = mapper.map(postData);
    repository.save(book);
    var dto = mapper.map(book);
    return dto;
}


    public BookDTO update(BookUpdateDTO updateData, Long id) {
        var book = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Book with id " + id + " is not found"));
        var mayBeAuthorId = updateData.getAuthorId();
        if (mayBeAuthorId.isPresent()){
            var authorId = mayBeAuthorId.get();
            var author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author with id " + authorId + " is not found"));
        }

        mapper.update(updateData, book);
        repository.save(book);
        var dto = mapper.map(book);
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
