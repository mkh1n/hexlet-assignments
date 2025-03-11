package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> getAll() {
        var authors = repository.findAll();
        var result = authors.stream().map(mapper::map).toList();
        return result;
    }

    public AuthorDTO getById(Long id) {
        var author = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Author with id " + id + " is not found"));
        var dto = mapper.map(author);
        return dto;
    }

    public AuthorDTO create(AuthorCreateDTO postData) throws DataIntegrityViolationException {

        var author = mapper.map(postData);
        repository.save(author);
        var dto = mapper.map(author);
        return dto;
    }

    public AuthorDTO update(AuthorUpdateDTO updateData, Long id) {
        var author = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Author with id " + id + " is not found"));
        mapper.update(updateData, author);
        repository.save(author);
        var dto = mapper.map(author);
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // END
}
