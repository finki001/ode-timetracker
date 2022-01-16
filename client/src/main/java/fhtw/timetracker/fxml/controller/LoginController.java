package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private final LoginService loginService = new LoginService();
    private final NavigationService navigationService = new NavigationService();

    @FXML
    private Button btn_cancelLogin;

    @FXML
    private Button btn_login;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void cancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void loginButtonClicked(ActionEvent event) {

        // when login successful:
        navigationService.loginSuccessful();
    }
}
