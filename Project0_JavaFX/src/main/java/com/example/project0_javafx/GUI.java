package com.example.project0_javafx;

import java.io.File;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
    
    private CountryList country = new CountryList();
    private Button btnFile = new Button("Read File");
    private Button btnPrint = new Button("Print");
    private Button btnSearch = new Button("Search");
    private Button btnRemove = new Button("Remove");
    private Button btnAdd = new Button("Add");
    private Button btnClear = new Button("Clear List");
    private Button btnSort = new Button("Sort by Internet Usage");
    private TableView table = new TableView<>();
    private  VBox vBoxR = new VBox();
    private VBox vBoxL = new VBox();
    private BorderPane borderPane = new BorderPane();
    private TableColumn<Country, String> nameCol = new TableColumn<>("Name");
    private TableColumn<Country, Double> internetCol = new TableColumn<>(
            "Internet Usage");
    private FileChooser fileChooser = new FileChooser();
    private Label lblCountry = new Label("Country Name: ");
    private Label lblInternet = new Label("Internet Usage: ");
    private TextField tfCountry = new TextField();
    private TextField tfInternet = new TextField();
    private HBox hBox0 = new HBox(16);
    private HBox hBox3 = new HBox();
    private RadioButton rbFile = new RadioButton("File");
    private RadioButton rbAdd = new RadioButton("Add");
    private RadioButton rbRemove = new RadioButton("Remove");
    private RadioButton rbSearch = new RadioButton("Search");
    private ToggleGroup group = new ToggleGroup();
    private TextArea ta = new TextArea();
    private GridPane gridPane = new GridPane();
    private String pathCss = "style.css";
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        visible(false);
        initial();
        space();
        handleRB();
        handle(primaryStage);
        borderPane.setLeft(vBoxL);
        borderPane.setRight(vBoxR);
        Scene scene = new Scene(borderPane, 1200, 700);
        scene
                .getStylesheets()
                .add(getClass().getResource(pathCss).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void initial() {
        ta.setEditable(false);
        tfCountry.setPromptText("Country Name");
        tfInternet.setPromptText("Internet Usage");
        rbAdd.setToggleGroup(group);
        rbRemove.setToggleGroup(group);
        rbSearch.setToggleGroup(group);
        rbFile.setToggleGroup(group);
        hBox0.getChildren().addAll(rbFile, rbAdd, rbRemove, rbSearch);
        gridPane.add(tfCountry, 0, 0);
        gridPane.add(btnRemove, 1, 0);
        gridPane.add(btnSearch, 1, 0);
        gridPane.add(ta, 0, 1, 2, 1);
        gridPane.add(tfInternet, 0, 1);
        gridPane.add(btnAdd, 1, 1);
        gridPane.add(btnFile, 0, 1);
        vBoxL.getChildren().addAll(hBox0, gridPane);
        nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        internetCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getInternetUsage())
                .asObject());
        table.getColumns().addAll(nameCol, internetCol);
        hBox3.getChildren().addAll(btnPrint, btnSort, btnClear);
        vBoxR.getChildren().addAll(table, hBox3);
    }
    
    private void handle(Stage stage) {
        btnFile.setOnAction(e -> {
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(stage);
            if (file == null) {
                alert(Alert.AlertType.ERROR, "Error", "File Is Null");
            } else if (!file.exists()) {
                alert(Alert.AlertType.ERROR, "Error", "File not found");
            } else if (!file.isFile()) {
                alert(Alert.AlertType.ERROR, "Error", "File is not a file");
            } else {
                String fileName = file.getAbsolutePath();
                country.read(fileName);
            }
        });
        btnAdd.setOnAction(e -> {
            if (tfCountry.getText().isEmpty() || tfInternet.getText().isEmpty()) {
                alert(Alert.AlertType.ERROR, "Error", "Enter Name and Internet Usage");
            } else {
                try {
                    country.add(
                            tfCountry.getText(),
                            Double.parseDouble(tfInternet.getText()));
                } catch (NumberFormatException ex) {
                    alert(Alert.AlertType.WARNING, "Warning", ex.getMessage());
                } catch (NullPointerException ex) {
                    alert(Alert.AlertType.WARNING, "Warning", "Country not found");
                } catch (IllegalArgumentException ex) {
                    alert(Alert.AlertType.WARNING, "Warning", ex.getMessage());
                    
                }
            }
            clear();
            
        });
        btnRemove.setOnAction(e -> {
            try {
                country.remove(tfCountry.getText());
            } catch (NullPointerException ex) {
                alert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
            clear();
            
        });
        btnPrint.setOnAction(e -> tableE());
        btnSearch.setOnAction(e -> {
            Country country1 = country.search(tfCountry.getText());
            try {
                ta.setVisible(true);
                clear();
                ta.setText(country1.toString());
            } catch (NullPointerException ex) {
                alert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });
        
        btnClear.setOnAction(e -> {
            clear();
            country.clear();
            table.getItems().clear();
        });
        
        btnSort.setOnAction(e -> {
            country.sort();
            tableE();
        });
    }
    
    private void tableE() {
        table.getItems().clear();
        for (int i = 0; i < country.getList().size(); i++) {
            table.getItems().addAll(country.getList().get(i));
        }
    }
    
    private void handleRB() {
        rbFile.setOnAction(e -> {
            visible(false);
            btnFile.setVisible(true);
        });
        rbAdd.setOnAction(e -> {
            clear();
            visible(false);
            tfCountry.setVisible(true);
            tfInternet.setVisible(true);
            lblCountry.setVisible(true);
            lblInternet.setVisible(true);
            btnAdd.setVisible(true);
        });
        rbRemove.setOnAction(e -> {
            clear();
            visible(false);
            
            lblCountry.setVisible(false);
            tfCountry.setVisible(true);
            btnRemove.setVisible(true);
        });
        rbSearch.setOnAction(e -> {
            clear();
            visible(false);
            lblCountry.setVisible(true);
            tfCountry.setVisible(true);
            btnSearch.setVisible(true);
        });
    }
    
    private void clear() {
        tfCountry.clear();
        tfInternet.clear();
        ta.clear();
    }
    
    private void visible(boolean flag) {
        lblCountry.setVisible(flag);
        tfCountry.setVisible(flag);
        lblInternet.setVisible(flag);
        tfInternet.setVisible(flag);
        btnAdd.setVisible(flag);
        btnRemove.setVisible(flag);
        btnSearch.setVisible(flag);
        btnFile.setVisible(flag);
        ta.setVisible(flag);
    }
    
    private void space() {
        ta.setPrefSize(200, 50);
        ta.setWrapText(true);
        vBoxL.prefWidthProperty().bind(borderPane.widthProperty().divide(2));
        vBoxR.prefWidthProperty().bind(borderPane.widthProperty().divide(2));
        nameCol.prefWidthProperty().bind(table.widthProperty().divide(2));
        internetCol.prefWidthProperty().bind(table.widthProperty().divide(2));
        table.prefWidthProperty().bind(vBoxR.widthProperty());
        table.prefHeightProperty().bind(vBoxR.heightProperty());
        hBox3.setPrefSize(450, 100);
        btnSort.prefWidthProperty().bind(hBox3.widthProperty().divide(3));
        btnClear.prefWidthProperty().bind(hBox3.widthProperty().divide(3));
        btnPrint.prefWidthProperty().bind(hBox3.widthProperty().divide(3));
        btnPrint.prefHeightProperty().bind(hBox3.heightProperty());
        btnSort.prefHeightProperty().bind(hBox3.heightProperty());
        btnClear.prefHeightProperty().bind(hBox3.heightProperty());
        rbAdd.prefWidthProperty().bind(hBox0.widthProperty().divide(4));
        rbRemove.prefWidthProperty().bind(hBox0.widthProperty().divide(4));
        rbSearch.prefWidthProperty().bind(hBox0.widthProperty().divide(4));
        rbFile.prefWidthProperty().bind(hBox0.widthProperty().divide(4));
        rbFile.prefHeightProperty().bind(hBox0.heightProperty());
        rbAdd.prefHeightProperty().bind(hBox0.heightProperty());
        rbRemove.prefHeightProperty().bind(hBox0.heightProperty());
        rbSearch.prefHeightProperty().bind(hBox0.heightProperty());
        rbFile.prefHeightProperty().bind(hBox0.heightProperty());
        hBox0.setPrefHeight(90);
        gridPane.setVgap(16);
        gridPane.setAlignment(Pos.BASELINE_CENTER);
        gridPane.setPadding(new Insets(16, 16, 16, 16));
        btnRemove.setPrefSize(110, 60);
        btnAdd.setPrefSize(110, 60);
        btnSearch.setPrefSize(110, 60);
        btnFile.setPrefSize(110, 60);
        tfCountry.setPrefSize(250, 60);
        tfInternet.setPrefSize(250, 60);
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