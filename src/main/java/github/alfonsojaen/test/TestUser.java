package github.alfonsojaen.test;

import github.alfonsojaen.dao.UserManager;
import github.alfonsojaen.model.User;


public class TestUser {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        User newUser = new User("alfonso", "jaen2005", "johndoe@example.com", "John Doe", 30);
        userManager.addUser(newUser);

        for (User user : userManager.getUsers()) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Age: " + user.getAge());
            System.out.println("-------------------------");
        }
    }
}