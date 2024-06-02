module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires javafx.base;

    
    opens demo to javafx.fxml;
    exports demo;
}
