package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.thebestsoft.webvpn.services.ControlService.Answer;
import ru.thebestsoft.webvpn.services.ControlService.PptpdService;
import ru.thebestsoft.webvpn.services.UserService;
import ru.thebestsoft.webvpn.services.user.User;

@Controller
@RequestMapping("/")
public class indexControl {

    @GetMapping("/")
    public String doGet (Model model) {

        UserService userService = new UserService();
        PptpdService pptpdService = new PptpdService();
        Answer answer = pptpdService.getInfo();
        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("serverStatusError", answer.getError());
        model.addAttribute("serverStatusAnswer", answer.getAnswer());
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/error")
    public String doError () {
        return "redirect:/";
    }

}
