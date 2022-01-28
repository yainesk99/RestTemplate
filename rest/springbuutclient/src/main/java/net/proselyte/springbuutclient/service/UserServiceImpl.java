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
    HttpHeaders headers = new HttpHeaders();

    private final String URL = "http://localhost:8081/";

    @Override
    public void addUser(User user) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URL + "addUser", user, Void.class);
    }

    @Override
    public void updateUser(User user) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URL + "edit", user, Void.class);
    }

    @Override
    public void removeUser(Long id) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URL + "remove/" + id, id, Void.class);
    }

    @Override
    public User getUserById(Long id) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Long> requestEntity = new HttpEntity<>(id, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(URL + "edit/" + id, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<User>() {
        });
        User user = responseEntity.getBody();
        return user;
    }

    @Override
    public List<User> listUser() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(
                        URL + "admin",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<User>>() {
                        }
                );
        return responseEntity.getBody();
    }

    @Override
    public Set<Role> getSetOfRole(List<String> rolesId) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(rolesId, headers);
        ResponseEntity<Set<Role>> responseEntity = restTemplate.exchange(URL + "new", HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Set<Role>>() {
        });
        Set<Role> roleSet = responseEntity.getBody();
        return roleSet;
    }

    @Override
    public User findUserByName(String username) {
        return getUserByName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByName = getUserByName(username);
        return new MyUserDetails(userByName, userByName.getRoles());
    }

    public User getUserByName(String username) {
        headers.set("username", username);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<User> response = restTemplate.exchange(
                URL + "user", HttpMethod.GET, requestEntity, User.class, username);
        return response.getBody();
    }
}
