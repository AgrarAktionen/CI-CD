package at.htl.bhif17.demo.rest;

import at.htl.bhif17.demo.dao.PersonDao;
import at.htl.bhif17.demo.model.Person;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
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
    @PUT
    public Response addPerson(Person person) {
        //var school = schoolDao.findById(person.getSchool().getId());
        //person.setSchool(school);
        personDao.save(person);
        return Response.ok(person).status(Response.Status.CREATED).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        var person = personDao.get(id);
        personDao.remove(person);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
