package github.alfonsojaen.view;

import java.io.IOException;

import github.alfonsojaen.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Scenes.setRoot("primary", null);
    }
}