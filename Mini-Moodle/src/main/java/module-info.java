module com.example.minimoodle {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens com.example.minimoodle to javafx.fxml;
    exports com.example.minimoodle;
    opens com.example.minimoodle.teacherfunctionalities to javafx.fxml;
    exports com.example.minimoodle.teacherfunctionalities;
    exports com.example.minimoodle.studentfunctionalities to javafx.fxml;
    opens com.example.minimoodle.studentfunctionalities to javafx.fxml;
    exports com.example.minimoodle.adminfunctionalities to javafx.fxml;
    opens com.example.minimoodle.adminfunctionalities to javafx.fxml;
}
