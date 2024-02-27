module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.sql;
    exports com.example.demo;
    opens com.example.demo to javafx.fxml;
    opens com.example.demo.models to javafx.base;
    exports com.example.demo.interfaces;
    opens com.example.demo.interfaces to javafx.fxml;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;
}