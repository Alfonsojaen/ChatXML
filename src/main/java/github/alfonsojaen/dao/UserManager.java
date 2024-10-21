package github.alfonsojaen.dao;

import github.alfonsojaen.model.User;
import github.alfonsojaen.wrapper.WrapperUser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String FILE_PATH = "users.xml";
    private WrapperUser wrapperUser;

    public UserManager() {
        wrapperUser = new WrapperUser();
        wrapperUser.setUsers(new ArrayList<>());
        loadUsers();
    }

    public void addUser(User user) {
        wrapperUser.getUsers().add(user);
        saveUsers();
    }

    private void saveUsers() {
        try {
            JAXBContext context = JAXBContext.newInstance(WrapperUser.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperUser, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

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

    public List<User> getUsers() {
        return wrapperUser.getUsers();
    }
    public String checkLogin(String gmail, String password) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WrapperUser.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            WrapperUser wrapper = (WrapperUser) unmarshaller.unmarshal(new File(FILE_PATH));

            List<User> users = wrapper.getUsers();
            for (User user : users) {

                if (user.getGmail() != null && user.getPassword() != null && user.getGmail().equals(gmail) && user.getPassword().equals(password)) {
                    return user.getFullName();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}