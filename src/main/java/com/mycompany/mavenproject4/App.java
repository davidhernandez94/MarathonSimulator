package com.mycompany.mavenproject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    protected static Marathoner[] runners = new Marathoner[4];

    @Override
    public void start(Stage stage) throws IOException {
        makeRunners();
        scene = new Scene(loadFXML("main"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void makeRunners() {
        String[] names = {"Gideon", "Alphonse", "Jean-Paul", "Mohammed"};
        Image[] images = new Image[4];
        for (int i = 0; i < 4; i++) {
            images[i] = new Image("file:bjorkImages/bjork" + (i + 1) + ".jpg");
            runners[i] = new Marathoner(names[i], images[i]);
        }
    }
}