package github.alfonsojaen.view;

import github.alfonsojaen.dao.MessageManager;
import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.Message;
import github.alfonsojaen.model.User;
import github.alfonsojaen.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase ControllerChat que maneja la lógica de la interfaz de chat.
 * Permite enviar y recibir mensajes, gestionar contactos y generar informes de conversación.
 *
 * Author: Alfonso Jaen
 */
public class ControllerChat {

    /** Campo de texto para ingresar el correo electrónico del contacto. */
    @FXML
    private TextField tEmail;

    /** Campo de texto para ingresar un nuevo mensaje. */
    @FXML
    private TextField newMessage;

    /** ComboBox para seleccionar un contacto de la lista. */
    @FXML
    private ComboBox<String> contactList;

    /** Área de texto para mostrar el historial de chat. */
    @FXML
    private TextFlow chatHistory;

    /** Usuario que ha iniciado sesión. */
    @FXML
    private User loggedInUser;

    /** Contacto seleccionado para chatear. */
    private User selectedContact;

    /** Gestor de mensajes para manejar el envío y recepción de mensajes. */
    private MessageManager messageManager;

    /** Constructor de la clase, inicializa el MessageManager. */
    public ControllerChat() {
        this.messageManager = new MessageManager();
    }

    /**
     * Método que establece el usuario que ha iniciado sesión.
     * Carga los contactos disponibles para el usuario.
     *
     * @param user Usuario que ha iniciado sesión.
     */
    public void setUser(User user) {
        this.loggedInUser = user;
        loadContacts();
    }

    /**
     * Método que cambia a la pantalla de inicio de sesión.
     *
     * @throws IOException Excepción lanzada si hay un error al cambiar de pantalla.
     */
    @FXML
    private void switchToLogin() throws IOException {
        Scenes.setRoot("pantallaLoginUser", null);
    }

    /**
     * Método que maneja la selección de un contacto de la lista.
     * Si se selecciona un contacto válido, se carga el historial de chat.
     */
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

    /**
     * Método que carga el historial de chat entre el usuario y el contacto seleccionado.
     * Muestra los mensajes en la interfaz.
     */
    private void loadChatHistory() {
        chatHistory.getChildren().clear();
        List<Message> conversation = messageManager.getMessages().stream()
                .filter(msg -> (msg.getSender().equals(loggedInUser.getGmail()) && msg.getRecipient().equals(selectedContact.getGmail())) ||
                        (msg.getSender().equals(selectedContact.getGmail()) && msg.getRecipient().equals(loggedInUser.getGmail())))
                .collect(Collectors.toList());

        for (Message message : conversation) {
            Text messageText = new Text(message.getSender() + ": " + message.getContent() + "\n");

            if (message.getSender().equals(loggedInUser.getGmail())) {
                messageText.setFill(Color.BLUE);
            } else {
                messageText.setFill(Color.GREEN);
            }

            chatHistory.getChildren().add(messageText);
        }
    }

    /**
     * Método que carga los contactos del usuario actual en el ComboBox.
     * Si no hay contactos disponibles, muestra una alerta.
     */
    private void loadContacts() {
        contactList.getItems().clear();

        if (loggedInUser != null && loggedInUser.getContacts() != null) {
            contactList.getItems().addAll(loggedInUser.getContacts());
        } else {
            Utils.ShowAlert("No hay contactos disponibles para este usuario.");
        }
    }

    /**
     * Método que maneja la adición de un nuevo contacto a la lista de contactos del usuario.
     */
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

    /**
     * Método que maneja el envío de un mensaje al contacto seleccionado.
     * Muestra alertas si no hay contacto seleccionado o si el mensaje está vacío.
     */
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

    /**
     * Método que exporta la conversación actual a un archivo de texto.
     * Si no hay contacto seleccionado, muestra una alerta.
     */
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

        File file = new File("conversacion.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Conversación entre: " + loggedInUser.getGmail() + " y " + selectedContact.getGmail() + "\n");
            writer.write("--------------------------------------------------\n");

            for (Message message : conversation) {
                writer.write(message.getSender() + ": " + message.getContent() + "\n");
            }

