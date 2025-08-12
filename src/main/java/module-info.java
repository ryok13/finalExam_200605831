module com.georgiancollege.finalexam_200605831 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.desktop;
    requires com.google.gson;


    opens com.georgiancollege.finalexam_200605831 to javafx.fxml, com.google.gson;
    exports com.georgiancollege.finalexam_200605831;
}