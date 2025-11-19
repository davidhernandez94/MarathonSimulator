/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 6309110
 */
public class MainController implements Initializable {

    private TranslateTransition[] transs = new TranslateTransition[8];
    private ParallelTransition para;
    private static final AudioClip clip1 = new AudioClip("file:i_miss_you.mp3");
    @FXML
    private GridPane finishLine;
    @FXML
    private ImageView runner1;
    @FXML
    private ImageView runner4;
    @FXML
    private ImageView runner3;
    @FXML
    private ImageView runner2;
    @FXML
    private Pane resultPane;
    @FXML
    private Label resultLabel;
    @FXML
    private ImageView resultImgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeFinishLine();
        race();
    }    
    
    /**
     * make black and white finish line at the end of the track 
     * by making a GridPane and putting black panes inside every second one
     */
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
    
    /**
     * the marathon simulation:
     * each runner has a horizontal transition and a vertical transition
     * all of them are in para 
     * speed of each is a random number from 3000-6000
     * plays audio clip during race
     */
    private void race() {
        clip1.play();
        runner1.setImage(App.runners[0].getImage());
        runner2.setImage(App.runners[1].getImage());
        runner3.setImage(App.runners[2].getImage());
        runner4.setImage(App.runners[3].getImage());
        
        Random rand = new Random();
        Duration[] durations = new Duration[5];
        for (int i = 0; i < 4; i++) {
            durations[i] = new Duration(3000 + rand.nextInt(3000));
        }
        transs[0] = new TranslateTransition(durations[0], runner1);
        transs[1] = new TranslateTransition(durations[1], runner2);
        transs[2] = new TranslateTransition(durations[2], runner3);
        transs[3] = new TranslateTransition(durations[3], runner4);
        transs[4] = new TranslateTransition(new Duration(500), runner1);
        transs[5] = new TranslateTransition(new Duration(200), runner2);
        transs[6] = new TranslateTransition(new Duration(400), runner3);
        transs[7] = new TranslateTransition(new Duration(300), runner4);
        for (int i = 4; i < 8; i++) {
            transs[i].setAutoReverse(true);
            transs[i].setCycleCount(Animation.INDEFINITE);
            transs[i].setFromY((i - 4) * 3);
            transs[i].setToY((i - 4) * 3 + 10);
        }
        
        for (int i = 0; i < 4; i++) {
            transs[i].setFromX(0);
            transs[i].setToX(420);
            transs[i].setOnFinished(eh -> ending());
        }
        para = new ParallelTransition(transs);
    }
    
    /**
     * displays winner message and shows who won
     */
    private void ending() {
        // iterates through the list of players and finds the fastest one
        int winIdx = 0;
        for (int i = 0; i < 4; i++) {
            if (transs[winIdx].getDuration().greaterThan(transs[i].getDuration())) {
                winIdx = i;
            }
        }
        Marathoner winner = App.runners[winIdx];
        resultPane.setVisible(true);
        resultLabel.setText(winner.getName() + " wins!!!!");
        resultImgView.setImage(winner.getImage());
    }

    /**
     * when play button is pressed, play animation
     * @param event action event
     */
    @FXML
    private void onPlayPressed(ActionEvent event) {
        para.play();
    }

    /**
     * when exit button is pressed, exit scene
     * @param event action event
     */
    @FXML
    private void onExitPressed(ActionEvent event) {
        Platform.exit();
    }

    /**
     * when pause button is pressed, pause animation
     * @param event action event
     */
    @FXML
    private void onPausePressed(ActionEvent event) {
        para.pause();
    }
}
