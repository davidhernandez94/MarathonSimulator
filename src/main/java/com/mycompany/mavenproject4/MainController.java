/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 6309110
 */
public class MainController implements Initializable {

    private TranslateTransition[] transs = new TranslateTransition[4];
    private ParallelTransition para;
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
        Duration[] durations = new Duration[4];
        for (int i = 0; i < 4; i++) {
            durations[i] = new Duration(3000 + rand.nextInt(3000));
        }
        transs[0] = new TranslateTransition(durations[0], runner1);
        transs[1] = new TranslateTransition(durations[1], runner2);
        transs[2] = new TranslateTransition(durations[2], runner3);
        transs[3] = new TranslateTransition(durations[3], runner4);
        for (int i = 0; i < 4; i++) {
            transs[i].setFromX(70);
            transs[i].setToX(420);
        }
        para = new ParallelTransition(transs);
        para.play();
        para.setOnFinished(eh -> ending());
    }
    
    private void ending() {
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

    @FXML
    private void onPlayPressed(ActionEvent event) {
        para.play();
    }

    @FXML
    private void onExitPressed(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void onPausePressed(ActionEvent event) {
        para.pause();
    }
    
}
