module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.sql;
<<<<<<< HEAD
    exports com.example.demo;
    opens com.example.demo to javafx.fxml;
    opens com.example.demo.models to javafx.base;
    exports com.example.demo.interfaces;
    opens com.example.demo.interfaces to javafx.fxml;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;
=======
    requires jbcrypt;
    requires java.mail;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.utils;
    opens com.example.app.utils to javafx.fxml;
>>>>>>> c4851e2abd0b34bad66752c4d91783036acb75a1
}