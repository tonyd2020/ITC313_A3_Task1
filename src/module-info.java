module a3Task1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.openjfx to javafx.fxml;

    exports org.openjfx;
}