package me.aakrylov.sandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.aakrylov.sandbox.dao.api.UserDao;
import me.aakrylov.sandbox.domain.User;
import me.aakrylov.sandbox.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserDao dao;

    @Override
    public Optional<User> get(long id) {
        log.info("Looking for user with id=[{}].", id);
        return dao.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("Retrieving all users.");
        return dao.getAll();
    }

    @Override
    public void save(User user) {
        log.info("Saving user {}", user);
        dao.save(user);
    }

    @Override
    public void update(User user, String paramsString) {
        String[] params = paramsString.split(",");
        log.info("Updating user {}", user);
        dao.update(user, params);
    }

    @Override
    public void delete(User user) {
        log.info("Deleting user {}", user);
        dao.delete(user);
    }
}
