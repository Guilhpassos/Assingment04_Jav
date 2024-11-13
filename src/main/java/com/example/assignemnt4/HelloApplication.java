package com.example.assignemnt4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.util.HashSet;
import java.util.Set;

public class HelloApplication extends Application {

    private TextField nameField, addressField, cityField, provinceField, postalField, phoneField, emailField;
    private RadioButton compSciRadio, businessRadio;
    private ComboBox<String> courseComboBox;
    private ListView<String> selectedCoursesList;
    private Set<String> selectedCoursesSet;
    private TextArea displayArea;
    private CheckBox studentCouncil, volunteerWork;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information");

        // Initialize fields
        nameField = new TextField();
        addressField = new TextField();
        cityField = new TextField();
        provinceField = new TextField();
        postalField = new TextField();
        phoneField = new TextField();
        emailField = new TextField();

        // Radio buttons for major selection
        compSciRadio = new RadioButton("Computer Science");
        businessRadio = new RadioButton("Business");
        ToggleGroup majorGroup = new ToggleGroup();
        compSciRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        // ComboBox for courses based on selected major
        courseComboBox = new ComboBox<>();
        selectedCoursesSet = new HashSet<>();
        selectedCoursesList = new ListView<>();
        selectedCoursesList.setPrefHeight(80);

        // Populate courseComboBox based on major selection
        compSciRadio.setOnAction(e -> loadCourses("Computer Science"));
        businessRadio.setOnAction(e -> loadCourses("Business"));

        // Add selected course to ListView and ensure no duplicates
        courseComboBox.setOnAction(e -> {
            String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
            if (selectedCourse != null && selectedCoursesSet.add(selectedCourse)) {
                selectedCoursesList.getItems().add(selectedCourse);
            }
        });

        // Checkboxes for additional student information
        studentCouncil = new CheckBox("Student Council");
        volunteerWork = new CheckBox("Volunteer Work");

        // Text area to display student information with scroll
        displayArea = new TextArea();
        displayArea.setEditable(false);
        displayArea.setWrapText(true);
        ScrollPane scrollPane = new ScrollPane(displayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(100);

        // Display button
        Button displayButton = new Button("Display");
        displayButton.setOnAction(e -> displayStudentInfo());

        // Layouts
        GridPane inputPane = new GridPane();
        inputPane.setPadding(new Insets(10));
        inputPane.setHgap(10);
        inputPane.setVgap(10);

        // Add components to GridPane in 3 columns layout
        inputPane.add(new Label("Name:"), 0, 0);
        inputPane.add(nameField, 1, 0);
        inputPane.add(new Label("Address:"), 0, 1);
        inputPane.add(addressField, 1, 1);
        inputPane.add(new Label("Province:"), 0, 2);
        inputPane.add(provinceField, 1, 2);
        inputPane.add(new Label("City:"), 0, 3);
        inputPane.add(cityField, 1, 3);
        inputPane.add(new Label("Postal Code:"), 0, 4);
        inputPane.add(postalField, 1, 4);
        inputPane.add(new Label("Phone Number:"), 0, 5);
        inputPane.add(phoneField, 1, 5);
        inputPane.add(new Label("Email:"), 0, 6);
        inputPane.add(emailField, 1, 6);

        // Second column for checkboxes
        VBox activitiesBox = new VBox(10, studentCouncil, volunteerWork);
        inputPane.add(activitiesBox, 2, 0, 1, 3);

        // Third column for radio buttons and course selection
        VBox courseBox = new VBox(10, compSciRadio, businessRadio, new Label("Select Course:"), courseComboBox);
        inputPane.add(courseBox, 3, 0, 1, 4);

        // ListView for selected courses in the third column
        inputPane.add(new Label("Selected Courses:"), 3, 4);
        inputPane.add(selectedCoursesList, 3, 5);

        // Display button on top of the display area
        VBox displayBox = new VBox(10, displayButton, scrollPane);
        displayBox.setPadding(new Insets(10));

        // Main layout with input and display sections
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));
        mainPane.setTop(inputPane);
        mainPane.setCenter(displayBox);

        // Scene and Stage setup
        Scene scene = new Scene(mainPane, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to load courses based on the selected major
    private void loadCourses(String major) {
        courseComboBox.getItems().clear();
        selectedCoursesList.getItems().clear();
        selectedCoursesSet.clear();
        if (major.equals("Computer Science")) {
            courseComboBox.getItems().addAll("Python", "C#", "Java");
        } else if (major.equals("Business")) {
            courseComboBox.getItems().addAll("Accounting", "Marketing", "Finance", "Business Law");
        }
    }

    // Method to display student information
    private void displayStudentInfo() {
        StringBuilder info = new StringBuilder();
        info.append(nameField.getText()).append(", ");
        info.append(addressField.getText()).append(", ");
        info.append(cityField.getText()).append(", ");
        info.append(provinceField.getText()).append(", ");
        info.append(postalField.getText()).append(", ");
        info.append(phoneField.getText()).append(", ");
        info.append(emailField.getText()).append("\n");

        info.append("Courses: ");
        for (String course : selectedCoursesList.getItems()) {
            info.append(course).append(" ");
        }

        displayArea.setText(info.toString());
    }
}
