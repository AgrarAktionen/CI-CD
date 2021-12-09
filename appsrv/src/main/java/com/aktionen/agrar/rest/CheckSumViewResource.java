package com.aktionen.agrar.rest;


import com.aktionen.agrar.dao.CheckSumDao;
import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.model.CheckSum;
import com.aktionen.agrar.model.Item;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Transactional
@Path("/checksum/view")
public class CheckSumViewResource {


    @Inject
    Template checksumView;

    @Inject
    CheckSumDao checkSumDao;

    @GET
    public TemplateInstance templateInstance() {
        List<CheckSum> checkSums = checkSumDao.getAll();
        return checksumView.data("checkSums", checkSums);
    }
}

