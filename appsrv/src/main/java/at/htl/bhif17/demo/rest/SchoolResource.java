package at.htl.bhif17.demo.rest;

import at.htl.bhif17.demo.dao.SchoolDao;
import at.htl.bhif17.demo.model.School;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("school")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class SchoolResource {
    @Inject SchoolDao schoolDao;

    @GET
    @Path("/")
    public List<School> all() {
        return schoolDao.all();
    }
    @Path("/{id:[0-9]+}")
    @GET
    public School getSchool(@PathParam("id") int id) {
        return schoolDao.findById(id);
    }
}
