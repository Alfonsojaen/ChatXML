module github.alfonsojaen {
    requires javafx.controls;
    requires javafx.fxml;

    opens github.alfonsojaen to javafx.fxml;
    exports github.alfonsojaen;
}
