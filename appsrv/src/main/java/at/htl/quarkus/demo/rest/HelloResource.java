package at.htl.quarkus.demo.rest;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("hello")
@Produces(MediaType.TEXT_HTML)
public class HelloResource {
    @Inject
    Template hello;

    @GET
    public TemplateInstance hello(@QueryParam("name") String name) {
        return hello.data("name", name);
    }
}
