package com.example.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gui extends Application {
    
    
    private MyList<USAName> list = new MyList<USAName>(32470);
    private TableView<USAName> tableView = new TableView<>();
    private TableColumn<USAName, String> colName = new TableColumn<>("Name");
    private TableColumn<USAName, String> colGen = new TableColumn<>("Gender");
    private TableColumn<USAName, String> colFreq = new TableColumn<>("Frequency");
    private FileChooser chooser = new FileChooser();
    private Button button = new Button("Read File");
    private Button btnClear = new Button("Clear All");
    private Button btnHFreq = new Button("Highest Frequency");
    private TextArea textArea = new TextArea();
    private  HBox box = new HBox();
    private  VBox box1 = new VBox();
    private  VBox box2 = new VBox();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        handle(primaryStage);
        style();
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colGen.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        colFreq.setCellValueFactory(new PropertyValueFactory<>("Freq"));
        box1.getChildren().addAll(button, btnHFreq, textArea, btnClear);
        box2.getChildren().addAll(tableView);
        tableView.getColumns().addAll(colName, colGen, colFreq);
        box.getChildren().addAll(box1, box2);
        Scene scene = new Scene(box);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
    public void handle(Stage primaryStage) throws Exception {
        button.setOnAction(e -> {
            File file = chooser.showOpenDialog(primaryStage);
            String fileName = file.getAbsolutePath();
//            readFile(fileName);
            for (int i = 0; i < list.size(); i++) {
                tableView.getItems().add(list.getIndex(i));
            }
        });
        btnClear.setOnAction(e -> {
            tableView.getItems().clear();
            textArea.clear();
        });
        btnHFreq.setOnAction(e -> {
//            textArea.setText(highestFreq());
        });
    }
    
    public void style() {
//        box1.setPrefSize(400, 600);
//        box2.setPrefSize(400, 600);
        textArea.setMaxSize(200, 60);
//        tableView.setPrefSize(400, 600);
        colName.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        colGen.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        colFreq.prefWidthProperty().bind(tableView.widthProperty().divide(3));
        box2.prefWidthProperty().bind(box.widthProperty().divide(2));
        box1.prefWidthProperty().bind(box.widthProperty().divide(2));
        tableView.prefWidthProperty().bind(box2.widthProperty().divide(2));
        tableView.prefHeightProperty().bind(box.heightProperty());
        textArea.setEditable(false);
        button.setPrefSize(200, 60);
        btnClear.setPrefSize(200, 60);
        btnHFreq.setPrefSize(200, 60);
        box1.setSpacing(16);
        box1.setPadding(new Insets(24));
        btnClear.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #193237ff 0.0%, #2e4e58ff 50.0%, #39687cff 100.0%); -fx-text-fill: white;-fx-font-size: 16px;");
        btnHFreq.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #193237ff 0.0%, #2e4e58ff 50.0%, #39687cff 100.0%); -fx-text-fill: white;-fx-font-size: 16px;");
        button.setStyle("-fx-background-color:linear-gradient(from 0.0% 0.0% to 100.0% 0.0%, #193237ff 0.0%, #2e4e58ff 50.0%, #39687cff 100.0%); -fx-text-fill: white;-fx-font-size: 16px;");
        tableView.setStyle(" -fx-text-fill: white;-fx-font-size: 16px;");
    }
    
//    public void readFile(String fileName) {
//        try (Scanner scanner = new Scanner(new File(fileName))) {
//            String line;
//            while (scanner.hasNextLine()) {
//                line = scanner.nextLine();
//                String[] parts = line.split(",");
//                list.add(new USAName(parts[0], parts[1].charAt(0), Integer.parseInt(parts[2])));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public String highestFreq() {
//        int hFreq = 0;
//        String name = null;
//        for (int i = 0; i < list.size(); i++) {
//            USAName usaName = list.getIndex(i);
//            if (usaName.getFreq() > hFreq) {
//                hFreq = usaName.getFreq();
//                name = usaName.getName();
//            }
//        }
//        if (name != null) {
//            return "Name :" + name + ", Freq : " + hFreq;
//        } else {
//            return "No names found in the list.";
//        }
//    }
    
    public static void main(String[] args) {
        launch();
    }
    
}
