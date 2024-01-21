package com.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CalculatorApplication extends Application {
    private double x;
    private double y;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("calc-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneX();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Calculator");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/calculator-svgrepo-com.png")));
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.requestFocus();
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}