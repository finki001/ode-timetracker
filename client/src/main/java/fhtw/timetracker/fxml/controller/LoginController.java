package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.service.AlertService;
import fhtw.timetracker.service.LoginService;
import fhtw.timetracker.service.StateService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private final LoginService loginService = new LoginService();
    private final NavigationService navigationService = new NavigationService();
    private final AlertService alertService = new AlertService();

    @FXML
    private Button btn_cancelLogin;

    @FXML
    private Button btn_login;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void cancelButtonClicked() {
        navigationService.closeApp();
    }

    @FXML
    void loginButtonClicked() {
        String username = txt_username.getText();
        String password = txt_password.getText();

        if (username.isBlank()) {
            alertService.showAlert("Benutzername fehlt", "Geben Sie einen Benutzernamen ein.");
            return;
        }

        if (password.isBlank()) {
            alertService.showAlert("Passwort fehlt", "Geben Sie ein Passwort ein.");
            return;
        }

        loginService.login(username, password, (success, user, error) -> {
            if (success) {
                navigationService.showOverview(btn_login.getScene());
                StateService.getInstance().setUserId(user.getId());
            } else {
                Platform.runLater(() -> {
                    alertService.showAlert("Falsche Login-Daten", "Bitte überprüfen Sie Benutzername und Passwort.");
                    txt_password.clear();
                });
            }
        });
    }
}
