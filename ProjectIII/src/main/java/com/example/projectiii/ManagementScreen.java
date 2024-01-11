package com.example.projectiii;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;

public class ManagementScreen extends BorderPane {
    // Input Fields
    private DatePicker datePicker = new DatePicker();
    private TextField israelInput = new TextField();
    private TextField egyptInput = new TextField();
    private TextField gazaInput = new TextField();
    private TextField overallDemandInput = new TextField();
    private TextField powerCutsHoursDayInput = new TextField();
    private TextField tempInput = new TextField();
    // Buttons
    private Button addBtn = new Button("Add");
    private Button updateBtn = new Button("Update");
    private Button deleteBtn = new Button("Delete");
    private Button backBtn = new Button();
    private Button searchBtn = new Button("Search");
    private HBox buttonBox = new HBox();
    private VBox vLeft = new VBox();
    private TableView<ElectricityRecord> tableView = new TableView<>();
    private RecordList list;
    private TableColumn<ElectricityRecord, LocalDate> dateCol = new TableColumn<>("Date");
    private TableColumn<ElectricityRecord, Double> israeliLinesCol = new TableColumn<>("Israeli Lines");
    private TableColumn<ElectricityRecord, Double> gazaPlantCol = new TableColumn<>("Gaza Plant");
    private TableColumn<ElectricityRecord, Double> egyptianLinesCol = new TableColumn<>("Egyptian Lines");
    private TableColumn<ElectricityRecord, Double> totalSupplyCol = new TableColumn<>("Total Supply");
    private TableColumn<ElectricityRecord, Double> overallDemandCol = new TableColumn<>("Overall Demand");
    private TableColumn<ElectricityRecord, Double> powerCutsCol = new TableColumn<>("Power Cuts Hours Day");
    private TableColumn<ElectricityRecord, Double> tempCol = new TableColumn<>("Temp");

    // Constructor
    public ManagementScreen(RecordList list) {
        String fileName = "style.css";
        if (fileName != null) {
            getStylesheets().add(getClass().getResource(fileName).toExternalForm());
        }
        this.list = list;
        style();
        handle();
        setRight(initializeTable());
        vLeft.getChildren().addAll(backBtn, initializeGridPane());
        setLeft(vLeft);
    }

    // Methods for initializing gridPane
    private GridPane initializeGridPane() {
        GridPane inputGrid = new GridPane();
        inputGrid.add(new Label("Date:"), 0, 0);
        inputGrid.add(datePicker, 1, 0);
        inputGrid.add(new Label("Israel Lines:"), 0, 1);
        inputGrid.add(israelInput, 1, 1);
        inputGrid.add(new Label("Gaza Lines:"), 0, 2);
        inputGrid.add(egyptInput, 1, 2);
        inputGrid.add(new Label("Egypt Lines:"), 0, 3);
        inputGrid.add(gazaInput, 1, 3);
        inputGrid.add(new Label("Over All Demand:"), 0, 4);
        inputGrid.add(overallDemandInput, 1, 4);
        inputGrid.add(new Label("Power Cuts:"), 0, 5);
        inputGrid.add(powerCutsHoursDayInput, 1, 5);
        inputGrid.add(new Label("Temp:"), 0, 6);
        inputGrid.add(tempInput, 1, 6);
        buttonBox.getChildren().addAll(addBtn, updateBtn, deleteBtn, searchBtn);
        inputGrid.add(buttonBox, 1, 7);
        inputGrid.setPadding(new Insets(16));
        inputGrid.setVgap(8);
        inputGrid.setHgap(8);
        return inputGrid;
    }

