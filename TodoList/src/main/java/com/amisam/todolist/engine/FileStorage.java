package com.amisam.todolist.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;

import com.amisam.todolist.App;
import com.amisam.todolist.datamodel.TodoData;
import com.amisam.todolist.datamodel.TodoItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileStorage implements IStorage {
    private String URL = App.filename;
    private TodoData todoData;
    private ObservableList<TodoItem> todoItems;

    public FileStorage(TodoData todoData) {
        this.todoData = todoData;
    }

    public TodoData getTodoData() {
        return this.todoData;
    }

    public void addTodItem(TodoItem item){
        todoItems.add(item);
    }

    @Override
    public void storeTodoItem() throws Exception {
        Path path = Paths.get(App.filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<TodoItem> iter = todoItems.iterator();
            while (iter.hasNext()){
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(todoData.getFormatter())));
                bw.newLine();
            }

        } finally {
            if (bw != null){
                bw.close();
            }
        }
    }

    @Override
    public ObservableList<TodoItem> loadTodoItems() throws Exception {
        this.todoItems = FXCollections.observableArrayList(); //observable list is used
        Path path = Paths.get(this.URL); 
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                if (itemPieces.length < 1) break;

                try {
                    String shortDescription = itemPieces[0];
                    String details = itemPieces[1];
                    String dateString = itemPieces[2];
    
                    LocalDate date = LocalDate.parse(dateString, todoData.getFormatter());
                    TodoItem todoItem = new TodoItem(shortDescription, details, date);
                    todoItems.add(todoItem);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return todoItems;
    }
    
}
