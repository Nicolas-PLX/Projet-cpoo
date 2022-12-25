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
        //launch(args); //Fonction launch de la classe Application
        Joueur j = new Joueur("test");
        while(1){
            j.appuieJoueur(null);
        }
       
    }

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
    }
}
