package projet.cpoo.game;

import java.util.*;

public class Game {
    
    private double vitesse; //En seconde : le nombre de seconde qu'il faudra pour ajouter un mot.
    private double frequence_bonus;
    private GameMode gameMode;
    private int niveau;
    private int nbWord;
    private boolean withTimer;
    private Joueur joueur;
        // [...] A rajouter éventuellement, en tout cas on passera par un builder comme il est FORTEMENT sous entendu ...

    private Game(GameBuilder b){
        this.vitesse = b.vitesse;
        this.frequence_bonus = b.frequence_bonus;
        this.gameMode = b.gameMode;
        this.niveau = b.niveau;
        this.joueur = b.joueur;
        this.withTimer = b.withTimer;
        this.nbWord = b.nbWordToWrite;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }
    /* Getter et Setter */
    public Joueur getJoueur(){
        return this.joueur;
    }

    public GameMode getGameMode(){
        return this.gameMode;
    }

    public double getVitesse(){
        return this.vitesse;
    }
    
    public boolean withTimer(){
        return this.withTimer;
    }

    public int getNbWord(){
        return this.nbWord;
    }

    public int getNiveau(){
        return this.niveau;
    }

    public void addNbWord(){
        this.nbWord++;
    }

    public void remNbWord(){
        this.nbWord--;
    }

    /**
     * Remet le nombre de mot que le joueur doit écrire à 0.
     */
    public void resetNbrWord(){
        this.joueur.getStat().getnbrWord();
    }
    /**
     *  Ces fonctions ne sont plus à jour. Servait a créer des parties toute faite.
     * @param duration temps de la partie
     * @param pseudo   pseudo du joueur
     * @param nbWord   nombre de mot
     * @return
     */
    private Game newNormalGame(int duration, String pseudo, int nbWord){
        GameBuilder gb = Game.builder();
        gb = gb.frequence_bonus(0)
        .gameMode(GameMode.Normal)
        .vitesse(0)
        .joueur(new Joueur(pseudo))
        .nbWord(nbWord)
        .niveau(0);
        return gb.build();
    }

    public Game newGameModeGame(String pseudo, int speed, int frequence_bonus){
        GameBuilder gb = Game.builder();
        gb = gb.frequence_bonus(frequence_bonus)
        .gameMode(GameMode.Jeu)
        .vitesse(speed)
        .joueur(new Joueur(pseudo))
        .niveau(1);
        return gb.build();
    }


        //Fonction qui permet de modifier le buffer de mot (a lié avec la partie du GUI)
        // @params Il faut absolument que String s soit écrit sous la forme "<mot>, <mot>, etc..." pour que cela marche
    public void modificationBufferWordGenerator(String s){
        this.joueur.getListWord().changeWordGenerator(s);
    }

        //Change de niveau : il faudra également changer de vitesse 
    public void changementNiveau(){
        if (this.gameMode == GameMode.Normal){return;}
        this.niveau++;
        this.vitesse = 3 * Math.pow(0.9, this.niveau);
    }


    /**
     * Fonction qui va validé un mot, si on est dans le mode jeu alors on va retirer des vies également
     * @params mot qu'on valide
    **/
    public void validationWord(String tc){
        int nb = this.joueur.getListWord().checkMotValide(tc);
        this.joueur.ajoutStatsValidation(tc,nb);
        if (this.gameMode == GameMode.Jeu){
            this.joueur.changeLife(Math.negateExact(nb));
            this.joueur.getListWord().motValideGame();
        } else {
            this.joueur.getListWord().motValideNormal();
        }
    }    
    
    /* Builder */

    public static class GameBuilder{
        private double vitesse = 3;
        private double frequence_bonus;
        private GameMode gameMode;
        private int niveau = 1;
        private Joueur joueur;
        private int nbWordToWrite;
        private boolean withTimer;

        

        public Game build() throws IllegalArgumentException{
            if (this.verification()){
                return new Game(this);
            } else throw new IllegalArgumentException("Option non valide");
        }

        public GameBuilder vitesse(double vitesse){
            this.vitesse = vitesse;
            return this;
        }

        public GameBuilder frequence_bonus(double f_b){
            this.frequence_bonus = f_b;
            return this;
        }

        public GameBuilder withTimer(boolean b){
            this.withTimer = b;
            return this;
        }

        public GameBuilder joueur(Joueur j){
            this.joueur = j;
            return this;
        }

        public GameBuilder gameMode(GameMode gm){
            this.gameMode = gm;
            return this;
        }
        

        public GameBuilder niveau(int n){
            this.niveau = n;
            return this;
        }

        public GameBuilder nbWord(int n){
            this.nbWordToWrite = n;
            return this;
        }



        //TODO : Fonction qui va vérifier si les paramètres sont adéquat.
        public boolean verification(){
            if (this.gameMode == GameMode.Normal){
                if (this.niveau > 1  || this.frequence_bonus > 0){
                    return false;
                }
            } else if (this.gameMode == GameMode.Jeu){
                if (this.nbWordToWrite > 0 || this.withTimer == true){
                    return false;
                }
            }
            return true;
        }
    }
}

