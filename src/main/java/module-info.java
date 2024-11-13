module com.example.assignemnt4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignemnt4 to javafx.fxml;
    exports com.example.assignemnt4;
}