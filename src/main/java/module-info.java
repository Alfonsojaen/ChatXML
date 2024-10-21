module github.alfonsojaen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.activation;

    opens github.alfonsojaen to javafx.fxml, java.xml.bind;
    opens github.alfonsojaen.dao to java.xml.bind;

    exports github.alfonsojaen;
    exports github.alfonsojaen.model;
    opens github.alfonsojaen.wrapper to java.xml.bind;
}
