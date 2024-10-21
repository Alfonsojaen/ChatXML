package github.alfonsojaen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Conversation {
    private List<Message> messages;

    public Conversation() {}

    public Conversation(List<Message> messages) {
        this.messages = messages;
    }

    @XmlElement
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
