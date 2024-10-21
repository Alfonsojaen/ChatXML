package github.alfonsojaen.test;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;

public class TestContacs {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        String userEmail = "alfonsojaen@gmail.com";
        String newContactEmail = "alfonsoj@gmail.com";

        userManager.addContactToUser(userEmail, newContactEmail);

        User user = userManager.findUserByEmail(userEmail);
        if (user != null) {
            System.out.println("Contactos de " + user.getFullName() + ":");
            for (String contact : user.getContacts()) {
                System.out.println("Contacto: " + contact);
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    }

