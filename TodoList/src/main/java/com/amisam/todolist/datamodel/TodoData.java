package com.amisam.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.amisam.todolist.App;
import com.amisam.todolist.engine.Storage;

public class TodoData {
    private static TodoData instance = new TodoData();
    public static Storage storage = App.getStorage(instance);
    
    // private static String filename = "TodoListItem.txt";

    private ObservableList<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    //Create a private constructor
    //this prevents the TodoData class from being instantiated
    //in order to access todoItems list
    public TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    // Add items to the TodoItems list
    public void addTodItem(TodoItem item){
        todoItems.add(item);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void loadTodoItems() throws IOException {
        try {
            this.todoItems = TodoData.storage.loadTodoItems();
        } catch (Exception e) {
            this.todoItems = FXCollections.observableArrayList();
        }
    }

    public void storeTodoItem() throws IOException{

        try {
            TodoData.storage.storeTodoItem();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTodoItem(TodoItem item){
        todoItems.remove(item);
    }
}
