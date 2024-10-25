package github.alfonsojaen.singleton;

import github.alfonsojaen.model.User;

/**
 * Clase UserSession que maneja la sesión de un usuario dentro de la aplicación.
 * Permite iniciar sesión, cerrar sesión y verificar el estado de la sesión.
 *
 * Author: Alfonso Jaen
 */
public class UserSession {

    /** Variable estática para almacenar el usuario actual de la sesión. */
    private static User user = null;

    /**
     * Método estático que permite iniciar sesión con el correo electrónico y nombre de usuario.
     * Crea una instancia de User con la información proporcionada.
     *
     * @param gmail Correo electrónico del usuario.
     * @param username Nombre de usuario.
     */
    public static void login(String gmail, String username) {
        user = new User(username, null, gmail, null, 0);
    }

    /**
     * Método estático que permite cerrar sesión.
     * Establece la variable user en null para indicar que no hay sesión activa.
     */
    public static void logout() {
        user = null;
    }

    /**
     * Método estático que verifica si hay una sesión activa.
     *
     * @return true si hay una sesión activa, false en caso contrario.
     */
    public static boolean isLogged() {
        return user != null;
    }

    /**
     * Método estático que devuelve el usuario actual de la sesión.
     *
     * @return Usuario actual si hay sesión activa, null en caso contrario.
     */
    public static User getUser() {
        return user;
    }
}

