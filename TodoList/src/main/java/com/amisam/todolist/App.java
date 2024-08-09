package com.amisam.todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import com.amisam.todolist.datamodel.TodoData;
import com.amisam.todolist.engine.DBStorage;
import com.amisam.todolist.engine.FileStorage;
import com.amisam.todolist.engine.Storage;

public class App extends Application {

    public static String filename = "TodoListItem.txt";
    public static String storageType = "file";


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainwindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 877, 533);
        
        Image icon = new Image(getClass().getResourceAsStream("logo1.png"));
        stage.getIcons().add(icon);
        
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

    public static Storage getStorage(TodoData todoData) {
        return storageType.equals("file") ? 
                new Storage(new FileStorage(todoData)) :
                    new Storage(new DBStorage());
    }
}