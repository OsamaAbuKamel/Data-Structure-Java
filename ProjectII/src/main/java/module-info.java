module com.example.projectii {
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens com.example.projectii to javafx.fxml;
    exports com.example.projectii;
}