module com.example.todolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.donbilly.todolist to javafx.fxml;
    exports com.donbilly.todolist;
}