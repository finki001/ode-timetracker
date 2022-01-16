package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TimeTrackerClientController {

    @FXML
    private Button button;

    private final LoginService loginService = new LoginService();
    private final NetworkService networkService = new NetworkService();

    @FXML
    void buttonClicked() {
        // example login (should work with demo data)
        // is making use of the NetworkCallback + lambda expression -> makes code more readable at the higher level, which is to be desired IMHO
        loginService.login("elayoe", "elayoe123", (success, user, error) -> {
            System.out.println("login callback");
            System.out.println("==========================");
            System.out.println("Success? " + success);
            System.out.println("User: " + user);
            System.out.println("Error: " + (error != null ? error.toString() : null));
            System.out.println("==========================\n");

            // modifies the button text -> can't be run in this thread, but has to be enqueued to the main JavaFX loop
            // Platform.runLater does exactly this, it runs "later" but not visible later
            if (user != null) {
                Platform.runLater(() -> button.setText("Hallo, " + user.getFirstname() + " " + user.getLastname()));
            }


            // this is how you make a nested call -> shouldn't really be needed, but be careful with variable naming of the callback parameters
            // -> they have to be different
            networkService.loadAllUsers((loadUsersSuccess, users, loadUsersError) -> {
                System.out.println("loadAllUsers callback");
                System.out.println("==========================");
                System.out.println("Success? " + loadUsersSuccess);
                System.out.println("User: " + users);
                System.out.println("Error: " + (loadUsersError != null ? loadUsersError.toString() : null));
                System.out.println("\n==========================");
            });
        });
    }
}
