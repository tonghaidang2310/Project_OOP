module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires javafx.base;

    requires jbcrypt;
    requires java.base;
    requires javax.servlet.api;

    opens demo to javafx.fxml;
    opens demo.Course to javafx.base;
    exports demo;
}
