module com.t1.task.shubookchapter02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;
    requires java.desktop;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.t1.task4.shubookchapter02 to javafx.fxml, spring.core; // добавлен доступ для spring.core
    exports com.t1.task4.shubookchapter02;
    opens com.t1.task4.shubookchapter02.domain to com.fasterxml.jackson.databind;

}