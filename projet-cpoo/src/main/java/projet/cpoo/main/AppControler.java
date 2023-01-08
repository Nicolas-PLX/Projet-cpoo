package projet.cpoo.main;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import org.fxmisc.richtext.event.MouseStationaryEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import projet.cpoo.game.GameMode;


public final class AppControler {

    public final int max_pseudo_size = 20;

    private static String gameplay;
    
    private static Model model = new Model();
    @FXML
    private TextField pseudo; // Endroit où le joueur entre son pseudo

    @FXML
    private TextField mot_ecrit; // Endroit où le joueur tape son mot

    @FXML
    private TextArea liste_mots; // Endroit où les mots à taper sont afficher

    @FXML
    private TextArea description; // Endroit où la description des modes de jeu sera affiché

    @FXML
    private TextArea other_player_prgression; // Endroit où la description des modes de jeu sera affiché

    @FXML
    private Label timer; // Affichage du timer

    @FXML
    private Label nombre_mot_restant; // Affiche le nombre de mot restant à taper

    @FXML
    private Label vie;

    @FXML
    private Label nombre_mot_actuel;

    @FXML
    private Label niveau;

    @FXML 
    private Button start; //bouton start

    @FXML
    private Pane game_option_pane;

    @FXML
    private Pane game_pane;

    @FXML
    private ToggleGroup game_option;

    @FXML
    private TextField option_time;

    @FXML
    private TextField option_number_word;

    @FXML
    /* Affiche le menu du jeu */
    protected void showMenu(ActionEvent event) throws IOException {
        String SE = System.getProperty("os.name".toLowerCase()).substring(0, 3);
        if(SE.indexOf("Win") >= 0){
            App.changeStage((Node) event.getSource(), "/Menu.fxml");
        } else {
            App.changeStage((Node) event.getSource(), "Menu.fxml");
        }
    }

    /* Affiche la selection du mode de la partie */
    private void startGame(ActionEvent event, String filepath) throws IOException {
        String name = pseudo.getText();
        if(name.length() == 0) {
            pseudo.setPromptText("Set a username !");
        } else if(name.length() > max_pseudo_size) {
            pseudo.setText("");
            pseudo.setPromptText("Too long !");
        } else {
            App.changeStage((Node) event.getSource(), filepath);
        }
    }

    @FXML
    /**
     * permet l'utilisation du bouton start (lance la partie)
     * @param event quand le bouton start est pressé
     * */
    protected void startGame(ActionEvent event){
        String s = AppControler.model.printTampon();
        liste_mots.setText(s);
        mot_ecrit.setEditable(true);

        if (model.getGameMode() == GameMode.Jeu){
            vie.setText(Integer.toString(model.getLifePlayer()));
            niveau.setText(Integer.toString(model.getNiveau()));
            nombre_mot_actuel.setText(Integer.toString(model.getMotEcrit()));
            long time = System.currentTimeMillis();
            model.setPassedTime(time);
            model.resetLife();
        
        } else {

            timer.setText(Integer.toString(model.getTimeRemaining()));
            if (model.getGameMode() == GameMode.Normal){
                if (model.getWithTimer()){
                    model.startTimer();
                } else {
                }
            }
        }
        model.resetStat();    

    }

    protected String getPseudo(){
        return pseudo.getText();
    }

    @FXML
    /* Si on lance tout seul */
    protected void soloMode(ActionEvent event) throws IOException {
        if (AppControler.gameplay == "Normal"){
            model.newGameSolo(GameMode.Normal, getPseudo(),60,0,true);
            String SE = System.getProperty("os.name".toLowerCase()).substring(0, 3);
            if(SE.indexOf("Win") >= 0){
                startGame(event, "/SoloGame.fxml");
            }else {
                startGame(event, "SoloGame.fxml");
            }
        } else if(AppControler.gameplay == "Challenge"){
            model.newGameSolo(GameMode.Jeu, getPseudo(), 0, 0, false);
            String SE = System.getProperty("os.name".toLowerCase()).substring(0, 3);
            if(SE.indexOf("Win") >= 0){
                startGame(event, "/SoloGameChallenge.fxml");
            }else {
                startGame(event, "SoloGameChallenge.fxml");
            }
        }
       
    }

    @FXML
    /**change d'affichage après avoir choisi les options. vérifie les options si elles sont valable
     * @param event action de clique après avoir remplis les options
    */
    protected void changePane(ActionEvent event) {
        if (AppControler.gameplay == "Challenge"){
            game_option_pane.setVisible(false);
            game_pane.setVisible(true);
            return;
        }
        if ((option_number_word.getText().length() > 0) || (option_time.getText().length() > 0)){
            boolean b = true; int nb = 0; int duration = 60;
            if(option_time.getText().length() == 0){
                b = false;
            } else {
                try {
                    duration = Integer.parseInt(option_time.getText());
                } catch (NumberFormatException e) {
                    option_time.setPromptText("Veuillez choisir une valeur valable");
                }
            }
            if (option_number_word.getText().length() > 0){
                try {
                    nb = Integer.parseInt(option_number_word.getText());
                } catch (NumberFormatException e){
                    option_time.setPromptText("Veuillez choisir une valeur valable");
                }
            }
            model.newGameSolo(GameMode.Normal, model.getPseudo(), duration, nb,b);
            game_option_pane.setVisible(false);
            game_pane.setVisible(true);
        }
        
    } 

