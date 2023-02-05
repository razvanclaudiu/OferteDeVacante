module com.example.vacantion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.vacantion to javafx.fxml;
    opens com.example.vacantion.domain to javafx.base;
    exports com.example.vacantion;
    exports com.example.vacantion.domain;

}