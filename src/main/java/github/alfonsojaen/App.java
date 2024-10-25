package github.alfonsojaen;

import github.alfonsojaen.model.User;
import github.alfonsojaen.view.ControllerChat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende la clase Application de JavaFX.
 * Esta clase es responsable de iniciar la aplicación y gestionar las escenas.
 * Author Alfonso Jaen
 */
public class App extends Application {

    public static Scene scene;

    public static Stage primaryStage;

    /**
     * Método que se llama al iniciar la aplicación.
     * Crea la escena inicial y la configura en el escenario.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("pantallaLoginUser"), 640, 460);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Método para crear una nueva escena a partir de un archivo FXML.
     *
     * @param fxml Nombre del archivo FXML a cargar.
     * @param width Ancho de la nueva escena.
     * @param height Alto de la nueva escena.
     * @param User Objeto User que se pasará al controlador, si corresponde.
     * @return La nueva escena creada.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    public static Scene createScene(String fxml, double width, double height, User User) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml")); // Crea el cargador FXML
        Parent root = fxmlLoader.load();
        if (fxml.equals("pantallaChat")) {
            ControllerChat controller = fxmlLoader.getController();
            if (controller != null) {
                controller.setUser(User);
            }
        }
        Scene scene = new Scene(root, width, height);
        return scene;
    }

    /**
     * Método para cargar un archivo FXML y retornar su contenido como Parent.
     *
     * @param fxml Nombre del archivo FXML a cargar.
     * @return El contenido cargado del archivo FXML.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Método principal de la aplicación. Inicia la ejecución de JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}