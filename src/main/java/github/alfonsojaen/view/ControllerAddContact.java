package github.alfonsojaen.view;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControllerAddContact {
    @FXML
    private TextField tEmail;

    private User loggedInUser;

    public void setUser(User user) {
        this.loggedInUser = user;
        System.out.println("Logged in user: " + loggedInUser.getGmail());
    }

    @FXML
    private void handleAddContact() {
        String emailToAdd = tEmail.getText().trim();

        if (emailToAdd.isEmpty()) {
            Utils.ShowAlert("Por favor, introduce un correo electrónico.");
            return;
        }

        UserManager userManager = new UserManager();

        User userToAdd = userManager.findUserByEmail(emailToAdd);
        if (userToAdd != null) {
            loggedInUser.addContact(emailToAdd);
            userManager.addContactToUser(loggedInUser.getGmail(), emailToAdd);
            Utils.ShowAlert("Contacto añadido: " + emailToAdd);
            tEmail.clear();
        } else {
            Utils.ShowAlert("No hay usuario registrado con el correo: " + emailToAdd);
        }
    }
}
