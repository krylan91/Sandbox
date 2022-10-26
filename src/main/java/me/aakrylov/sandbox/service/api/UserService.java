package me.aakrylov.sandbox.service.api;

import me.aakrylov.sandbox.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> get(long id);

    List<User> getAll();

    void save(User user);

    void update(User user, String paramsString);

    void delete(User user);
}
