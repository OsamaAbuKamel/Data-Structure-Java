package com.example.projectiii;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticsScreen extends BorderPane {
    private RecordAVL list;
    private Button btnBack = new Button();
    private VBox vBox = new VBox();
    private TextField tfInput = new TextField();
    private RadioButton rbTotal = new RadioButton("TOTAL\t  ");
    private RadioButton rbAvg = new RadioButton("AVERAGE");
    private RadioButton rbMax = new RadioButton("MAXIMUM");
    private RadioButton rbMin = new RadioButton("MINIMUM");
    private ComboBox<ElectricityType> comboBox = new ComboBox<>();
    private ComboBox<String> comboBox1 = new ComboBox<>();
    private ComboBox<Months> cbMonths = new ComboBox<>();
    private VBox box = new VBox();
    private TextArea area = new TextArea();
    private Button btnResult = new Button("Result");
    private Statistics statistics;
    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private ToggleGroup group = new ToggleGroup();
    private GridPane gridPane = new GridPane();
    private HBox hBox = new HBox();
    private HBox hBox1 = new HBox();
    
    // Constructor
    public StatisticsScreen(RecordAVL list) {
        this.list = list;
        setStyle("-fx-background-color:#ffffff");
        getStylesheets().add(getClass().getResource("statisticStyle.css").toExternalForm());
        statistics = new Statistics(list);
        initialize();
        style();
        handle();
        setTop(btnBack);
        setLeft(vBox);
        setCenter(box);
    }
    
    // Initialize the screen
    private void initialize() {
        hBox.getChildren().addAll(rbTotal, rbAvg);
        hBox1.getChildren().addAll(rbMax, rbMin);
        gridPane.add(cbMonths, 0, 0);
        gridPane.add(tfInput, 0, 0);
        gridPane.add(hBox, 0, 1);
        gridPane.add(hBox1, 0, 2);
        comboBox.getItems().addAll(ElectricityType.values());
        comboBox1.getItems().addAll("Day", "Month", "Year");
        cbMonths.getItems().addAll(Months.values());
        vBox.getChildren().addAll(comboBox1, comboBox, gridPane, btnResult);
        box.getChildren().addAll(area, createChart());
        box.setPrefHeight(300);
        box.setPrefWidth(300);
        rbAvg.setToggleGroup(group);
        rbMax.setToggleGroup(group);
        rbMin.setToggleGroup(group);
        rbTotal.setToggleGroup(group);
        tfInput.setPromptText("Enter a number");
        cbMonths.setVisible(false);
        tfInput.setVisible(false);
    }
    
    private LineChart<Number, Number> createChart() {
        // Set X-axis label based on the selected time unit
        String timeUnit = comboBox1.getValue();
        xAxis.setLabel(timeUnit);
        yAxis.setLabel("Statistic Value");
        // Creating the line chart
        lineChart.setTitle("Statistics for " + timeUnit);
        // Creating the data series for the chart
        series.setName("Statistic Values");
        // Check if tfInput is not empty before parsing as an integer
        lineChart.getData().add(series);
        return lineChart;
    }
    
    private void handle() {
        // back button to main screen
        btnBack.setOnAction(e -> {
            clear();
            SceneChanger.changeScene(new MainScreen(list));
        });
        comboBox1.setOnAction(e -> {
            if ("Month".equals(comboBox1.getValue())) {
                cbMonths.setVisible(true);
                tfInput.setVisible(false);
            } else {
                cbMonths.setVisible(false);
                tfInput.setVisible(true);
            }
        });
        btnResult.setOnAction(e -> {
            try {
                // Check if all inputs are empty
                if (comboBox1.getValue() == null)
                    alert(Alert.AlertType.ERROR, "Error", "Please select a time unit");
                    // else if (tfInput.getText().isEmpty())
                    // alert(Alert.AlertType.ERROR, "Error", "Please enter a number");
                else if (comboBox.getValue() == null)
                    alert(Alert.AlertType.ERROR, "Error", "Please select an electricity type");
                else if (group.getSelectedToggle() == null)
                    alert(Alert.AlertType.ERROR, "Error", "Please select a statistic type");
                double value = 0;
                // Get the value from the selected year,month or day
                if (comboBox1.getValue() == "Day") {
                    value = statistics.getStatisticForDay(Integer.parseInt(tfInput.getText()),
                            comboBox.getValue(),
                            getType());
                } else if (comboBox1.getValue() == "Month") {
                    value = statistics.getStatisticForMonth(cbMonths.getValue(),
                            comboBox.getValue(),
                            getType());
                } else if (comboBox1.getValue() == "Year") {
                    value = statistics.getStatisticForYear(Integer.parseInt(tfInput.getText()),
                            comboBox.getValue(),
                            getType());
                }
                // Display the value in the text area
                area.setText(String.valueOf(value));
                // Check if the value is not null
                if (comboBox.getValue() != null && comboBox1.getValue() != null) {
                    // Set the title of the chart
                    lineChart.setTitle(comboBox.getValue() + " " + comboBox1.getValue() + " " + getType().toString());
                }
                // Add the value to the Line Chart
                String input = tfInput.getText();
                if (input != null && !input.isEmpty()) {
                    // Input is all digits
                    int value1 = Integer.parseInt(input);
                    series.getData().add(new XYChart.Data<>(value1, value));
                }
            } catch (IllegalArgumentException ex) {
                // Display Error
                alert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            }
        });
    }
    
    private StatisticType getType() {
        if (rbTotal.isSelected()) {
            return StatisticType.TOTAL;
        }
        if (rbAvg.isSelected()) {
            return StatisticType.AVERAGE;
        }
        if (rbMax.isSelected()) {
            return StatisticType.MAX;
        }
        if (rbMin.isSelected()) {
            return StatisticType.MIN;
        }
        return null;
    }
    
    private void style() {
        Image image = new Image(
                "C:\\Users\\osama\\DataStructure\\Data-Structure-\\ProjectIII\\src\\main\\resources\\com\\example\\projectiii\\left-arrow.gif");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        btnBack.setGraphic(imageView);
        btnBack.setStyle("-fx-background-color: transparent;");
        vBox.setPadding(new Insets(16));
        vBox.setSpacing(16);
        gridPane.setPadding(new Insets(16));
        gridPane.setHgap(16);
        gridPane.setVgap(16);
        vBox.prefWidthProperty().bind(this.widthProperty().divide(3));
        box.prefWidthProperty().bind(this.widthProperty().divide(2));
        btnResult.prefWidthProperty().bind(vBox.widthProperty().divide(2));
        comboBox.prefWidthProperty().bind(vBox.widthProperty().divide(2));
        comboBox1.prefWidthProperty().bind(vBox.widthProperty().divide(2));
        cbMonths.prefWidthProperty().bind(vBox.widthProperty().divide(2));
        gridPane.prefWidthProperty().bind(vBox.widthProperty().divide(2));
        tfInput.maxWidthProperty().bind(vBox.widthProperty().divide(2));
        tfInput.setMaxHeight(150);
        vBox.setAlignment(Pos.TOP_CENTER);
        gridPane.setAlignment(Pos.TOP_CENTER);
        box.setAlignment(Pos.TOP_CENTER);
        area.setMaxHeight(200);
        area.setMinHeight(200);
        lineChart.setMaxHeight(550);
        lineChart.setMinHeight(550);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(8);
        hBox1.setSpacing(8);
        hBox1.setAlignment(Pos.CENTER_LEFT);
    }
    
    private void clear() {
        comboBox.getItems().clear();
        comboBox1.getItems().clear();
        tfInput.clear();
        if (group.getSelectedToggle() != null) {
            group.getSelectedToggle().setSelected(false);
        }
    }
    
    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}