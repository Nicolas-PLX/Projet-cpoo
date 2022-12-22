package projet.cpoo.main;

import java.io.*;
import java.net.URL;

import org.checkerframework.common.reflection.qual.GetClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

import projet.cpoo.game.*;
import projet.cpoo.statistic.Statistic;
import projet.cpoo.wordGenerator.WordGenerator;


public class Main extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

    
    public static void main(String[] args) throws IOException{
        /*/
        WordGenerator wg = new WordGenerator("test, oui");
        System.out.println(wg.generateNewWord());
        WordGenerator wg2 = WordGenerator.generatorFromFile();
        for (int i = 0; i < wg2.getDicoLength();i++){
            System.out.println(wg2.getDico()[i]);
        }
        */
        //launch(args);
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("src/main/resources/test.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }*/
}
