module com.example.demo {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    exports com.example.app.controllers; // Export the controllers package
    opens com.example.app.controllers to javafx.fxml; // Open the controllers package to javafx.fxml
    exports com.example.app.Articles_Ehealth.Controller;
    opens com.example.app.Articles_Ehealth.Controller to javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    requires jbcrypt;
    requires java.mail;
    requires com.auth0.jwt;
    requires java.prefs;
    requires java.desktop;
    requires java.sql.rowset;
    requires twilio;
    requires org.apache.pdfbox;
    requires com.google.zxing;
    requires poi;
    requires poi.ooxml;

    opens com.example.app to javafx.fxml;
    opens com.example.app.utils to javafx.fxml;


    exports com.example.app;
    exports com.example.app.utils;

}
