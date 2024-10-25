package github.alfonsojaen.wrapper;

import github.alfonsojaen.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase WrapperUser que encapsula una lista de usuarios para su
 * serialización/deserialización en formato XML.
 * Author Alfonso Jaen
 */
@XmlRootElement(name = "users")
public class WrapperUser {

    private List<User> users;

    /**
     * Constructor por defecto que inicializa la lista de usuarios.
     */
    public WrapperUser() {
        this.users = new ArrayList<>();
    }

    /**
     * Método para obtener la lista de usuarios.
     * Este método está anotado con @XmlElement para
     * que sea incluido en la representación XML.
     *
     * @return Lista de usuarios.
     */
    @XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

    /**
     * Método para establecer la lista de usuarios.
     *
     * @param users Lista de usuarios que se desea establecer.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
