package app.service;

import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;
import app.repository.UserRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserServiceModel entity) {

        this.userRepository.save(this.mapper.map(entity , User.class));

    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository.findAll().stream()
                .map(e -> this.mapper.map(e , UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findById(String id) {
        return this.mapper.map(this.userRepository.findById(id) , UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String pass) {
        return this.mapper.map(this.userRepository.findByUsernameAndPassword(username , pass) , UserServiceModel.class);
    }

    @Override
    public void delete(String id) {

        this.userRepository.delete(id);

    }

    @Override
    public void update(UserServiceModel entity) {

        this.userRepository.update(this.mapper.map(entity , User.class));

    }
}
