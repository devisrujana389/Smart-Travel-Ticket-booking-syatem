package com.decodelabs.UserApi;

//import com.decodelabs.Userapi.User;
//import com.decodelabs.Userapi.Repositary;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    private final Repositary repo;

    public Controller(Repositary repo) {
        this.repo = repo;
    }

    // READ all
    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    // READ one
    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        user.setId(null); // make sure a new row is created
        try {
            return repo.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody User newData) {
        User existing = repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (repo.existsByEmailAndIdNot(newData.getEmail(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        existing.setEmail(newData.getEmail());
        existing.setAge(newData.getAge());
        existing.setActive(newData.isActive());
        try {
            return repo.save(existing);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repo.deleteById(id);
    }
}