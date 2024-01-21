package com.example.calculator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CalculatorController {
    String numberOne;
    boolean newDigit =true;
    @FXML
    private ImageView maxIcon;
    @FXML
    private BorderPane rootPane;
    @FXML
    private TextField display;
    Stage myStage = null;

    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void maxSize(MouseEvent event) {
        myStage = (Stage) rootPane.getScene().getWindow();
        if(myStage.isMaximized()){
            myStage.setMaximized(false);
            maxIcon.setImage(new Image(getClass().getResourceAsStream("images/resize-out-svgrepo-com.png")));
        }else  {
            myStage.setMaximized(true);
            maxIcon.setImage(new Image(getClass().getResourceAsStream("images/resize-in-svgrepo-com.png")));
        }
    }

    @FXML
    void minSize(MouseEvent event) {
        myStage = (Stage) rootPane.getScene().getWindow();
        myStage.setIconified(true);
    }

    @FXML
    void pressNumber(ActionEvent event){
        Button btn =  (Button)event.getSource();
        String tmp = display.getText();
        if(Double.parseDouble(tmp) == 0 || newDigit) {
            display.setText(btn.getText());
            newDigit = false;
        }else{
            myStage = (Stage) rootPane.getScene().getWindow();
            if(tmp.length()*17>display.getWidth()) myStage.setWidth(myStage.getWidth()+17);
            display.setText(display.getText() + btn.getText());
        }
    }

    @FXML
    public void calC(ActionEvent event){
        numberOne = null;
        display.setText("0");
    }

    @FXML
    public void calCE(ActionEvent event){
        display.setText("0");
    }

    @FXML
    public void callPoint(ActionEvent event){
        String tmp = display.getText();
        if(tmp.indexOf('.')==-1) display.setText(tmp + ".");
    }

    long factorial(long num){
        if(num>1) return num*factorial(num-1);
        else return 1;
    }

    @FXML
    public void callFactorial(ActionEvent event){
        String tmp = display.getText();
        if(tmp.indexOf('.')!=-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Faktoriál");
            alert.setHeaderText("Chyba při práci s faktoriálem.");
            alert.setContentText("Faktoriál je definován pouze z celých čísel.");
            alert.showAndWait();
            return;
        }
        long num = Integer.parseInt(tmp);
        display.setText(String.valueOf(factorial(num)));
    }

    @FXML
    public void callOp(ActionEvent event){
        if(numberOne != null){
            char operation = numberOne.charAt(numberOne.length()-1);
            double number = Double.parseDouble(numberOne.substring(0, numberOne.length()-1));
            double numberTwo = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation){
                case '+': result  = number + numberTwo;
                    break;
                case '-': result  = number - numberTwo;
                    break;
                case '/': result  = number / numberTwo;
                    break;
                case '*': result  = number * numberTwo;
                    break;
            }
            display.setText(String.valueOf(result));
        }
        newDigit = true;
        numberOne = display.getText() + ((Button) event.getSource()).getText();
    }

    @FXML
    void callEqual(ActionEvent event) {
        if(numberOne != null){
            char operation = numberOne.charAt(numberOne.length()-1);
            double number = Double.parseDouble(numberOne.substring(0, numberOne.length()-1));
            double numberTwo = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation){
                case '+': result  = number + numberTwo;
                    break;
                case '-': result  = number - numberTwo;
                    break;
                case '/': result  = number / numberTwo;
                    break;
                case '*': result  = number * numberTwo;
                    break;
            }
            display.setText(String.valueOf(result));
            numberOne = null;
        }
        newDigit = true;
    }

    @FXML
    void callPercentage(ActionEvent event) {
        if(numberOne != null){
            char operation = numberOne.charAt(numberOne.length()-1);
            double number = Double.parseDouble(numberOne.substring(0, numberOne.length()-1));
            double numberTwo = Double.parseDouble(display.getText());
            double result = 0;
            switch (operation){
                case '+': result  = number *((100+numberTwo)/100);
                    break;
                case '-': result  = number - ((number/100)*numberTwo);
                    break;
                case '/': result  =  number / (numberTwo/100);
                    break;
                case '*': result  = (number/100) * numberTwo;
                    break;
            }
            display.setText(String.valueOf(result));
            numberOne = null;
        }
        newDigit = true;
    }
}