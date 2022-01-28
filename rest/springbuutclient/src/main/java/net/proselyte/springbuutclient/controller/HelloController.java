package net.proselyte.springbuutclient.controller;

import net.proselyte.springbuutclient.model.User;
import net.proselyte.springbuutclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return "/index";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/usert")
    public String userPage(Model model, Authentication authentication) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        User user = userService.findUserByName(authentication.getName());
        messages.add("Your name" + " " + authentication.getName());
        messages.add("Your role" + " " + authentication.getAuthorities());
        messages.add("Your age" + " " + user.getAge());
        messages.add("Your profession" + " " + user.getProfession());
        model.addAttribute("messages", messages);
        return "/user";
    }
}
