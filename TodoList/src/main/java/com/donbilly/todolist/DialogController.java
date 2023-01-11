package com.donbilly.todolist;

import com.donbilly.todolist.datamodel.TodoData;
import com.donbilly.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    // Properties
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;



    //Methods
    @FXML
    public TodoItem processResults(){
        // Retrieves data from the dialog
        String shortDescription = shortDescriptionField.getText();
        String details = detailsArea.getText();
        LocalDate deadlineValue = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescription, details, deadlineValue);
        TodoData.getInstance().addTodItem(newItem);
        return newItem;
    }
}