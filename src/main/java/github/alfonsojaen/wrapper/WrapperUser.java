package github.alfonsojaen.wrapper;

import github.alfonsojaen.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
public class WrapperUser {
    private List<User> users;

    public WrapperUser() {
        this.users = new ArrayList<>();
    }

    @XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
