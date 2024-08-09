package com.amisam.todolist.engine;

import com.amisam.todolist.datamodel.TodoItem;

import javafx.collections.ObservableList;

public class Storage {
    private IStorage storage;

    public Storage(IStorage storage) {
        this.storage = storage;
    }

    public void storeTodoItem() throws Exception {
        storage.storeTodoItem();
    }

    public ObservableList<TodoItem> loadTodoItems() throws Exception {
        return storage.loadTodoItems();
    }

}
