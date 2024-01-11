module com.example.projectiii {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectiii to javafx.fxml;
    exports com.example.projectiii;
}