            Utils.ShowAlert("Conversación exportada a conversacion.txt");
        } catch (IOException e) {
            e.printStackTrace();
            Utils.ShowAlert("Error al exportar la conversación.");
        }
    }

    /**
     * Método que genera un informe de la conversación actual y lo guarda en un archivo de texto.
     * Muestra estadísticas de los mensajes enviados y palabras más comunes.
     */
    @FXML
    private void generateConversationReport() {
        if (selectedContact == null) {
            Utils.ShowAlert("Por favor, selecciona un contacto para generar el informe.");
            return;
        }

        List<Message> conversation = messageManager.getMessages().stream()
                .filter(msg -> (msg.getSender().equals(loggedInUser.getGmail()) && msg.getRecipient().equals(selectedContact.getGmail())) ||
                        (msg.getSender().equals(selectedContact.getGmail()) && msg.getRecipient().equals(loggedInUser.getGmail())))
                .collect(Collectors.toList());

        if (conversation.isEmpty()) {
            Utils.ShowAlert("No hay mensajes entre los usuarios seleccionados.");
            return;
        }

        long totalMessages = conversation.size();
        long userMessages = 0;
        long contactMessages = 0;
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        double totalLength = 0;

        List<String> allWords = new ArrayList<>();

        for (Message msg : conversation) {
            int wordCount = msg.getContent().split("\\s+").length;
            minLength = Math.min(minLength, wordCount);
            maxLength = Math.max(maxLength, wordCount);
            totalLength += wordCount;

            if (msg.getSender().equals(loggedInUser.getGmail())) {
                userMessages++;
            } else {
                contactMessages++;
            }

            Arrays.stream(msg.getContent().toLowerCase().split("\\s+"))
                    .filter(word -> word.length() > 3)
                    .forEach(allWords::add);
        }

        List<String> uniqueWords = new ArrayList<>(new HashSet<>(allWords));
        List<String> mostCommonWords = new ArrayList<>();
        List<Long> mostCommonWordsCounts = new ArrayList<>();

        for (String word : uniqueWords) {
            long frequency = allWords.stream().filter(word::equals).count();
            int index = 0;
            while (index < mostCommonWords.size() && frequency <= mostCommonWordsCounts.get(index)) {
                index++;
            }
            if (index < 10) {
                mostCommonWords.add(index, word);
                mostCommonWordsCounts.add(index, frequency);
                if (mostCommonWords.size() > 10) {
                    mostCommonWords.remove(10);
                    mostCommonWordsCounts.remove(10);
                }
            }
        }

        double averageLength = totalLength / totalMessages;

        StringBuilder report = new StringBuilder();
        report.append("Informe de la conversación entre ")
                .append(loggedInUser.getGmail()).append(" y ")
                .append(selectedContact.getGmail()).append("\n")
                .append("--------------------------------------------------\n")
                .append("Número total de mensajes: ").append(totalMessages).append("\n\n")
                .append("Mensajes enviados por cada usuario:\n")
                .append(loggedInUser.getGmail()).append(": ").append(userMessages).append(" mensajes\n")
                .append(selectedContact.getGmail()).append(": ").append(contactMessages).append(" mensajes\n");

        report.append("\nEstadísticas de longitud de mensajes:\n")
                .append("Mensaje más corto (en palabras): ").append(minLength).append("\n")
                .append("Mensaje más largo (en palabras): ").append(maxLength).append("\n")
                .append("Promedio de longitud de mensajes: ").append(String.format("%.2f", averageLength)).append("\n\n")
                .append("Palabras más comunes:\n");

        for (int i = 0; i < mostCommonWords.size(); i++) {
            report.append(mostCommonWords.get(i)).append(": ").append(mostCommonWordsCounts.get(i)).append(" veces\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("informe_conversacion.txt"))) {
            writer.write(report.toString());
        } catch (IOException e) {
            Utils.ShowAlert("Error al guardar el informe: " + e.getMessage());
            return;
        }

        Utils.ShowAlert("Informe generado y guardado como informe_conversacion.txt");
    }
}
