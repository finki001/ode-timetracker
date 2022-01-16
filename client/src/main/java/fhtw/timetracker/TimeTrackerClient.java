package fhtw.timetracker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * This is the entry point of the application, can be started with via the Client run-configuration in IntelliJ IDEA
 * (or via `./gradlew client:run`)
 */
public class TimeTrackerClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.setTitle("TimeTracker Client");
        primaryStage.show();
    }
}
