package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel entity);

    List<UserServiceModel> findAll();

    UserServiceModel findById(String id);

    UserServiceModel findByUsernameAndPassword(String username, String pass);

    void delete(String id);

    void update(UserServiceModel entity);
}
