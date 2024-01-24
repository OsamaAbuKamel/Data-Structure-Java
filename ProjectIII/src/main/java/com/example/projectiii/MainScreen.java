package com.example.projectiii;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

public class MainScreen extends BorderPane {
    private RecordAVL list;
    private Button btnUpload = new Button();
    private Button btnManagement = new Button("MANAGEMENT");
    private Button btnStatistics = new Button("STATISTICS");
    private Button btnSave = new Button("SAVE FILE");
    private FileChooser chooser = new FileChooser();
    private HBox hBox = new HBox();
    private Image gifImageUpload = new Image(
            "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\system-solid-49-upload-file.gif");
    private ImageView uploadImage = new ImageView(gifImageUpload);
    private Tooltip toolTip = new Tooltip("Upload File .CSV");
    
    public MainScreen(RecordAVL list) {
        this.list = list;
        setStyle("-fx-background-color:#ffffff");
        toolTip.setTextAlignment(TextAlignment.LEFT);
        // btnUpload.setTooltip(toolTip);
        btnUpload.setTooltip(toolTip);
        style();
        handle(list);
        hBox.getChildren().addAll(btnUpload, btnManagement, btnStatistics, btnSave);
        setCenter(hBox);
    }
    
    private void style() {
        hBox.setPadding(new Insets(16));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(16);
        setPadding(new Insets(16));
        uploadImage.setFitHeight(60);
        uploadImage.setFitWidth(60);
        btnUpload.setGraphic(uploadImage);
        btnUpload.setStyle("-fx-background-color: transparent;");
        btnSave.setStyle("-fx-background-color:#111330;-fx-text-fill:#ffffff");
        btnManagement.setStyle("-fx-background-color: #111330;-fx-text-fill:#ffffff");
        btnStatistics.setStyle("-fx-background-color: #111330;-fx-text-fill:#ffffff");
        btnManagement.setPrefSize(120, 60);
        btnStatistics.setPrefSize(120, 60);
        btnSave.setPrefSize(120, 60);
    }
    
    private void handle(RecordAVL list) {
        btnUpload.setOnAction(e -> {
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(null);
            if (file == null) {
                alert(Alert.AlertType.ERROR, "Error", "File Is Null");
            } else if (!file.exists()) {
                alert(Alert.AlertType.ERROR, "Error", "File not found");
            } else if (!file.isFile()) {
                alert(Alert.AlertType.ERROR, "Error", "File is not a file");
            } else {
                String fileName = file.getAbsolutePath();
                list.loadFile(fileName);
            }
        });
        btnManagement.setOnAction(e -> {
            SceneChanger.changeScene(new ManagementScreen(list));
        });
        btnStatistics.setOnAction(e -> {
            SceneChanger.changeScene(new StatisticsScreen(list));
        });
        btnSave.setOnAction(e -> {
            SceneChanger.changeScene(new SaveScreen(list));
        });
    }
    
    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}