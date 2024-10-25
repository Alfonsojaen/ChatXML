package github.alfonsojaen.utils;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Utils que proporciona métodos utilitarios para la aplicación.
 * Incluye métodos para guardar contenido en un archivo y para mostrar alertas informativas.
 *
 * Author: Alfonso Jaen
 */
public class Utils {

    /**
     * Método estático que guarda contenido en un archivo especificado.
     * Utiliza un BufferedWriter para escribir el contenido en el archivo de forma eficiente.
     *
     * @param filePath Ruta del archivo donde se guardará el contenido.
     * @param content Contenido que se guardará en el archivo.
     */
    public static void saveToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático que muestra una alerta informativa con el mensaje especificado.
     * Utiliza la clase Alert para generar una ventana emergente con el mensaje.
     *
     * @param message Mensaje que se mostrará en la alerta.
     */
    public static void ShowAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }
}

