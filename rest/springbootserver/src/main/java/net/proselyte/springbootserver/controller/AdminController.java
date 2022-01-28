package net.proselyte.springbootserver.controller;

import net.proselyte.springbootserver.model.Role;
import net.proselyte.springbootserver.model.User;
import net.proselyte.springbootserver.service.RoleService;
import net.proselyte.springbootserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin")
    public ResponseEntity<List<User>> users() {
        List userList= userService.listUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<List<Role>> newUserForm() {
        List roles = roleService.listRole();
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Set<Role>> setOfRole(@RequestBody List<String> value){
        Set<Role> roles = userService.getSetOfRole(value);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser( @RequestBody User user){
         userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<User> edit(@PathVariable("id") @RequestBody Long id ) {
     User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> removeUser(@PathVariable("id") @RequestBody Long id) {
       userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
