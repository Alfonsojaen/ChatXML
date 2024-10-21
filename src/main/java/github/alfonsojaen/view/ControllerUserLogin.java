package github.alfonsojaen.view;

import java.io.IOException;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.singleton.UserSession;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerUserLogin {
    @FXML
    private TextField tGmail;

    @FXML
    private PasswordField tPass;

    /**
     * Handles the user login process.
     * Retrieves the entered Gmail and password, validates them,
     * and logs the user in if the credentials are correct.
     * Displays appropriate alerts for successful or failed login attempts.
     */
    @FXML
    private void login() throws IOException {
        String gmail = tGmail.getText().trim();
        String password = tPass.getText().trim();

        if (gmail.isEmpty() || password.isEmpty()) {
            Utils.ShowAlert("Falta algún campo por introducir");
        } else {
            UserManager userDAO = new UserManager();
            String nameUser;
            if ((nameUser = userDAO.checkLogin(gmail, password)) != null) {
                UserSession.login(gmail, nameUser);
                Utils.ShowAlert("Login exitoso, se ha logeado el usuario correctamente.");
                switchToUserPage();
            } else {
                UserSession.logout();
                Utils.ShowAlert("No se ha podido logear, inténtelo de nuevo.");
            }
        }
    }
    @FXML
    private void switchToUserPage() throws IOException {
        Scenes.setRoot("secondary");
    }
}
