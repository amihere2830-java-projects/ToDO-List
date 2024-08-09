package com.amisam.todolist;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

import com.amisam.todolist.datamodel.TodoData;
import com.amisam.todolist.datamodel.TodoItem;

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