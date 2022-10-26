package me.aakrylov.sandbox.controller;

import lombok.RequiredArgsConstructor;
import me.aakrylov.sandbox.domain.User;
import me.aakrylov.sandbox.service.api.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable("id") long id) {
        return service.get(id);
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody User user) {
        service.save(user);
    }

    @PutMapping("/")
    public void update(@RequestBody User user, @RequestParam("params") String paramsString) {
        service.update(user, paramsString);
    }

    @DeleteMapping("/")
    public void delete(@RequestBody User user) {
        service.delete(user);
    }

}
