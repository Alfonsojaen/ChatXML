package github.alfonsojaen.dao;

import github.alfonsojaen.model.User;
import github.alfonsojaen.wrapper.WrapperUser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase UserManager que gestiona el almacenamiento y manejo de usuarios.
 *
 * @author Alfonso Jaen
 */
public class UserManager {
    /**
     * Ruta del archivo donde se almacenan los usuarios en formato XML.
     */
    private static final String FILE_PATH = "users.xml";

    /**
     * Instancia de WrapperUser que contiene la lista de usuarios.
     */
    private WrapperUser wrapperUser;

    /**
     * Constructor de UserManager.
     * Inicializa el wrapperUser y carga los usuarios desde el archivo.
     */
    public UserManager() {
        wrapperUser = new WrapperUser();
        wrapperUser.setUsers(new ArrayList<>());
        loadUsers();
    }

    /**
     * Agrega un nuevo usuario a la lista.
     * Guarda la lista actualizada en el archivo XML.
     *
     * @param user El usuario a agregar.
     * @return true si se agrega el usuario correctamente.
     */
    public boolean addUser(User user) {
        wrapperUser.getUsers().add(user);
        saveUsers();
        return true;
    }

    /**
     * Guarda la lista de usuarios en el archivo XML.
     * Utiliza JAXB para convertir el objeto wrapperUser a XML.
     */
    private void saveUsers() {
        try {
            File file = new File(FILE_PATH);
            // Si el archivo no existe, se crea
            if (!file.exists()) {
                file.createNewFile();
            }
            JAXBContext context = JAXBContext.newInstance(WrapperUser.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperUser, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Carga los usuarios desde el archivo XML.
     * Si el archivo existe y no está vacío, deserializa los usuarios;
     * de lo contrario, inicializa una nueva lista vacía.
     */
    private void loadUsers() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                JAXBContext context = JAXBContext.newInstance(WrapperUser.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                wrapperUser = (WrapperUser) unmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            wrapperUser.setUsers(new ArrayList<>());
        }
    }

    /**
     * Obtiene la lista de usuarios.
     *
     * @return La lista de usuarios almacenados.
     */
    public List<User> getUsers() {
        return wrapperUser.getUsers();
    }

    /**
     * Verifica las credenciales de inicio de sesión del usuario.
     *
     * @param gmail El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario si las credenciales son válidas, null en caso contrario.
     */
    public User checkLogin(String gmail, String password) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WrapperUser.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            WrapperUser wrapper = (WrapperUser) unmarshaller.unmarshal(new File(FILE_PATH));

            List<User> users = wrapper.getUsers();
            for (User user : users) {
                if (user.getGmail() != null && user.getPassword() != null && user.getGmail().equals(gmail) && user.getPassword().equals(password)) {
                    return user;
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Agrega un contacto a un usuario existente.
     *
     * @param userEmail El correo electrónico del usuario al que se le agregará el contacto.
     * @param contactEmail El correo electrónico del contacto a agregar.
     */
    public void addContactToUser(String userEmail, String contactEmail) {
        for (User user : wrapperUser.getUsers()) {
            if (user.getGmail().equals(userEmail)) {
                user.addContact(contactEmail);
                saveUsers();
                return;
            }
        }
        System.out.println("Usuario no encontrado: " + userEmail);
    }

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El usuario si se encuentra, null en caso contrario.
     */
    public User findUserByEmail(String email) {
        for (User user : wrapperUser.getUsers()) {
            if (user.getGmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}