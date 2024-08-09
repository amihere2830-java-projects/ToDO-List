package com.amisam.todolist.engine;

import com.amisam.todolist.datamodel.TodoItem;

import javafx.collections.ObservableList;

public class DBStorage implements IStorage {
    private String URL = "jdbc:mysql://localhost:3306/todolist";

    @Override
    public void storeTodoItem() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public ObservableList<TodoItem> loadTodoItems() throws Exception {
        return null;
        // TODO Auto-generated method stub

    }
    
}
