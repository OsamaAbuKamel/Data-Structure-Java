package com.example.projectiii;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChanger {
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void changeScene(Parent parent) {
        Scene scene = new Scene(parent, 1400, 820);
        mainStage.setScene(scene);
    }
}