package app.web.beans;


import app.domain.models.binding.UserRegisterBingindModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserRegisterBean extends BaseBean{

    private UserRegisterBingindModel model;
    private UserService userService;
    private ModelMapper mapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init(){

        this.model = new UserRegisterBingindModel();
    }

    public void register(){

        if (!this.model.getPassword().equals(model.getConfirmPassword())){
                this.redirect("/register");
        }

        this.model.setPassword(DigestUtils.sha256Hex(model.getPassword()));
        this.userService.register
                (this.mapper.map(model, UserServiceModel.class));


    }

    public UserRegisterBingindModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBingindModel model) {
        this.model = model;
    }
}
