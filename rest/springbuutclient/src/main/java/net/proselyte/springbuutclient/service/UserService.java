package net.proselyte.springbuutclient.service;

import net.proselyte.springbuutclient.model.Role;
import net.proselyte.springbuutclient.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;


public interface UserService extends UserDetailsService {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> listUser();

    Set<Role> getSetOfRole(List<String> id);

    User findUserByName(String userName);

}
