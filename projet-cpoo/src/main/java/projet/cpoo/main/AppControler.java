package projet.cpoo.main;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class AppControler {

    public final int max_pseudo_size = 20;

    @FXML
    private TextField pseudo; // Endroit où je joueuer entre son pseudo

    @FXML
    /* Affiche le menu du jeu */
    protected void showMenu(ActionEvent event) throws IOException {
        App.changeStage((Node) event.getSource(), "/Menu.fxml");
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
    /* Si on lance tout seul */
    protected void soloMode(ActionEvent event) throws IOException {
        /* TODO : Mettre un argument que la partie ce joue tout seul */
        startGame(event, "");
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
    }

    @FXML
    /* Si on lance un challenge */
    protected void startChallenge(ActionEvent event) throws IOException {
        /* TODO : Mettre en paramètre quelque part qu'on lance une game normal */
        showGameSelection(event);
    }

    @FXML
    protected void exit(ActionEvent event) {
        App.exit();
    }
    
}
