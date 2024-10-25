package github.alfonsojaen.test;

import github.alfonsojaen.dao.MessageManager;
import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.Message;
import github.alfonsojaen.model.User;

import java.util.List;

public class TestMessage {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        User user1 = new User("usuario1", "contraseña1", "user1@gmail.com", "Usuario Uno", 25);
        User user2 = new User("usuario2", "contraseña2", "user2@gmail.com", "Usuario Dos", 30);
        User user3 = new User("usuario3", "contraseña3", "user3@gmail.com", "Usuario Tres", 28);

        userManager.addUser(user1);
        userManager.addUser(user2);
        userManager.addUser(user3);

        MessageManager messageManager = new MessageManager();

        sendMessage(messageManager, user1.getGmail(), user2.getGmail(), "Hola, Usuario Dos!");
        sendMessage(messageManager, user2.getGmail(), user1.getGmail(), "Hola, Usuario Uno! ¿Cómo estás?");
        sendMessage(messageManager, user1.getGmail(), user3.getGmail(), "¡Hola, Usuario Tres! ¿Te gustaría salir hoy?");

        List<Message> messages = messageManager.getMessages();
        for (Message message : messages) {
            System.out.println("De: " + message.getSender());
            System.out.println("Para: " + message.getRecipient());
            System.out.println("Contenido: " + message.getContent());
            System.out.println("Fecha y hora: " + message.getTimestamp());
            System.out.println("-------------------------------");
        }
    }

    private static void sendMessage(MessageManager messageManager, String sender, String recipient, String content) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        Message message = new Message(sender, recipient, content, timestamp);
        messageManager.sendMessage(message);
        System.out.println("Mensaje enviado de " + sender + " a " + recipient + ": " + content);
    }
    }
