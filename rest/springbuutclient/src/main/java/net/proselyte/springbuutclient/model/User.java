package net.proselyte.springbuutclient.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;

    private String username;

    private int age;

    private String profession;

    private String password;

    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, int age, String profession, Set<net.proselyte.springbuutclient.model.Role> roles) {
        this.username = username;
        this.age = age;
        this.profession = profession;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
