package com.example.spotifyproject.controllers;

import com.example.spotifyproject.models.User;
import com.example.spotifyproject.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    private final UserDao userDao;

    @Autowired
    public UserRestController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userDao.insert(user);
    }

    @GetMapping("/showAllUsers")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> userList = userDao.getAll();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userList);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam Long id) {
        User user = userDao.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/findUser")
    public ResponseEntity<User> findUser(@RequestParam String name) {
        User user = userDao.findByName(name);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody User user) {
        userDao.update(user.getId(), user.getName(), user.getDateOfBirth(), user.getEmail());

    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody User user) {
        userDao.delete(user);

    }
}
