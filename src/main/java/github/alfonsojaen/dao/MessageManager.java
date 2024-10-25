package github.alfonsojaen.dao;

import github.alfonsojaen.model.Message;
import github.alfonsojaen.wrapper.WrapperMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase MessageManager que gestiona el envío y almacenamiento de mensajes.
 *
 * @author Alfonso Jaen
 */
public class MessageManager {
    /**
     * Ruta del archivo donde se almacenan los mensajes en formato XML.
     */
    private static final String FILE_PATH = "messages.xml";

    /**
     * Instancia de WrapperMessage que contiene la lista de mensajes.
     */
    private WrapperMessage wrapperMessage;

    /**
     * Constructor de MessageManager.
     * Inicializa el wrapperMessage y carga los mensajes desde el archivo.
     */
    public MessageManager() {
        wrapperMessage = new WrapperMessage();
        wrapperMessage.setMessages(new ArrayList<>());
        loadMessages();
    }

    /**
     * Envía un nuevo mensaje.
     * Agrega el mensaje a la lista y guarda la lista actualizada en el archivo.
     *
     * @param message El mensaje a enviar.
     */
    public void sendMessage(Message message) {
        if (wrapperMessage.getMessages() == null) {
            wrapperMessage.setMessages(new ArrayList<>());
        }
        wrapperMessage.getMessages().add(message);
        saveMessages();
    }

    /**
     * Guarda la lista de mensajes en el archivo XML.
     * Utiliza JAXB para convertir el objeto wrapperMessage a XML.
     */
    private void saveMessages() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            JAXBContext context = JAXBContext.newInstance(WrapperMessage.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperMessage, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los mensajes desde el archivo XML.
     * Si el archivo existe y no está vacío, deserializa los mensajes;
     * de lo contrario, inicializa una nueva lista vacía.
     */
    private void loadMessages() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                JAXBContext context = JAXBContext.newInstance(WrapperMessage.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                wrapperMessage = (WrapperMessage) unmarshaller.unmarshal(file);
                if (wrapperMessage.getMessages() == null) {
                    wrapperMessage.setMessages(new ArrayList<>());
                }
            } catch (JAXBException e) {
                e.printStackTrace();
                wrapperMessage.setMessages(new ArrayList<>());
            }
        } else {
            wrapperMessage.setMessages(new ArrayList<>());
        }
    }

    /**
     * Obtiene la lista de mensajes.
     *
     * @return La lista de mensajes almacenados o una lista vacía si es null.
     */
    public List<Message> getMessages() {
        return wrapperMessage.getMessages() != null ? wrapperMessage.getMessages() : new ArrayList<>();
    }
}