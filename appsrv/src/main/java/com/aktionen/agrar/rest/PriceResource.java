package com.aktionen.agrar.rest;



import com.aktionen.agrar.dao.PriceDao;
import com.aktionen.agrar.download.CsvDownloader;
import com.aktionen.agrar.model.Price;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path("price")
public class PriceResource {

    @Inject
    CsvDownloader csvDownloader;

    @Inject
    PriceDao priceDao;

    @PUT
    @Path("priceInsert")
    public void insertDatas() throws FileNotFoundException {
        List<Price> prices = csvDownloader.createPriceList();
        priceDao.insertAll(prices);
    }

    @GET
    public List<Price> all() {
        List<Price> price = priceDao.getAll();
        return priceDao.getAll();
    }
    @GET
    @Path("/{id}")
    public Price getPrice(@PathParam("id") int id) {
        return priceDao.get(id);
    }

    @PUT
    public Response addPrice(Price price) {
        priceDao.add(price);
        return Response.ok(price).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        var price = priceDao.get(id);
        priceDao.delete(price);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