    @FXML
    /* S'occuper de la connexion au serveur pour les games en multi */
    protected void multiMode(ActionEvent event) throws IOException {
        /* TODO1 : Mettre un argument que la partie ce joue à plusieurs */
        /* TODO2 : S'occuper de la connexion au serveur */
        startGame(event, "");
    }

    @FXML
    /* Affichage de la description des différents modes de jeu */
    protected void showDescription(ActionEvent event) throws IOException {
        App.changeStage((Node) event.getSource(), "/Description.fxml");
    }

    /* Affiche l'interface de choix du mode de jeu */
    private void showGameSelection(ActionEvent event) throws IOException {
        App.changeStage((Node) event.getSource(), "/GameSelection.fxml");
    }

    @FXML
    /* Si on lance une game normal */
    protected void startNormalGame(ActionEvent event) throws IOException {
        /* TODO : Mettre en paramètre quelque part qu'on lance une game normal */
        showGameSelection(event);
        AppControler.gameplay = "Normal";
    }

    @FXML
    /* Si on lance un challenge */
    protected void startChallenge(ActionEvent event) throws IOException {
        /* TODO : Mettre en paramètre quelque part qu'on lance une game normal */
        showGameSelection(event);
        AppControler.gameplay = "Challenge";
    }

    private void clearWord(){
        mot_ecrit.setText("");
    }


    @FXML
    /**
     *  A chaque caractère pressé par le joueur, la fonction vérifie 
     * - si le jeu n'est pas terminé
     * - si c'est un espace, va valider le mot
     * - vérifie si il faut rajouter un mot pour le mode Jeu
     * */
    protected void checkKey(KeyEvent event) throws IOException {
        if (model.checkEndGame()){ //Vérifie si la partie n'est pas terminé
            clearWord();
            model.ajoutStatFinal();
            mot_ecrit.setEditable(false);
            liste_mots.setText(model.printStats());
        } else {
            long time = System.currentTimeMillis();     //Va prendre le temps ou la fonction est appelé (quand le joueur presse une touche)
            model.changementEcartType(time);            //Le rajoute dans le Stack pour l'écart type
            if (model.getGameMode() == GameMode.Jeu){       //Pour le mode jeu, va vérifier s'il faut rajouter un mot
                long passedTime = model.getPassedTime();
                double diffTime = (double)(time - passedTime) / 1000.0;
                if (diffTime >= model.getVitesse()){
                    model.ajoutWordGameModeChallenge(mot_ecrit.getText());
                    model.setPassedTime(time);
                }
                if (model.getMotEcrit() >= 100 * model.getNiveau()){ // vérifie s'il ne faut pas changer de niveau également
                    model.changementNiveau();
                }
            }
            String s = "|" + AppControler.model.printTampon(); 
            liste_mots.setText(s);  //Met à jour la liste de mots
            if(event.getCharacter().compareTo(" ") == 0) { //Valide le mot si besoin
                String mot = mot_ecrit.getText();
                AppControler.model.ValidationMot(mot.substring(0, mot.length()-1));
                refresh_Affichage();
                clearWord();
            } else { //Si ce n'est pas un espace, rafrachit l'écran
                refresh_Affichage();
            }
    }
    }
    /**
     * Fonction qui va rafraichir l'écran, en mettant à jour le tampon, le timer, le nombre de vies, le nombre de mots ...
     */
    protected void refresh_Affichage(){
        String s = "|" + AppControler.model.printTampon();
        liste_mots.setText(s);
        if (model.getGameMode() == GameMode.Normal){
            nombre_mot_restant.setText(Integer.toString(model.getNbWord()));
            timer.setText(Integer.toString(model.getTimeRemaining()));

        } else {
            vie.setText(Integer.toString(model.getLifePlayer()));
            niveau.setText(Integer.toString(model.getNiveau()));
            nombre_mot_actuel.setText(Integer.toString(model.getMotEcrit()));
        }
        
    }

    @FXML
    /* Afficher les règles du jeu */
    protected void RulesDescription(ActionEvent event) {
        description.setText(Description.rules);
    }

    @FXML
    /* Afficher le fonctionnement du mode multi-joueur */
    protected void MultiGameDescription(ActionEvent event) {
        description.setText(Description.multi);
    }

    @FXML
    /* Affiche le fonctionnement le mode solo */
    protected void SoloGameDescription(ActionEvent event) {
        description.setText(Description.solo);
    }

    @FXML
    /* Affiche le fonctionnement jeu en mode normal */
    protected void NormalGameDescription(ActionEvent event) {
        description.setText(Description.normal);
    }

    @FXML
    /* Affiche le fonctionnement du jeu en mode challenge */
    protected void ChallengeGameDescription(ActionEvent event) {
        description.setText(Description.challenge);
    }


    @FXML
    /* Quitter l'application */
    protected void exit(ActionEvent event) {
        App.exit();
    }
    
}
