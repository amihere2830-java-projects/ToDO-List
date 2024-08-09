package com.amisam.todolist.engine;

import com.amisam.todolist.datamodel.TodoItem;

import javafx.collections.ObservableList;

public interface IStorage {
    String URL = "";
    void storeTodoItem() throws Exception;
    ObservableList<TodoItem> loadTodoItems() throws Exception;
}
