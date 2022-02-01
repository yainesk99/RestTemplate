package net.proselyte.springbuutclient.service;


import net.proselyte.springbuutclient.model.MyUserDetails;
import net.proselyte.springbuutclient.model.Role;
import net.proselyte.springbuutclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders headers;

    private final String URL = "http://localhost:8081/";

    @Override
    public void addUser(User user) {
        restTemplate.postForEntity(URL + "addUser", user, Void.class);
    }

    @Override
    public void updateUser(User user) {
        restTemplate.postForEntity(URL + "edit", user, Void.class);
    }

    @Override
    public void removeUser(Long id) {
        restTemplate.postForEntity(URL + "remove/" + id, id, Void.class);
    }

    @Override
    public User getUserById(Long id) {
        HttpEntity<Long> requestEntity = new HttpEntity<>(id, headers);
        return restTemplate.exchange(URL + "edit/" + id, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<User>() {
        }).getBody();
    }

    @Override
    public List<User> listUser() {
        return restTemplate.exchange(
                URL + "admin",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        ).getBody();
    }

    @Override
    public Set<Role> getSetOfRole(List<String> rolesId) {
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(rolesId, headers);
        return restTemplate.exchange(URL + "new", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Set<Role>>() {
        }).getBody();
    }

    @Override
    public User findUserByName(String username) {
        return getUserByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByName = getUserByName(username);
        return new MyUserDetails(getUserByName(username), userByName.getRoles());
    }

    public User getUserByName(String username) {
        headers.set("username", username);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                URL + "user", HttpMethod.GET, requestEntity, User.class, username).getBody();
    }
}
