package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class UserDaoImplementation implements UserDao {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    //probably Optional<User>
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByName(String name) {
        return (User) entityManager.createQuery("SELECT user  FROM User user WHERE user.name LIKE :name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) entityManager.createQuery("SELECT user  FROM User user").getResultList();
    }

    @Override
    public void insert(User user) {
        if(user.getId() == null) {
            entityManager.persist(user);
        }else {
            entityManager.merge(user);
        }
    }

    @Override
    public void update(Long id, String name, LocalDate dateOfBirth, String email) {
        User user = entityManager.find(User.class, id);
        user.setName(name);
        user.setDateOfBirth(dateOfBirth);
        user.setEmail(email);
       // entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        User userInDatabase = entityManager.find(User.class, user.getId());
        user.getPlaylists().clear();
        entityManager.remove(userInDatabase);

        //entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }





}
