package app.repository;

import app.domain.entities.User;

import java.util.List;

public interface UserRepository {

    void save(User entity);

    List<User> findAll();

    User findById(String id);

    User findByUsernameAndPassword(String username, String pass);

    void delete(String id);

    void update(User entity);
}
