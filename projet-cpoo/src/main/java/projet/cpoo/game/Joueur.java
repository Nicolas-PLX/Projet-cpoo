package projet.cpoo.game;

import projet.cpoo.statistic.Statistic;

public class Joueur {
    private String nom;
    private Tampon listWord;
    private Statistic stat;
    private int life = 10;
    private long tempsDernierChar;
    


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

    private void setLife(int nb){
        this.life = nb;
    }

    public void changeLife(int nb){
        this.setLife(this.life + nb);
    }

    public void addNewWord(){
        this.listWord.addWord();
    }

    public void changeDernierTemps(long t){
        this.tempsDernierChar = t;
    }

    public long getLastTime(){
        return this.tempsDernierChar;
    }

    //Ajout aux statistiques les valeurs après avoir modifié un mot
    public void ajoutStatsValidation(String tc,int nb){
        this.stat.ajoutStatsValidation(tc,nb);
    }

    public String affichageStats(){
        return this.stat.toString();
    }
}
