package com.aktionen.agrar.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
    @ApplicationPath("api")
    @OpenAPIDefinition(
            tags = {
                    @Tag(name="JPA", description="Java Persistence API"),
                    @Tag(name="REST", description="Representational State Transfer")
            },
            info = @Info(
                    title="JPA/REST Tutorial",
                    version = "0.0.5",
                    contact = @Contact(
                            name = "Agrar Aktionen Mobile",
                            url = "https://student.cloud.htl-leonding.ac.at/20170011",
                            email = "20170011@students.htl-perg.ac.at"),
                    license = @License(
                            name = "Apache 2.0",
                            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
    )
    public class ApplicationResource extends Application {

}
