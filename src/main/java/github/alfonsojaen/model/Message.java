package github.alfonsojaen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @XmlRootElement(name = "message")
 * Esta anotación define que esta clase es un elemento raíz de XML
 * y se representa como <message> en el archivo XML.
 *
 * Author: Alfonso Jaen
 */
@XmlRootElement(name = "message")
public class Message {

    /** Almacena el remitente del mensaje. */
    private String sender;

    /** Almacena el destinatario del mensaje. */
    private String recipient;

    /** Contenido o texto del mensaje. */
    private String content;

    /** Marca de tiempo de cuando se envió el mensaje. */
    private String timestamp;

    /**
     * Constructor vacío de la clase Message.
     * Es requerido por el framework para la serialización y deserialización de XML.
     */
    public Message() {}

    /**
     * Constructor sobrecargado que permite inicializar todas las variables de instancia.
     *
     * @param sender El remitente del mensaje.
     * @param recipient El destinatario del mensaje.
     * @param content El contenido del mensaje.
     * @param timestamp La marca de tiempo del mensaje.
     */
    public Message(String sender, String recipient, String content, String timestamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = timestamp;
    }

    /**
     * @XmlElement
     * Método que obtiene el remitente del mensaje. Al utilizar @XmlElement,
     * este método se incluye en el archivo XML como un elemento.
     *
     * @return El remitente del mensaje.
     */
    @XmlElement
    public String getSender() {
        return sender;
    }

    /**
     * Método para establecer el remitente del mensaje.
     *
     * @param sender El remitente del mensaje.
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @XmlElement
     * Método que obtiene el destinatario del mensaje. Este método
     * se incluirá en el archivo XML como un elemento.
     *
     * @return El destinatario del mensaje.
     */
    @XmlElement
    public String getRecipient() {
        return recipient;
    }

    /**
     * Método para establecer el destinatario del mensaje.
     *
     * @param recipient El destinatario del mensaje.
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @XmlElement
     * Método que obtiene el contenido del mensaje, incluido en el archivo XML.
     *
     * @return El contenido del mensaje.
     */
    @XmlElement
    public String getContent() {
        return content;
    }

    /**
     * Método para establecer el contenido del mensaje.
     *
     * @param content El contenido del mensaje.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @XmlElement
     * Método que obtiene la marca de tiempo del mensaje, incluido en el archivo XML.
     *
     * @return La marca de tiempo del mensaje.
     */
    @XmlElement
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Método para establecer la marca de tiempo del mensaje.
     *
     * @param timestamp La marca de tiempo del mensaje.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}