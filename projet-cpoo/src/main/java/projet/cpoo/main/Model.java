package projet.cpoo.main;

import projet.cpoo.game.ExecutorTimer;
import projet.cpoo.game.Game;
import projet.cpoo.game.GameMode;
import projet.cpoo.game.Joueur;
import projet.cpoo.game.Game.GameBuilder;

public class Model {
    //private App view;
    //private AppControler controler;
    private Game game;
    private ExecutorTimer timer;

    public void newGameSolo(GameMode gm, String pseudo,int duration, int nbWord, boolean timer){
        GameBuilder gb = Game.builder()
        .gameMode(gm)
        .joueur(new Joueur(pseudo))
        .nbWord(nbWord)
        .withTimer(timer);
        this.game = gb.build();
        
        
        this.timer = new ExecutorTimer(duration);
    }



    public String printTampon(){
        return this.game.getJoueur().getListWord().affichage_first_words();
    }

    public String getPseudo(){
        return this.game.getJoueur().getNom();
    }

    public GameMode getGameMode(){
        return this.game.getGameMode();
    }

    public long getPassedTime(){
        return this.timer.getPassTime();
    }

    public String printStats(){
        return this.game.getJoueur().affichageStats();
    }

    public void ajoutWordGameModeChallenge(String s){
            if (this.game.getJoueur().getListWord().getTampon().size() == 15){
                this.ValidationMot(s);
                this.game.getJoueur().getListWord().addWord();
            }
    }

    public void ValidationMot(String s){
        this.game.validationWord(s);
        if (!this.game.withTimer() && this.game.getGameMode() == GameMode.Normal){
            this.game.remNbWord();
        }
    }

    public void ajoutStatFinal(){
        this.game.getJoueur().getStat().ajoutStatsFinal();
    }

    public void changementEcartType(long l){
        long old_time = this.game.getJoueur().getLastTime();
        this.game.getJoueur().changeDernierTemps(l);
        this.game.getJoueur().getStat().addStack(old_time - l);
    }

    public int getTimeRemaining(){
        return this.timer.getTime();
    }

    public double getVitesse(){
        return this.game.getVitesse();
    }

    public boolean getStateTimer(){
        return this.timer.getState();
    }

    public void startTimer(){
        this.timer.start();
    }

    public void setPassedTime(long time){
        this.timer.setPassTime(time);
    }

    public boolean getWithTimer(){
        return this.game.withTimer();
    }

    public int getNbWord(){
        return this.game.getNbWord();
    }

    public boolean checkEndGame(){
        if (this.game.getGameMode() == GameMode.Jeu){
            if (this.game.getJoueur().getLife() <= 0){
                return true;
            }
        } else {
            if (this.game.withTimer()){
                if (this.timer.getState() == false){
                    return true;
                }
            } else {
                if (this.getNbWord() <= 0){
                    return true;
                }
            }
        }
        return false;
    }
}
