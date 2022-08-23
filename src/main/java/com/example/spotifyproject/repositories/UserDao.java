package com.example.spotifyproject.repositories;

import com.example.spotifyproject.models.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {
    User getById(Long id);

    List<User> getAll();

    void insert(User user);

    void update(Long id, String name, LocalDate dateOfBirth, String email);

    void delete(User user);

    User findByName(String name);

}
