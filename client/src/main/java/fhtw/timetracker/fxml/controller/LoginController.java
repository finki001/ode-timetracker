package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.network.NetworkCallback;
import fhtw.timetracker.service.LoginService;
import javafx.application.Platform;
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
        String username = txt_username.getText();
        String password = txt_password.getText();

        if (username.isBlank()) {
            txt_username.setPromptText("Field must not be empty");
        } else if (password.isBlank()) {
            txt_password.setPromptText("Field must not be empty");
        } else {
            loginService.login(username, password, (success, response, error) -> {
                if (success) {
                    navigationService.loginSuccessful(btn_login.getScene());
                } else {
                    Platform.runLater(() -> {
                        txt_username.clear();
                        txt_username.setPromptText("User must exist");
                        txt_password.clear();
                        txt_password.setPromptText("Password must be valid");
                    });
                }
            });
        }
    }
}
