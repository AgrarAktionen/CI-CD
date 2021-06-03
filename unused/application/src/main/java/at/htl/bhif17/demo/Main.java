package at.htl.bhif17.demo;

import at.ac.htl.util.ControllerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("app.fxml"));
        loader.setControllerFactory(new ControllerFactory());
        Parent root = loader.load();
        primaryStage.setTitle("JPA Demo");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
    public static void main(String[] args) {
        throw new RuntimeException("don't start that, use ApplicationLauncher!");
    }
    public static void start(String[] args) {
        launch(args);
    }
}
