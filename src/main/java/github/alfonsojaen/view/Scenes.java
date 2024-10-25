package github.alfonsojaen.view;

import github.alfonsojaen.App;
import github.alfonsojaen.model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Clase Scenes que gestiona la configuración y cambio de escenas en la aplicación.
 * @author Alfonso Jaen
 */
public class Scenes {

    /**
     * Método para establecer la escena raíz de la aplicación.
     * Carga la escena correspondiente al archivo FXML especificado y
     * ajusta el tamaño de la ventana según la escena.
     *
     * @param fxml Nombre del archivo FXML que define la interfaz de usuario.
     * @param user Usuario que se pasará a la escena si es necesario.
     * @throws IOException Excepción lanzada si hay un error al cargar el archivo FXML.
     */
    public static void setRoot(String fxml, User user) throws IOException {
        Parent p = App.loadFXML(fxml);
        Scene newScene;

        if (fxml.equals("pantallaRegisterUser")) {
            newScene = App.createScene(fxml, 640, 460, null);
        } else if (fxml.equals("pantallaChat")) {
            newScene = App.createScene(fxml, 700, 700, user);
        } else if (fxml.equals("pantallaLoginUser")) {
            newScene = App.createScene(fxml, 640, 460, null);
        } else {
            newScene = App.createScene(fxml, 640, 480, null);
        }

        App.primaryStage.setScene(newScene);
        App.primaryStage.show();
    }
}


