package projet.cpoo.game;

import javafx.scene.input.KeyEvent;
import projet.cpoo.statistic.Statistic;

public class Joueur {
    private String nom;
    private Tampon listWord;
    private Statistic stat;


    public Joueur(String nom){
        this.nom = nom;
        this.listWord = new Tampon();
        this.stat = new Statistic();
    }

    public Statistic getStat(){
        return this.stat;
    }

    public String getNom(){
        return this.nom;
    }

    public Tampon getListWord(){
        return this.listWord;
    }

    public void addNewWord(){
        this.listWord.addWord();
    }

    public void appuieJoueur(KeyEvent e){
        System.out.println("oui");
    }


}
