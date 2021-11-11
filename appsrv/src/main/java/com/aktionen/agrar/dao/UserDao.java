package com.aktionen.agrar.dao;


import com.aktionen.agrar.model.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named
@Dependent
public class UserDao {
    @Inject
    EntityManager em;

    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
    public User get(int id) {
        return em.find(User.class, id);
    }
    public User update(User user) {
        return em.merge(user);
    }
    public void add(User user) {
        em.persist(user);
        em.flush();
    }
    public void delete(User user) {
        em.remove(user);
    }
}
