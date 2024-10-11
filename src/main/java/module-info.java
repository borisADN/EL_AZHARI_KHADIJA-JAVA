module org.example.el_azhari_khadija {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires mysql.connector.j;


    opens org.example.el_azhari_khadija to javafx.fxml;
    exports org.example.el_azhari_khadija;
}