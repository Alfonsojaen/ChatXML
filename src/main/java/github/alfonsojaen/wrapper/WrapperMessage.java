package github.alfonsojaen.wrapper;

import github.alfonsojaen.model.Message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Clase WrapperMessage que encapsula una lista de mensajes para su
 * serialización/deserialización en formato XML.
 * @author Alfonso Jaen
 */
@XmlRootElement(name = "messages")
public class WrapperMessage {

    private List<Message> messages;

    /**
     * Método para obtener la lista de mensajes.
     * Este método está anotado con @XmlElement para
     * que sea incluido en la representación XML.
     *
     * @return Lista de mensajes.
     */
    @XmlElement(name = "message")
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Método para establecer la lista de mensajes.
     *
     * @param messages Lista de mensajes que se desea establecer.
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
