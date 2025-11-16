/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 6309110
 */
public class MainController implements Initializable {

    @FXML
    private GridPane finishLine;
    @FXML
    private Button playButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button pauseButton;
    @FXML
    private ImageView runner1;
    @FXML
    private ImageView runner4;
    @FXML
    private ImageView runner3;
    @FXML
    private ImageView runner2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeFinishLine();
        race();
    }    
    
    private void makeFinishLine() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if ((i + j) % 2 == 0) {
                    StackPane rect = new StackPane();
                    rect.setStyle("-fx-background-color: black;");
                    finishLine.add(rect, i, j);
                }
            }
        }
    }
    
    private void race() {
        
        
        runner1.setImage(App.runners[0].getImage());
        runner2.setImage(App.runners[1].getImage());
        runner3.setImage(App.runners[2].getImage());
        runner4.setImage(App.runners[3].getImage());
        Random rand = new Random();
        TranslateTransition trans1 = new TranslateTransition(new Duration(3000 + rand.nextInt(3000)), runner1);
        TranslateTransition trans2 = new TranslateTransition(new Duration(3000 + rand.nextInt(3000)), runner2);
        TranslateTransition trans3 = new TranslateTransition(new Duration(3000 + rand.nextInt(3000)), runner3);
        TranslateTransition trans4 = new TranslateTransition(new Duration(3000 + rand.nextInt(3000)), runner4);
        ParallelTransition para = new ParallelTransition(trans1, trans2, trans3, trans4);
        trans1.setFromX(70);
        trans1.setToX(420);
        trans2.setFromX(70);
        trans2.setToX(420);
        trans3.setFromX(70);
        trans3.setToX(420);
        trans4.setFromX(70);
        trans4.setToX(420);
        para.play();
    }

    @FXML
    private void onPlayPressed(ActionEvent event) {
    }

    @FXML
    private void onExitPressed(ActionEvent event) {
    }

    @FXML
    private void onPausePressed(ActionEvent event) {
    }
    
}
