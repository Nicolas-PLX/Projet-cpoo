package projet.cpoo.game;

import projet.cpoo.statistic.Statistic;

public class Joueur {
    private String nom;
    private Tampon listWord;
    private Statistic stat;
    private int life = 10;


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

    public int getLife(){
        return this.life;
    }

    public void setLife(int nb){
        this.life = nb;
    }

    public void addLife(int nb){
        this.setLife(this.life + nb);
    }

    public void addNewWord(){
        this.listWord.addWord();
    }
}
