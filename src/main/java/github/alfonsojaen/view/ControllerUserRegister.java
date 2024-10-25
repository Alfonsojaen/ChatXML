package github.alfonsojaen.view;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Clase ControllerUserRegister que gestiona el registro de nuevos usuarios.
 * Permite recoger información del usuario y validar antes de crear una nueva cuenta.
 * @author Alfonso Jaen
 */
public class ControllerUserRegister {

    /** Campo de texto para introducir el nombre completo del usuario. */
    @FXML
    private TextField tFullName;

    /** Campo de texto para introducir el correo electrónico del usuario. */
    @FXML
    private TextField tGmail;

    /** Campo de texto para introducir el nombre de usuario. */
    @FXML
    private TextField tUsername;

    /** Campo de texto para introducir la contraseña del usuario. */
    @FXML
    private PasswordField tPass;

    /** Campo de texto para introducir la edad del usuario. */
    @FXML
    private TextField tAge;

    /**
     * Método que maneja el proceso de registro de un nuevo usuario.
     * Valida la información ingresada y crea una nueva cuenta si es válida.
     *
     * @throws IOException Excepción lanzada si hay un error al cambiar de pantalla.
     */
    @FXML
    private void register() throws IOException {
        String fullName = tFullName.getText().trim();
        String gmail = tGmail.getText().trim();
        String username = tUsername.getText().trim();
        String password = tPass.getText().trim();
        String ageStr = tAge.getText().trim();

        if (fullName.isEmpty() || gmail.isEmpty() || username.isEmpty() || password.isEmpty() || ageStr.isEmpty()) {
            Utils.ShowAlert("Falta algún campo por introducir");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 0) {
                Utils.ShowAlert("La edad debe ser un número positivo");
                return;
            }
        } catch (NumberFormatException e) {
            Utils.ShowAlert("La edad debe ser un número válido");
            return;
        }

        UserManager userManager = new UserManager();
        boolean emailExists = false;

        for (User existingUser : userManager.getUsers()) {
            if (existingUser.getGmail() != null && existingUser.getGmail().equals(gmail)) {
                emailExists = true;
                break;
            }
        }

        if (emailExists) {
            Utils.ShowAlert("El correo electrónico ya está en uso. Por favor, utiliza otro.");
            return;
        }

        User newUser = new User(username, password, gmail, fullName, age);

        if (userManager.addUser(newUser)) {
            Utils.ShowAlert("Registro exitoso, el usuario ha sido creado.");
            switchToLoginPage();
        } else {
            Utils.ShowAlert("No se pudo registrar el usuario. Tal vez el correo o nombre de usuario ya están en uso.");
        }
    }

    /**
     * Método que cambia la vista a la página de inicio de sesión de usuario.
     *
     * @throws IOException Excepción lanzada si hay un error al cambiar de pantalla.
     */
    @FXML
    private void switchToLoginPage() throws IOException {
        Scenes.setRoot("pantallaLoginUser", null);
    }
}