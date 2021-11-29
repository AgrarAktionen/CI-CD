package com.aktionen.agrar.categorize.rest;

import com.aktionen.agrar.categorize.dao.CategoriesDao;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.users.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Path("/categories")
public class CategoriesResource {

    @Inject
    CategoriesDao categoriesDao;

    @Path("/getAll")
    @GET
    public List<Item> all(){
        return categoriesDao.selectAll();
    }

    //-------------this functions provide possibility to get the available categories with the right sub categories--------------//
    //Now it is possible to get the right set of sub categories form a category.

    @Path("/getPrimeCategories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getPrimeCategories(){

        return categoriesDao.getPrimeCategories();
    }
    @Path("/getSecondCategories/{primeCategory}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getSecondCategories(@PathParam("primeCategory") String primeCategory){

        return categoriesDao.getSecondCategories(primeCategory);
    }
    @Path("/getThirdCategories/{primeCategory}/{secondCategory}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getThirdCategories(@PathParam("primeCategory") String primeCategory, @PathParam("secondCategory") String secondCategory){

        return categoriesDao.getThirdCategories(primeCategory, secondCategory);
    }
    @Path("/getFourthCategories/{primeCategory}/{secondCategory}/{thirdCategory}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getFourthCategories(@PathParam("primeCategory") String primeCategory, @PathParam("secondCategory") String secondCategory, @PathParam("thirdCategory") String thirdCategory){

        return categoriesDao.getFourthCategories(primeCategory, secondCategory, thirdCategory);
    }

    //-------------------------------this functions are returning the ITEMs of the selected categories-------------------------------//
    @GET
    @Path("/{primeCategory}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    public List<Item> getPrimeSelection(@PathParam("primeCategory") String primeCategory) {
        return categoriesDao.getPrime(primeCategory);
    }
    @GET
    @Path("/{primeCategory}/{secondCategory}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    public List<Item> getSecondSelection(@PathParam("primeCategory") String primeCategory, @PathParam("secondCategory") String secondCategory) {
        return categoriesDao.getSecond(primeCategory, secondCategory);
    }
    @GET
    @Path("/{primeCategory}/{secondCategory}/{thirdCategory}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    public List<Item> getThirdSelection(@PathParam("primeCategory") String primeCategory, @PathParam("secondCategory") String secondCategory, @PathParam("thirdCategory") String thirdCategory) {
        return categoriesDao.getThird(primeCategory, secondCategory, thirdCategory);
    }

    @GET
    @Path("/{primeCategory}/{secondCategory}/{thirdCategory}/{fourthCategory}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional

    public List<Item> getFourthSelection(@PathParam("primeCategory") String primeCategory, @PathParam("secondCategory") String secondCategory, @PathParam("thirdCategory") String thirdCategory, @PathParam("fourthCategory") String fourthCategory) {
        return categoriesDao.getFourth(primeCategory, secondCategory, thirdCategory, fourthCategory);
    }


}
