package github.alfonsojaen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "user")
public class User {
    private String username;
    private String password;
    private String gmail;
    private String fullName;
    private int age;
    private List<String> contacts;

    public User() {
        contacts = new ArrayList<>();
    }

    public User(String username, String password, String gmail, String fullName, int age) {
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.fullName = fullName;
        this.age = age;
        this.contacts = new ArrayList<>();
    }

    @XmlElement(name = "contact")
    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }
    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    @XmlElement
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public void addContact(String contactEmail) {
        if (!contacts.contains(contactEmail)) {
            contacts.add(contactEmail);
        }
    }

    public void removeContact(String contactEmail) {
        contacts.remove(contactEmail);
    }
}

