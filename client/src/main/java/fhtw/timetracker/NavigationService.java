package fhtw.timetracker;

import fhtw.timetracker.service.StateService;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class NavigationService {

    public void logout(final Scene scene) {
        StateService.getInstance().reset();
        navigate(scene, "login");
    }

    public void showCreateRecord(Scene scene) {
        navigate(scene, "record");
    }

    public void showOverview(Scene scene) {
        navigate(scene, "overview");
    }

    public void showDetails(Scene scene) {
        navigate(scene, "details");
    }

    private void navigate(final Scene scene, final String name) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(name + ".fxml")));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
