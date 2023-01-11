package com.donbilly.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItem.txt";

    private ObservableList<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }

    //Create a private constructor
    //this prevents the TodoData class from being instantiated
    //in order to access todoItems list
    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    // Add items to the TodoItems list
    public void addTodItem(TodoItem item){
        todoItems.add(item);
    }

//    public void setTodoItems(List<TodoItem> todoItems) {
//        this.todoItems = todoItems;
//    }

    public void loadTodoItems() throws IOException {

        todoItems = FXCollections.observableArrayList(); //observable list is used
        Path path = Paths.get(filename);                // for performance reasons (https://docs.oracle.com/javase/8/javafx/api/javafx/collections/FXCollections.html)
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription, details, date);
                todoItems.add(todoItem);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public void storeTodoItem() throws IOException{

        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<TodoItem> iter = todoItems.iterator();
            while (iter.hasNext()){
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }

        } finally {
            if (bw != null){
                bw.close();
            }
        }
    }

    public void deleteTodoItem(TodoItem item){
        todoItems.remove(item);
    }
}
