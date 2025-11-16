module com.mycompany.mavenproject4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;

    opens com.mycompany.mavenproject4 to javafx.fxml;
    exports com.mycompany.mavenproject4;
}
