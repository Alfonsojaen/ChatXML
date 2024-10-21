package github.alfonsojaen.view;

import github.alfonsojaen.App;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Scenes {
    public static void setRoot(String fxml) throws IOException {
        Parent p = App.loadFXML(fxml);
        Scene newScene;

        if (fxml.equals("secondary")) {
            newScene = App.createScene(fxml, 640, 460);

        } else if (fxml.equals("pantallaLoginUser")) {
            newScene = App.createScene(fxml, 640, 460);

        } else {
            newScene = App.createScene(fxml, 640, 480);
        }
        App.primaryStage.setScene(newScene);
        App.scene.setRoot(p);


    }
}

