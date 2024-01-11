package com.example.projectiii;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class SaveScreen extends BorderPane {
    private FileChooser chooser = new FileChooser();
    private Button btnSave = new Button();
    private Button btnBack = new Button();
    private Image gifImageDownload = new Image(
            "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\download.gif");
    private ImageView imageView1 = new ImageView(gifImageDownload);
    Image image = new Image(
            "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\left-arrow.gif");
    ImageView imageView = new ImageView(image);
    private RecordList list;

    public SaveScreen(RecordList list) {
        this.list = list;
        btnSave.setOnAction(e -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            chooser.getExtensionFilters().addAll(extFilterTxt, extFilter);
            File file = chooser.showSaveDialog(null);
            if (file != null) {
                String fileName = file.getAbsolutePath();
                list.saveFile(fileName);
            }
        });
        btnBack.setOnAction(e -> {
            SceneChanger.changeScene(new MainScreen(list));
        });
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        btnBack.setGraphic(imageView);
        imageView1.setFitHeight(80);
        imageView1.setFitWidth(80);
        btnSave.setGraphic(imageView1);
        btnBack.setStyle("-fx-background-color: transparent;");
        btnSave.setStyle("-fx-background-color: transparent;");
        setStyle("-fx-background-color:#ffffff");
        setLeft(btnBack);
        setCenter(btnSave);
    }
}