package at.htl.bhif17.demo.rest;

import at.htl.bhif17.demo.dao.PersonDao;
import at.htl.bhif17.demo.model.Person;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.List;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class PersonResource {
    @Inject
    PersonDao personDao;

    @GET
    @Path("/")
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
    @GET
    @Path("/download")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadAllPersons() {
        System.out.println("send persons...");

        var home = System.getProperty("user.home");
        var homeDirectory = new File(home);
        var deskTop = new File(homeDirectory, "Desktop");
        var testFile = new File(deskTop, "test.txt");
        var persons = personDao.getAll();
        //TODO: write persons to a testFile as a .csv file and send it as a response.
        return Response.ok(testFile).build();
    }
}
