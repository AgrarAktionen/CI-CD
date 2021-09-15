package com.aktionen.agrar.rest;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.download.CsvDownloader;
import com.aktionen.agrar.model.Item;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.util.List;

@Path("item")
@Produces(MediaType.APPLICATION_JSON)
@Transactional

public class ItemResource {
    @Inject
    CsvDownloader csvDownloader;

    @Inject
    ItemDao itemDao;

    @PUT
    @Path("itemInsert")
    public void insertData() throws FileNotFoundException {
        List<Item> items = csvDownloader.createItemList();
        itemDao.insertAll(items, "Faie");
    }

    @GET
    @Path("/")
    public List<Item> all() {
        return itemDao.getAll();
    }

    @Path("/{id:[0-9]+}")
    @GET
    public Item getItem(@PathParam("id") int id) {
        return itemDao.get(id);
    }
}

