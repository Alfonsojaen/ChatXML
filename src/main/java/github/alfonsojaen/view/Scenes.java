package github.alfonsojaen.view;

import github.alfonsojaen.App;
import github.alfonsojaen.model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Scenes {
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


