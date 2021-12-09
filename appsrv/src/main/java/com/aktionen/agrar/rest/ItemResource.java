package com.aktionen.agrar.rest;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.download.CsvDownloader;
import com.aktionen.agrar.model.Item;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("item")
@Produces(MediaType.APPLICATION_JSON)
@Transactional

public class ItemResource {
    @Inject
    CsvDownloader csvDownloader;

    @Inject
    ItemDao itemDao;


    @GET
    @Path("/")
    public List<Item> all() {
        return itemDao.getAll();
    }

    @GET
    @Path("/inserted")
    public List<Item> getAllInserted() {
        return itemDao.getAllInserted();
    }

    @Path("/{id:[0-9]+}")
    @GET
    public Item getItem(@PathParam("id") int id) {
        return itemDao.get(id);
    }
}

