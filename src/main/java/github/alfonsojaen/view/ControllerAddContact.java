package github.alfonsojaen.view;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ControllerAddContact {
    @FXML
    private TextField tEmail;

    @FXML
    private ComboBox<String> contactList; // ComboBox para mostrar la lista de contactos

    private User loggedInUser;

    public void setUser(User user) {
        this.loggedInUser = user;
        System.out.println("Logged in user: " + loggedInUser.getGmail());
        loadContacts();
    }
    @FXML
    private void handleSelectContact() {
        String selectedContact = contactList.getSelectionModel().getSelectedItem();

        if (selectedContact != null) {
            Utils.ShowAlert("Has seleccionado el contacto: " + selectedContact);
        } else {
            Utils.ShowAlert("Por favor, selecciona un contacto.");
        }
    }

    private void loadContacts() {
        contactList.getItems().clear(); // Limpia cualquier contacto previo

        if (loggedInUser != null && loggedInUser.getContacts() != null) {
            // Agrega los contactos del usuario logueado al ComboBox
            contactList.getItems().addAll(loggedInUser.getContacts());
        } else {
            Utils.ShowAlert("No hay contactos disponibles para este usuario.");
        }
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
