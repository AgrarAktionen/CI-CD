package com.aktionen.agrar.rest;


import com.aktionen.agrar.dao.CheckSumDao;
import com.aktionen.agrar.model.CheckSum;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Transactional
@Path("/checksum")
@Produces(MediaType.APPLICATION_JSON)
public class CheckSumResource {


    @Inject
    CheckSumDao checkSumDao;

    @GET
    @Path("/")
    public List<CheckSum> all() {
        return checkSumDao.getAll();
    }

    @Path("/{id:[0-9]+}")
    @GET
    public CheckSum getItem(@PathParam("id") int id) {
        return checkSumDao.get(id);
    }
    @GET
    @Path("/csv/{id}")
    @Produces("application/csv")
    public Response getFileInPDFFormat(@PathParam("id") int id)
    {

        //Put some validations here such as invalid file name or missing file name
        if(id == 0)
        {
            Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
            return response.build();
        }

        CheckSum currentChecksum = checkSumDao.getFileById(id);
        byte[] fileByteArray = currentChecksum.getCsvFile();

        //Filename builder
        String filename = currentChecksum.getCheckSum()+".csv";

        Response.ResponseBuilder response = Response.ok((Object) fileByteArray);
        response.header("Content-Disposition", "attachment; filename="+filename);
        return response.build();
    }
}

