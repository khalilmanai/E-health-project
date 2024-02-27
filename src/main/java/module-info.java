module x.nutri {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    exports x.nutri;
    opens x.nutri to javafx.fxml;
    exports x.nutri.test;
    opens x.nutri.test to javafx.fxml;
    exports x.nutri.controllers;
    opens x.nutri.controllers to javafx.fxml;
}