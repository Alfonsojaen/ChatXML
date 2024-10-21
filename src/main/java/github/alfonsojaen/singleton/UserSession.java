package github.alfonsojaen.singleton;

import github.alfonsojaen.model.User;

public class UserSession {
    private static User user=null;
    public static void login(String gmail,String username) {
        user = new User(username,null,gmail,null,0);
    }
    public static void logout() {
        user = null;
    }
    public static boolean isLogged() {
        return user==null?false:true;
    }
    public static User getUser() {
        return user;
    }
}

