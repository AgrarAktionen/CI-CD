package at.ac.htlperg.rest;

import at.ac.htlperg.syp.dao.SchoolDao;
import at.ac.htlperg.syp.model.School;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/school")
@Produces(MediaType.APPLICATION_JSON)
public class SchoolEndpoint {
    @Inject
    SchoolDao dao;
    @GET
    public List<School> schools() {
        return dao.getSchools();
    }
}
