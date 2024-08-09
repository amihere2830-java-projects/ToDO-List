module com.example.todolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.amisam.todolist to javafx.fxml;
    exports com.amisam.todolist;
    exports com.amisam.todolist.engine;
    exports com.amisam.todolist.datamodel;
}