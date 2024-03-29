package app.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AppBeanConfiguration {

    @Produces
    public EntityManager entityManager(){

        return Persistence.createEntityManagerFactory("exam").createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }
}
