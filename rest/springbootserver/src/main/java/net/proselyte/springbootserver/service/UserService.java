package net.proselyte.springbootserver.service;

import net.proselyte.springbootserver.model.Role;
import net.proselyte.springbootserver.model.User;

import java.util.List;
import java.util.Set;


public interface UserService  {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> listUser();

    Set<Role> getSetOfRole(List<String> id);

    User findUserByName(String userName);

}
