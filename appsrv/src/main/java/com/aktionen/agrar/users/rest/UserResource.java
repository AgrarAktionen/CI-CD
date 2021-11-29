package com.aktionen.agrar.users.rest;

import com.aktionen.agrar.users.dao.UserDao;
import com.aktionen.agrar.users.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    UserDao userDao;

    @GET
    public List<User> all() {
        return userDao.getAll();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) {
        return userDao.get(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        userDao.update(user);
        return Response.ok(user).status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userDao.add(user);
        return Response.ok(user).status(Response.Status.CREATED).build();
    }

    @Path("/getUserByEmailAndPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> getUserByEmailAndPassword(User user) {
        return userDao.getUserByEmailAndPassword(user);
    }

    /*
    @Path("/logout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> logout(String user) {
        User loggedIn = new User();
        String userloggedIn = user;
        //loggedIn.setLoggedIn();
        return userDao.logout(loggedIn);
    }



     */


    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        var user = userDao.get(id);
        userDao.delete(user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}