package projet.cpoo.main;

import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


import javafx.scene.layout.BorderPane;
import javafx.stage.*;

import projet.cpoo.game.*;


public class Main /*extends Application*/{

    private Stage primaryStage;
    private BorderPane rootLayout;

    
    public static void main(String[] args) throws IOException{
        //launch(args); //Fonction launch de la classe Application
        Game g = Game.newNormalGame(0, "test",100);
        Joueur j = g.getJoueur();
        //System.out.println(j.getNom());
        //System.out.println(j.getListWord().affichage_first_words());
        System.out.println(j.getListWord().getWordGenerator().generateNewWord());
        System.out.println(j.getListWord().getTampon().toString());
        String s = j.getListWord().getTampon().get(0);
        System.out.println(j.getListWord().getTampon().toString());

        char[] tc = new char[s.length()];
        for ( int i = 0 ;i < tc.length;i++){
            tc[i] = s.charAt(i);
            tc[0] = '(';
        }
        g.validationWord(tc);
        System.out.println(g.getGameMode());
        System.out.println(j.getListWord().getTampon().toString());
        System.out.println(j.getStat().getGoodWord());
        System.out.println(j.getStat().getGoodKey());
        System.out.println(j.getStat().getFalseKey());
        System.out.println(j.getStat().getAccuracy());


        Timer chrono = new Timer();
        
        Game.startNewGame(60, 0, "test", 0, 0, GameMode.Normal);
    /*
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Coucou");

        initRootLayout(); //On va mettre la page de base dans le Stage (JFrame si j'ai bien compris)

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(); //On charge le fichier fxml, on l'extrait et on le "load" au BorderPane en paramètre du main
            URL url = Main.class.getResource("/test.fxml"); //todo : modifier le chemin du fichier et trouver le bon
            loader.setLocation(url);
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //On rajoute la scene contenant le BorderPane d'avant et on l'a met dans la fenêtre
            primaryStage.setScene(scene);
            primaryStage.show(); //On affiche la fenêtre sinon ça marche pas 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Dans le cas où il y a un seul layout de base, sinon on peut rajouter plein d'autre layout qui charge chacun un fichier fxml différent
    //On procède d'apeu près la même façon à chaque fois, juste il faut que ça reste du code "logique" en java, qui compile quoi
    //Après c'est haut dela de mes capacités en GUI ...

    public Stage getPrimaryStage(){
        return primaryStage;
    }*/
    }
}
