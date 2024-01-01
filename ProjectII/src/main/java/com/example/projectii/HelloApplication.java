package com.example.projectii;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class HelloApplication extends Application {
    private BorderPane root = new BorderPane();
    private HBox box = new HBox();
    private HBox box1 = new HBox();
    private Label label1 = new Label("FIle: ");
    private Label label2 = new Label();
    private Button btnLoad = new Button("Load");
    private Button btnPrev = new Button("Prev");
    private Button btnNext = new Button("Next");
    private TextArea area = new TextArea();
    private FileChooser fileChoose = new FileChooser();
    private Main main = new Main();
    private int count = 0;
    private String fileName = "";

    @Override
    public void start(Stage stage) throws IOException {
        style();
        handle();
        label1.setId("label1");
        btnNext.setDisable(true);
        btnPrev.setDisable(true);
        Scene scene = new Scene(initRootLayout(), 450, 350);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("ProjectII");
        stage.setScene(scene);
        stage.show();
    }

    private BorderPane initRootLayout() {
        area.setEditable(false);
        box.getChildren().addAll(label1, label2, btnLoad);
        box1.getChildren().addAll(btnPrev, btnNext);
        root.setTop(box);
        root.setCenter(area);
        root.setBottom(box1);
        return root;
    }

    private void style() {
        box1.setAlignment(Pos.CENTER);
        area.setMaxSize(420, 220);
        btnLoad.setPrefSize(100, 40);
        btnPrev.setPrefSize(100, 40);
        btnNext.setPrefSize(100, 40);
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        box1.setPadding(new Insets(10));
        box1.setSpacing(10);
        root.setPadding(new Insets(8));
        label2.prefWidthProperty().bind(box.widthProperty().divide(2));
    }

    private void handle() {
        btnLoad.setOnAction(e -> {
            try {
                File file = fileChoose.showOpenDialog(null);
                if (file != null) {
                    fileName = file.getAbsolutePath();
                    String[] arr = fileName.split("\\.".trim());
                    if (!fileName.isEmpty()) {
                        if (arr[1].equals("242") && main.isBalanced(fileName)) {
                            label2.setText(fileName);
                            information(fileName);
                        } else {
                            alert(AlertType.ERROR, "", "File Not Is Balanced OR Extension .242");
                        }
                    } else {
                        alert(AlertType.ERROR, "", "File Is Empty");
                    }
                } else {
                    alert(AlertType.ERROR, "Chose File", "File Is Empty");
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException
                    | NoSuchElementException ex) {
                alert(AlertType.ERROR, "", ex.getMessage());
            }
        });
        btnPrev.setOnAction(e -> {
            try {
                if (count > 0) {
                    count--;
                    information(fileName);
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NoSuchElementException ex) {
                alert(AlertType.ERROR, "", ex.getMessage());
            }
        });
        btnNext.setOnAction(e -> {
            try {
                if (count < 1) {
                    count++;
                    information(fileName);
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NoSuchElementException ex) {
                alert(AlertType.ERROR, "", ex.getMessage());
            }
        });
    }

    private void information(String file) {
        area.setText(main.convertEquation(file, count));
        btnNext.setDisable(count >= 1);
        btnPrev.setDisable(count <= 0);
    }

    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}