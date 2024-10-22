package github.alfonsojaen.view;

import github.alfonsojaen.dao.MessageManager;
import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.Message;
import github.alfonsojaen.model.User;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerAddContact {
    @FXML
    private TextField tEmail;
    @FXML
    private TextField newMessage;
    @FXML
    private ComboBox<String> contactList;
    @FXML
    private TextArea chatHistory;

    @FXML
    private User loggedInUser;
    private User selectedContact;
    private MessageManager messageManager;

    public ControllerAddContact() {
        this.messageManager = new MessageManager();
    }

    public void setUser(User user) {
        this.loggedInUser = user;
        System.out.println("Logged in user: " + loggedInUser.getGmail());
        loadContacts();
    }
    @FXML
    private void handleSelectContact() {
        String selectedContactEmail = contactList.getSelectionModel().getSelectedItem();

        if (selectedContactEmail != null) {
            UserManager userManager = new UserManager();
            selectedContact = userManager.findUserByEmail(selectedContactEmail);

            if (selectedContact != null) {
                Utils.ShowAlert("Has seleccionado el contacto: " + selectedContactEmail);
                loadChatHistory();
            } else {
                Utils.ShowAlert("No se pudo encontrar el contacto seleccionado.");
            }
        } else {
            Utils.ShowAlert("Por favor, selecciona un contacto.");
        }
    }
    private void loadChatHistory() {
        chatHistory.clear();
        List<Message> conversation = messageManager.getMessages().stream()
                .filter(msg -> (msg.getSender().equals(loggedInUser.getGmail()) && msg.getRecipient().equals(selectedContact.getGmail())) ||
                        (msg.getSender().equals(selectedContact.getGmail()) && msg.getRecipient().equals(loggedInUser.getGmail())))
                .collect(Collectors.toList());

        for (Message message : conversation) {
            chatHistory.appendText(message.getSender() + ": " + message.getContent() + "\n");
        }
    }
    private void loadContacts() {
        contactList.getItems().clear();

        if (loggedInUser != null && loggedInUser.getContacts() != null) {
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
            loadContacts();
        } else {
            Utils.ShowAlert("No hay usuario registrado con el correo: " + emailToAdd);
        }
    }
    @FXML
    private void handleSendMessage() {
        if (selectedContact == null) {
            Utils.ShowAlert("Por favor, selecciona un contacto antes de enviar un mensaje.");
            return;
        }

        String messageContent = newMessage.getText().trim();
        if (messageContent.isEmpty()) {
            Utils.ShowAlert("Por favor, escribe un mensaje antes de enviarlo.");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Message newMessageObject = new Message(loggedInUser.getGmail(), selectedContact.getGmail(), messageContent, timestamp);
        messageManager.sendMessage(newMessageObject);
        loadChatHistory();
        newMessage.clear();
    }

    @FXML
    private void handleExportConversation() {
        if (selectedContact == null) {
            Utils.ShowAlert("Por favor, selecciona un contacto para exportar la conversación.");
            return;
        }

        List<Message> conversation = messageManager.getMessages().stream()
                .filter(msg -> (msg.getSender().equals(loggedInUser.getGmail()) && msg.getRecipient().equals(selectedContact.getGmail())) ||
                        (msg.getSender().equals(selectedContact.getGmail()) && msg.getRecipient().equals(loggedInUser.getGmail())))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Message message : conversation) {
            sb.append(message.getSender()).append(": ").append(message.getContent()).append("\n");
        }

        Utils.saveToFile("conversacion.txt", sb.toString());
        Utils.ShowAlert("Conversación exportada a conversacion.txt");
    }
}
