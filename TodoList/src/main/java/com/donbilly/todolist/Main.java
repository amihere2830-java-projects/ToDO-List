package com.donbilly.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.donbilly.todolist.datamodel.TodoData;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 877, 533);
        stage.setTitle("Todo List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        try{
            TodoData.getInstance().storeTodoItem();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //override the init method to load the TodoItems from file
    @Override
    public void init() throws Exception {
        try{
            TodoData.getInstance().loadTodoItems();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}