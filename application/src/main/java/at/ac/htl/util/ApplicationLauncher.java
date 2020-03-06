package at.ac.htl.util;

import at.htl.bhif17.demo.Main;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLauncher {
    public void main(@Observes ContainerInitialized event, @Parameters String[] args) {
        Main.start(args);
    }
}
