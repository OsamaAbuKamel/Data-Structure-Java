module com.example.project0_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens com.example.project0_javafx to javafx.fxml;
    exports com.example.project0_javafx;
}