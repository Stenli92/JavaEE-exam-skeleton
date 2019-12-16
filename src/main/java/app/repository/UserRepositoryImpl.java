package app.repository;

import app.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createNativeQuery("SELECT  * from user " , User.class).getResultList();
    }

    @Override
    public User findById(String id) {
        return this.entityManager.createQuery("SELECT u from USER u where u.id =: id", User.class )
                .setParameter("id" , id).getSingleResult();
    }

    @Override
    public User findByUsernameAndPassword(String username , String pass) {
        return this.entityManager.createQuery("select u from User u " +
                "where u.name= :username and u.password = :password" , User.class)
                .setParameter("username" , username)
                .setParameter("password" , pass).getSingleResult();
    }

    @Override
    public void delete(String id) {

        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("DELETE  from User u where u.id = :id" ,User.class)
                .setParameter("id" , id).executeUpdate();
        this.entityManager.getTransaction().commit();


    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();

    }
}
