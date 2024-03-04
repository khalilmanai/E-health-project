module com.example.demo {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;
    requires com.auth0.jwt;
    requires java.prefs;

    opens com.example.app to javafx.fxml;
    opens com.example.app.utils to javafx.fxml;


    exports com.example.app;
    exports com.example.app.utils;
}
