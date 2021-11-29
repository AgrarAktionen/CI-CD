package com.aktionen.agrar.users.dao;


import com.aktionen.agrar.users.model.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.LinkedList;
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
    public List<User> getUserByEmailAndPassword(User user) {
        List<User> userList = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        List<User> loggedInUserList  = new LinkedList<>();

        for(User searchForUser:userList){
            if(searchForUser.getPassword().equals(user.getPassword()) && searchForUser.getEmail().equals(user.getEmail())){
                searchForUser.setLoggedIn(true);
                loggedInUserList.add(searchForUser);
                System.out.println("LoggedIn!");
            }
        }
        return loggedInUserList;
    }
    public void delete(User user) {
        em.remove(user);
   }

    public List<User> logout(User user) {
        List<User> userList = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        List<User> loggedInUserList  = new LinkedList<>();
        for(User searchForUser:userList){

            if(searchForUser.equals(user)){
                if(searchForUser.isLoggedIn()){
                    searchForUser.setLoggedIn(false);
                    loggedInUserList.add(searchForUser);
                    System.out.println("Logged out!");
                }
            }
        }

        return loggedInUserList;
    }


}