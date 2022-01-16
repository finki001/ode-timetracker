package fhtw.timetracker;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class NavigationService {

    public void loginSuccessful(final Scene scene) {
        navigate(scene, "record");
    }

    public void logout(final Scene scene) {
        navigate(scene, "login");
    }

    private void navigate(final Scene scene, final String name) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(name + ".fxml")));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
