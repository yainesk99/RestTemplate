package net.proselyte.springbootserver.controller;

import net.proselyte.springbootserver.model.Role;
import net.proselyte.springbootserver.model.User;
import net.proselyte.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public ResponseEntity<?> printWelcome(@RequestBody ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        model.addAttribute("messages", messages);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> loginPage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> userPage(@RequestHeader(name = "userName", required = false) String userName){
        User user = userService.findUserByName(userName);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
