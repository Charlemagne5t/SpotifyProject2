package com.example.spotifyproject.MVCcontrollers;

import com.example.spotifyproject.models.User;
import com.example.spotifyproject.repositories.UserDao;
import com.example.spotifyproject.services.ParserHTMLDateToJavaLocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserViewController {
    private final UserDao userDao;

    @Autowired
    public UserViewController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public String viewUser(Model model) {
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "users.html";
    }

    @PostMapping("/users")
    public String addUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String dateOfBirthHTML,
            Model model
    ) {
        LocalDate dateOfBirth = ParserHTMLDateToJavaLocalDate.parse(dateOfBirthHTML);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        userDao.insert(user);

        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "users.html";
    }

    @GetMapping("/deleteUsers")
    public String viewUserDeleting(Model model) {
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "deleteUsers.html";
    }

    @PostMapping("/deleteUsers")
    public String deleteUserByName(Model model,
            @RequestParam String username
    ){
        User user = userDao.findByName(username);
        userDao.delete(user);

        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "deleteUsers.html";
    }

    @GetMapping("/updateUser")
    public String viewUsersForUpdate(Model model) {
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "updateUser.html";
    }

    @PostMapping("/updateUser")
    public String updateUser(Model model,
                             @RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam(required = false) String dateOfBirthString,
                             @RequestParam String email
    ){

        LocalDate dateOfBirth = ParserHTMLDateToJavaLocalDate.parse(dateOfBirthString);
        userDao.update(id, name, dateOfBirth, email);

        List<User> users = userDao.getAll();
        model.addAttribute("users", users);

        return "updateUser.html";
    }


    @GetMapping("/findUserById/{id}")
    public String findUserById(Model model,
                               @PathVariable long id
        ){
        User user = userDao.getById(id);
        model.addAttribute("user", user);

        return "findUserById.html";
    }



}
