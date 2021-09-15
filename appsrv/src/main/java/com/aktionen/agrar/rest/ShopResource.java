package com.aktionen.agrar.rest;

import com.aktionen.agrar.dao.ShopDao;
import com.aktionen.agrar.model.Shop;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path("shop")
public class ShopResource {
    @Inject
    ShopDao shopDao;

    @GET
    public List<Shop> all() {
        return shopDao.getAll();
    }

    @GET
    @Path("/{id}")
    public Shop getShop(@PathParam("id") int id) {
        return shopDao.get(id);
    }

    @PUT
    public Response addShop(Shop shop) {
        shopDao.add(shop);
        return Response.ok(shop).status(Response.Status.CREATED).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        var shop = shopDao.get(id);
        shopDao.delete(shop);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
