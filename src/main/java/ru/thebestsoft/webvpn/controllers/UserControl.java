package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.thebestsoft.webvpn.services.UserService;
import ru.thebestsoft.webvpn.services.user.User;

import java.io.IOException;

@Controller
@RequestMapping("/user/")
public class UserControl {

    @GetMapping("/add/")
    public String doGetAdd (Model model) {

        model.addAttribute("user",new User());
        return "user-add";
    }

    @PostMapping("/add/")
    public String doPostAdd (@ModelAttribute User user, Model model) {
        UserService userService = null;
        try {
            userService = new UserService();
            userService.addUser(user);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping(path = "/del/")
    public String doGetDel (@RequestParam(name = "login") String login, Model model ) {
        System.out.println("delete");
            UserService userService = new UserService();
            userService.delUser(login);
            model.addAttribute("userList", userService.getUserList());
        return "redirect:/";
    }
}
