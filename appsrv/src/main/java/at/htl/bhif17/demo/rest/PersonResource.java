package at.htl.bhif17.demo.rest;

import at.htl.bhif17.demo.dao.PersonDao;
import at.htl.bhif17.demo.model.Person;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    @Inject
    PersonDao personDao;
    @GET
    public List<Person> all() {
        return personDao.getAll();
    }
    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") int id) {
        return personDao.get(id);
    }
}
