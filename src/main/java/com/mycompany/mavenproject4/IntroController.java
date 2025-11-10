/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author 6309110
 */
public class IntroController implements Initializable {
    private Image[] characters = new Image[4];
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
        for (int i = 0; i < 4; i++) {
            characters[i] = new Image("file:src/bjorkImages/bjork1.jpg");
        }
        int charIdx = 1;
        RotateTransition trans = new RotateTransition(new Duration(2000), characterImageView);
        trans.setFromAngle(0);
        trans.setToAngle(359);
        trans.play();
//        trans.setOnFinished(eh -> {
//            
//        });
        
        characterImageView.setImage(new Image("file:bjorkImages/bjork1.jpg"));
    }
}
