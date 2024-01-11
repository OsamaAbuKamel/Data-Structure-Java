package com.example.projectiii;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainGUI extends Application {
    private RecordList list;

    @Override
    public void start(Stage stage) throws Exception {
        list = new RecordList();
        SceneChanger.setMainStage(stage);
        SceneChanger.changeScene(new MainScreen(list));
        stage.getIcons().add(new Image(
                "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\electricity.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
