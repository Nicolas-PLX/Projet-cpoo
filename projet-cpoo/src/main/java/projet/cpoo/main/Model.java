package projet.cpoo.main;

import projet.cpoo.game.ExecutorTimer;
import projet.cpoo.game.Game;
import projet.cpoo.game.GameMode;
import projet.cpoo.game.Joueur;
import projet.cpoo.game.Game.GameBuilder;

public class Model {
    private Game game;
    private ExecutorTimer timer;

        /**
         * Va créer une nouvelle partie en solo, que ce soit pour le mode jeu ou mode normal
         * @params differente valeur que ce soit le mode de jeu, le pseudo la durée de la partie, si on utilise un timer, et le nombre de mot à écrire
         */
    public void newGameSolo(GameMode gm, String pseudo,int duration, int nbWord, boolean timer){
        GameBuilder gb = Game.builder()
        .gameMode(gm)
        .joueur(new Joueur(pseudo))
        .nbWord(nbWord)
        .withTimer(timer);
        this.game = gb.build();
        
        if (duration != 0){
            this.timer = new ExecutorTimer(duration);
        } else {
            this.timer = new ExecutorTimer(60);
        }
    }
    /* Getter et Setter  */
    public void resetLife(){
        this.game.getJoueur().resetLife();
    }

    public int getLifePlayer(){
        return this.game.getJoueur().getLife();
    }

    public int getNiveau(){
        return this.game.getNiveau();
    }

    public int getMotEcrit(){
        return this.game.getJoueur().getStat().getGoodWord();
    }

    public void changementNiveau(){
        this.game.changementNiveau();
    }

    public void resetStat(){
        this.game.getJoueur().getStat().resetStat();
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

    public void resetNbrWord(){
        this.game.remNbWord();
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

    public String printStats(){
        return this.game.getJoueur().affichageStats();
    }

    /**
     * Valide le mot et ajoute un noubeau mot pour le mode jeu
     * @param s string a validé
     */
    public void ajoutWordGameModeChallenge(String s){
        this.game.getJoueur().getListWord().addWord();
            if (this.game.getJoueur().getListWord().getTampon().size() > 18){
                this.ValidationMot(s);
            }
    }
    /**
     * Valide la string
     * @param s valide le mot s
     */
    public void ValidationMot(String s){
        this.game.validationWord(s);
        if (!this.game.withTimer() && this.game.getGameMode() == GameMode.Normal){
            this.game.remNbWord();
        }
    }
    /**
     * Finalise les statistiques (calcul le MPM et regularité)
     */
    public void ajoutStatFinal(){
        int nb = this.timer.getOgTime();
        this.game.getJoueur().getStat().ajoutStatsFinal(nb);
    }
    /**
     * change l'écart type
     * @param l ajoute l au Stack
     */
    public void changementEcartType(long l){
        long old_time = this.game.getJoueur().getLastTime();
        this.game.getJoueur().changeDernierTemps(l);
        this.game.getJoueur().getStat().addStack(old_time - l);
    }

    
    /**
     * Vérifie que la partie est terminé en fonction du mode de jeu et des fin possible
     * @return boolean qui indique si c'est fini
     */
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
