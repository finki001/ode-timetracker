package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.service.AlertService;
import fhtw.timetracker.service.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUserController {

    private final LoginService loginService = new LoginService();
    private final NavigationService navigationService = new NavigationService();
    private final AlertService alertService = new AlertService();

    @FXML
    private TextField text_firstname;

    @FXML
    private TextField text_lastname;

    @FXML
    private PasswordField text_password;

    @FXML
    private TextField text_username;

    @FXML
    void createUser() {
        loginService.createUser(text_username.getText().trim(),
                text_firstname.getText().trim(),
                text_lastname.getText().trim(),
                text_password.getText(),
                (success, response, error) -> {
                    if (success) {
                        navigationService.showLogin(text_username.getScene());
                    } else {
                        alertService.showAlert("Fehler", "Benutzer konnte nicht erstellt werden: " + (error != null ? error.getLocalizedMessage() : null));
                    }
                }
        );
    }

    @FXML
    void goToLogin() {
        navigationService.showLogin(text_username.getScene());
    }
}
