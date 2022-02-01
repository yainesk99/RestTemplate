package net.proselyte.springbuutclient.service;

//import net.proselyte.springbuutclient.dao.RoleDao;
//import net.proselyte.springbootserver.model.Role;
import net.proselyte.springbuutclient.model.Role;
import net.proselyte.springbuutclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8081/";

    public List<Role> listRole() {
        return restTemplate.exchange(
                URL + "new",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }
        ).getBody();
    }
}

