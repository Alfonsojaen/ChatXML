package github.alfonsojaen.view;

import java.io.IOException;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;
import github.alfonsojaen.singleton.UserSession;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Clase ControllerUserLogin que maneja la lógica para el inicio de sesión del usuario.
 * Permite validar las credenciales del usuario y navegar a la pantalla de chat o registro.
 * @author Alfonso Jaen
 */
public class ControllerUserLogin {

    /** Campo de texto para introducir el correo electrónico del usuario. */
    @FXML
    private TextField tGmail;

    /** Campo de texto para introducir la contraseña del usuario. */
    @FXML
    private PasswordField tPass;

    /**
     * Método que maneja el proceso de inicio de sesión.
     * Valida las credenciales del usuario y navega a la pantalla de chat si son correctas.
     *
     * @throws IOException Excepción lanzada si hay un error al cambiar de pantalla.
     */
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
                Scenes.setRoot("pantallaChat", loggedInUser);
            } else {
                UserSession.logout();
                Utils.ShowAlert("No se ha podido logear, inténtelo de nuevo.");
            }
        }
    }

    /**
     * Método que cambia la vista a la página de registro de usuario.
     *
     * @throws IOException Excepción lanzada si hay un error al cambiar de pantalla.
     */
    @FXML
    private void switchToUserPage() throws IOException {
        Scenes.setRoot("pantallaRegisterUser", null);
    }
}
