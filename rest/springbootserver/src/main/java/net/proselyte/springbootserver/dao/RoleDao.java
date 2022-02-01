package net.proselyte.springbootserver.dao;

import net.proselyte.springbootserver.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public List<Role> listRole() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }
}

