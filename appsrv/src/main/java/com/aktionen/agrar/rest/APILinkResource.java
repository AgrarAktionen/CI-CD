package com.aktionen.agrar.rest;

import com.aktionen.agrar.dao.APILinkDao;
import com.aktionen.agrar.model.APILink;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path("apiLink")
public class APILinkResource {

    @Inject
    APILinkDao apiLinkDao;

    @GET
    public List<APILink> all() {
        return apiLinkDao.getAll();
    }

    @GET
    @Path("/{id}")
    public APILink getAPILink(@PathParam("id") int id) {
        return apiLinkDao.get(id);
    }

    @PUT
    public Response addAPILink(APILink apiLink) {
        apiLinkDao.add(apiLink);
        return Response.ok(apiLink).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        var apiLink = apiLinkDao.get(id);
        apiLinkDao.delete(apiLink);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
