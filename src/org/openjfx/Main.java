/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Anthony Dera Student ID: 94088030
 */
public class Main extends Application {
    static private Stage tmpStage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Task1.fxml"));
             stage.setTitle("Assessment 3 Task 1: Grade Processing");    
        Scene scene = new Scene(root);
        tmpStage = stage;
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CreateDataBase.create();
        launch(args);
    }
    
    public static Stage getStage(){
        return tmpStage;
    }
    
}
