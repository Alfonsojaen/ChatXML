package github.alfonsojaen.dao;

import github.alfonsojaen.model.Message;
import github.alfonsojaen.wrapper.WrapperMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private static final String FILE_PATH = "messages.xml"; // Ruta del archivo XML
    private WrapperMessage wrapperMessage;

    public MessageManager() {
        wrapperMessage = new WrapperMessage();
        wrapperMessage.setMessages(new ArrayList<>());
        loadMessages();
    }

    // Método para enviar un mensaje
    public void sendMessage(Message message) {
        wrapperMessage.getMessages().add(message);
        saveMessages();
    }

    // Método para guardar mensajes en el archivo XML
    private void saveMessages() {
        try {
            JAXBContext context = JAXBContext.newInstance(WrapperMessage.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapperMessage, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar mensajes desde el archivo XML
    private void loadMessages() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                JAXBContext context = JAXBContext.newInstance(WrapperMessage.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                wrapperMessage = (WrapperMessage) unmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            wrapperMessage.setMessages(new ArrayList<>());
        }
    }

    // Obtener todos los mensajes
    public List<Message> getMessages() {
        return wrapperMessage.getMessages();
    }
}
