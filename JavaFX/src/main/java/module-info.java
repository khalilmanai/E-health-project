module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.utils;
    opens com.example.app.utils to javafx.fxml;
}