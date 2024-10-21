package github.alfonsojaen.view;

import java.io.IOException;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;
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

    @FXML
    private void login() throws IOException {
        String gmail = tGmail.getText().trim();
        String password = tPass.getText().trim();

        if (gmail.isEmpty() || password.isEmpty()) {
            Utils.ShowAlert("Falta algún campo por introducir");
        } else {
            UserManager userDAO = new UserManager();
            User loggedInUser = userDAO.checkLogin(gmail, password);

            if (loggedInUser != null) {
                UserSession.login(gmail, password);
                Utils.ShowAlert("Login exitoso, se ha logeado el usuario correctamente.");
                Scenes.setRoot("pantallaAddContact",loggedInUser);
            } else {
                UserSession.logout();
                Utils.ShowAlert("No se ha podido logear, inténtelo de nuevo.");
            }
        }
    }
    @FXML
    private void switchToUserPage() throws IOException {
        Scenes.setRoot("pantallaRegisterUser", null);
    }

}
