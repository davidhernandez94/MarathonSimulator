/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 6309110
 */
public class IntroController implements Initializable {
    private static final AudioClip clip = new AudioClip("file:more_to_life_than_this.mp3");
    @FXML
    private Label welcomeLabel;
    @FXML
    private ImageView characterImageView;
    @FXML
    private Label characterName;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clip.play(); // plays music 
        characterImageView.setImage(App.runners[0].getImage());
        characterName.setText(App.runners[0].getName());
        int[] charIdx = {0};
        // rotating transition to show different players
        RotateTransition trans = new RotateTransition(new Duration(2000), characterImageView);
        trans.setFromAngle(0);
        trans.setToAngle(359);
        trans.play();
        // plays one animation at a time and launches next one
        // if it's final runner, change to main scene
        trans.setOnFinished(eh -> {
            if (charIdx[0] == 3) {
                try {
                    switchToMain();
                } catch (IOException ex) {
                    System.getLogger(IntroController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            } else {
                charIdx[0]++;
                characterImageView.setImage(App.runners[charIdx[0]].getImage());
                characterName.setText(App.runners[charIdx[0]].getName());
                trans.play();
            }
        });
    }
    
    /**
     * when intro slideshow is done, move on to marathon scene
     * @throws IOException 
     */
    public void switchToMain() throws IOException {
        clip.stop();
        App.setRoot("main");
    }
}
