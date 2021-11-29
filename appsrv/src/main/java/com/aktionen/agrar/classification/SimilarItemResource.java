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
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Item> getAlreadyUsedOnce(){
        return similarItemDao.getAlreadyUsed();
    }

    @GET
    @Path("/")
    public List<Item> all() {
        return similarItemDao.getAllUseAbleSimilarItems();
    }
}
