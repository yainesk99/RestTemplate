package net.proselyte.springbootserver.service;

import net.proselyte.springbootserver.dao.UserDao;
import net.proselyte.springbootserver.model.Role;
import net.proselyte.springbootserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements net.proselyte.springbootserver.service.UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;

    @Autowired
    public void setUserDaoAndEncoder(UserDao userDao,
                                     RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public Set<Role> getSetOfRole(List<String> rolesId) {
        Set<Role> roleSet = new HashSet<>();
        for (String id : rolesId) {
            roleSet.add(roleService.getRoleById(Long.parseLong(id)));
        }
        return roleSet;
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

}
