package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.network.AuthenticationInterceptor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class NavigationController {

    @FXML
    private BorderPane content;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuLogout;

    @FXML
    private MenuItem menuRecord;

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void createRecord(ActionEvent event) {
        try {
            FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout() {
        AuthenticationInterceptor.authHeader = "";

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
            content.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
