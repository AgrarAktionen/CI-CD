package at.ac.htl.util;

import javafx.util.Callback;
import org.jboss.weld.environment.se.WeldContainer;
import javax.enterprise.inject.Default;

@Default
public class ControllerFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> type) {
        return WeldContainer.current().select(type).get();
    }
}
