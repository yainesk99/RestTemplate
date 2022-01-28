package net.proselyte.springbootserver.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "SEQUENCE_USERS")
    private Long id;

    @Column
    private String username;

    @Column
    private int age;

    @Column
    private String profession;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<net.proselyte.springbootserver.model.Role> roles = new HashSet<>();

    public User() {
    }

    public User(String userName, int age, String profession, Set<net.proselyte.springbootserver.model.Role> roles) {
        this.username = userName;
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

    public Set<net.proselyte.springbootserver.model.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<net.proselyte.springbootserver.model.Role> roles) {
        this.roles = roles;
    }
}
