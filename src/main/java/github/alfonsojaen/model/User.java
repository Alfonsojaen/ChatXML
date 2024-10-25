package github.alfonsojaen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @XmlRootElement(name = "user")
 * Esta anotación define que esta clase es un elemento raíz de XML
 * y se representa como <user> en el archivo XML.
 *
 * Author: Alfonso Jaen
 */
@XmlRootElement(name = "user")
public class User {

    /** Nombre de usuario del usuario. */
    private String username;

    /** Contraseña del usuario. */
    private String password;

    /** Dirección de correo Gmail del usuario. */
    private String gmail;

    /** Nombre completo del usuario. */
    private String fullName;

    /** Edad del usuario. */
    private int age;

    /** Lista de contactos de correo electrónico del usuario. */
    private List<String> contacts;

    /**
     * Constructor vacío de la clase User.
     * Inicializa la lista de contactos como una lista vacía.
     */
    public User() {
        contacts = new ArrayList<>();
    }

    /**
     * Constructor sobrecargado para inicializar las propiedades del usuario.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param gmail Correo Gmail del usuario.
     * @param fullName Nombre completo del usuario.
     * @param age Edad del usuario.
     */
    public User(String username, String password, String gmail, String fullName, int age) {
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.fullName = fullName;
        this.age = age;
        this.contacts = new ArrayList<>();
    }

    /**
     * @XmlElement(name = "contact")
     * Obtiene la lista de contactos del usuario. Cada contacto es un elemento <contact> en XML.
     *
     * @return Lista de contactos del usuario.
     */
    @XmlElement(name = "contact")
    public List<String> getContacts() {
        return contacts;
    }

    /**
     * Establece la lista de contactos del usuario.
     *
     * @param contacts Lista de contactos del usuario.
     */
    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @XmlElement
     * Establece el nombre de usuario.
     *
     * @param username Nombre de usuario.
     */
    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @XmlElement
     * Establece la contraseña del usuario.
     *
     * @param password Contraseña del usuario.
     */
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el correo Gmail del usuario.
     *
     * @return Correo Gmail del usuario.
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * @XmlElement
     * Establece el correo Gmail del usuario.
     *
     * @param gmail Correo Gmail del usuario.
     */
    @XmlElement
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre completo del usuario.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @XmlElement
     * Establece el nombre completo del usuario.
     *
     * @param fullName Nombre completo del usuario.
     */
    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return Edad del usuario.
     */
    public int getAge() {
        return age;
    }

    /**
     * @XmlElement
     * Establece la edad del usuario.
     *
     * @param age Edad del usuario.
     */
    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Agrega un contacto a la lista de contactos del usuario si aún no existe.
     *
     * @param contactEmail Correo electrónico del contacto.
     */
    public void addContact(String contactEmail) {
        if (!contacts.contains(contactEmail)) {
            contacts.add(contactEmail);
        }
    }

    /**
     * Elimina un contacto de la lista de contactos del usuario.
     *
     * @param contactEmail Correo electrónico del contacto.
     */
    public void removeContact(String contactEmail) {
        contacts.remove(contactEmail);
    }
}