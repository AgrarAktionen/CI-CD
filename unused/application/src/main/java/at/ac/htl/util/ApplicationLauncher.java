package at.ac.htl.util;

import at.htl.bhif17.demo.Main;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLauncher {
    public static void main(String[] args) {
        org.jboss.weld.environment.se.StartMain.main(args);
    }
    public void start(@Observes ContainerInitialized event, @Parameters String[] args) {
        Main.start(args);
    }
}