    // add event handlers to buttons
    private void handle() {
        addBtn.setOnAction(e -> {
            try {
                // Get the record from the list
                ElectricityRecord newRecord = getRecord();
                // Add the record to the list
                list.add(newRecord);
                // Update the table view
                updateTableView();
                // Clear the text fields
                clear();
            } catch (IllegalArgumentException ex) {
                // display error message
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                // display error message
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        updateBtn.setOnAction(e -> {
            try {
                // Get the record from the list
                ElectricityRecord updatedRecord = getRecord();
                // Update the record in the list
                list.update(updatedRecord);
                // Update the table view
                updateTableView();
                tableView.setEditable(true);
                // Clear the text fields
                clear();
            } catch (IllegalArgumentException ex) {
                // Show an error alert if the date is invalid
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                // Show an error alert if the date is not entered
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        deleteBtn.setOnAction(e -> {
            try {
                // remove the record from the list
                list.remove(getDate());
                // update the table view
                updateTableView();
                // clear the text fields
                clear();
            } catch (IllegalArgumentException ex) {
                // Show an error alert if the date is invalid
                alert(AlertType.ERROR, "Error", ex.getMessage());
            } catch (NullPointerException ex) {
                // Show an error alert if the date is not entered
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        searchBtn.setOnAction(e -> {
            try {
                // search for the record in the list
                ElectricityRecord record = list.search(getDate());
                // if the record is found
                if (record != null) {
                    // create an observable list of the record
                    ObservableList<ElectricityRecord> list1 = FXCollections.observableArrayList(record);
                    // set the items of the table view to the observable list
                    tableView.setItems(list1);
                } else
                    // if no record is found, show a warning
                    alert(AlertType.WARNING, "Warning", "No record found for the given date");
                // clear the text fields
                clear();
            } catch (NullPointerException ex) {
                // if the date is empty, show an error
                alert(AlertType.ERROR, "Error", "Please enter date");
            }
        });
        backBtn.setOnAction(e -> {
            clear();
            SceneChanger.changeScene(new MainScreen(list));
            updateTableView();
        });
    }

    // Methods for initializing table
    private TableView<ElectricityRecord> initializeTable() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        israeliLinesCol.setCellValueFactory(new PropertyValueFactory<>("israeliLines"));
        gazaPlantCol.setCellValueFactory(new PropertyValueFactory<>("gazaPowerPlant"));
        egyptianLinesCol.setCellValueFactory(new PropertyValueFactory<>("egyptianLines"));
        totalSupplyCol.setCellValueFactory(new PropertyValueFactory<>("totalSupply"));
        overallDemandCol.setCellValueFactory(new PropertyValueFactory<>("overallDemand"));
        powerCutsCol.setCellValueFactory(new PropertyValueFactory<>("powerCutsHoursDay"));
        tempCol.setCellValueFactory(new PropertyValueFactory<>("temp"));
        tableView.getColumns().addAll(dateCol, israeliLinesCol, gazaPlantCol,
                egyptianLinesCol, totalSupplyCol, overallDemandCol, powerCutsCol, tempCol);
        dateCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        israeliLinesCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        gazaPlantCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        egyptianLinesCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        totalSupplyCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        overallDemandCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        powerCutsCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        tempCol.prefWidthProperty().bind(tableView.widthProperty().divide(8));
        tableView.setEditable(true);
        updateTableView();
        editTable();
        return tableView;
    }

    public void editTable() {
        israeliLinesCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        israeliLinesCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setIsraeliLines(newValue);
            }
        });
        gazaPlantCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        gazaPlantCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setGazaPowerPlant(newValue);
            }
        });
        egyptianLinesCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        egyptianLinesCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setEgyptianLines(newValue);
            }
        });
        overallDemandCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        overallDemandCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setOverallDemand(newValue);
            }
        });
        powerCutsCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        powerCutsCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setPowerCutsHoursDay(newValue);
            }
        });
        tempCol.setCellFactory(
                TextFieldTableCell.<ElectricityRecord, Double>forTableColumn(new DoubleStringConverter()));
        tempCol.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) {
                ElectricityRecord record = event.getRowValue();
                record.setTemp(newValue);
            }
        });
    }

    // Methods for fill and update table
    private void updateTableView() {
        if (tableView != null) {
            tableView.getItems().clear();
        }
        for (Year year : list.getRecords()) {
            for (Month month : year.getMonths()) {
                for (Day day : month.getDays()) {
                    tableView.getItems().addAll(day.getRecord());
                }
            }
        }
    }

    // get date from date picker
    private LocalDate getDate() {
        return datePicker.getValue();
    }

    // Methods for styling
    private void style() {
        setStyle("-fx-background-color:#ffffff");
        Image image = new Image(
                "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\left-arrow.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        backBtn.setGraphic(imageView);
        backBtn.setStyle("-fx-background-color: transparent;");
        buttonBox.setPadding(new Insets(16));
        buttonBox.setSpacing(16);
        vLeft.setMaxWidth(520);
        vLeft.setMinWidth(520);
        tableView.setMaxWidth(880);
        tableView.setMinWidth(880);
    }

    // Methods for clear
    private void clear() {
        datePicker.setValue(null);
        israelInput.clear();
        gazaInput.clear();
        egyptInput.clear();
        overallDemandInput.clear();
        powerCutsHoursDayInput.clear();
        tempInput.clear();
    }

    // get records from text fields
    private ElectricityRecord getRecord() {
        double israeliLines = Double.parseDouble(israelInput.getText());
        double gazaPowerPlant = Double.parseDouble(gazaInput.getText());
        double egyptianLines = Double.parseDouble(egyptInput.getText());
        double overallDemand = Double.parseDouble(overallDemandInput.getText());
        double powerCutsHoursDay = Double.parseDouble(powerCutsHoursDayInput.getText());
        double temp = Double.parseDouble(tempInput.getText());
        ElectricityRecord record = new ElectricityRecord(
                getDate(),
                israeliLines,
                gazaPowerPlant,
                egyptianLines,
                overallDemand,
                powerCutsHoursDay,
                temp);
        return record;
    }

    // Methods for alert
    private void alert(Alert.AlertType type, String title, String message) {
        // Create an alert with the given type, title, and message
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        // Show the alert
        alert.show();
    }
}