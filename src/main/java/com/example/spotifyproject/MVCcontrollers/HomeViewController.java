package com.example.spotifyproject.MVCcontrollers;

import com.example.spotifyproject.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeViewController {
    private final UserDao userDao;

    @Autowired
    public HomeViewController(UserDao userDao) {
        this.userDao = userDao;
    }

@RequestMapping("/home")
public String home(Model page,
                   @RequestParam(required= false) Long id,
                   @RequestParam(required = false) String color) {
        if(id == null){
            page.addAttribute("username", "New User" );
            page.addAttribute("color", "green");
        }else {
            page.addAttribute("username", userDao.getById(id).getName());
            page.addAttribute("color", color);
        }
    return "home.html";
}
}
