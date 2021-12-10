package com.aktionen.agrar.classification;

import com.aktionen.agrar.dao.SimilarItemDao;
import com.aktionen.agrar.model.Item;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path("/similarItems")
public class SimilarItemResource {

    @Inject
    SimilarItemDao similarItemDao;


    @GET
    @Path("/getAlreadyUsed")
    public List<Item> getAlreadyUsedOnce(){
        return similarItemDao.getAlreadyUsed();
    }

    @GET
    @Path("/getAll")
    public List<Item> all() {
        return similarItemDao.getAllUseAbleSimilarItems();
    }


    //TODO: create a getAllFromUser() function to get just the single uploaded current picture from the logged in account

}
