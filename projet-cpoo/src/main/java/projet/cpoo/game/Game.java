package projet.cpoo.game;

import java.util.*;

public class Game {
    
    private double vitesse; //En seconde : le nombre de seconde qu'il faudra pour ajouter un mot.
    private double frequence_bonus;
    private CustomTimer timer;
    private GameMode gameMode;
    private int niveau;
    private int nbWord;
    private Joueur joueur;
        // [...] A rajouter éventuellement, en tout cas on passera par un builder comme il est FORTEMENT sous entendu ...

    private Game(GameBuilder b){
        this.vitesse = b.vitesse;
        this.frequence_bonus = b.frequence_bonus;
        this.timer = b.timer;
        this.gameMode = b.gameMode;
        this.niveau = b.niveau;
        this.joueur = b.joueur;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }
    public Joueur getJoueur(){
        return this.joueur;
    }

    public GameMode getGameMode(){
        return this.gameMode;
    }

    public static Game newNormalGame(int duration, String pseudo, int nbWord){
        GameBuilder gb = Game.builder();
        gb = gb.frequence_bonus(0)
        .gameMode(GameMode.Normal)
        .vitesse(0)
        .timer(new CustomTimer(duration))
        .joueur(new Joueur(pseudo))
        .nbWord(nbWord)
        .niveau(0);
        return gb.build();
    }

    public static Game newGameModeGame(String pseudo, int speed, int frequence_bonus){
        GameBuilder gb = Game.builder();
        gb = gb.frequence_bonus(frequence_bonus)
        .gameMode(GameMode.Jeu)
        .vitesse(speed)
        //.timer(null)
        .joueur(new Joueur(pseudo))
        .niveau(1);
        return gb.build();
    }


        //Fonction qui permet de modifier le buffer de mot (a lié avec la partie du GUI)
        //Il faut absolument que String s soit écrit sous la forme "<mot>, <mot>, etc..." pour que cela marche
    public void modificationBufferWordGenerator(String s){
        this.joueur.getListWord().changeWordGenerator(s);
    }

        //Change de niveau : il faudra également changer de vitesse 
    public void changementNiveau(){
        if (this.gameMode == GameMode.Normal){return;}
        this.niveau++;
        this.vitesse = 3 * Math.pow(0.9, this.niveau);
        this.timer.timerRestart();
    }

    public void modificationTimer(int duration){
        this.timer.timerRestart();
    }

    //Fonction qui va validé un mot, si on est dans le mode jeu alors on va retirer des vies également
    public void validationWord(char[] tc){
        int nb = this.joueur.getListWord().checkMotValide(tc);
        this.joueur.ajoutStatsValidation(tc,nb);
        if (this.gameMode == GameMode.Jeu){
            this.joueur.changeLife(Math.negateExact(nb));
            System.out.println("je vais ici");
            this.joueur.getListWord().motValideGame();
        } else {
            this.joueur.getListWord().motValideNormal();
        }
    }



    //fonction qui va lancer une partie
    public static void startNewGame(int duration, int nbWord, String pseudo, int speed, int frequence_bonus, GameMode gm){
        if (gm == GameMode.Jeu){
            Game game = Game.newGameModeGame(pseudo, speed, frequence_bonus);
            //TODO a finir : le mode jeu se fini par rapport aux nombres de vie
            while(game.joueur.getLife() > 0){

            }
        } else if (gm == GameMode.Normal){
            Game game = Game.newNormalGame(duration, pseudo, nbWord);
            Timer chrono = new Timer();
            chrono.schedule(game.timer, 1000,game.timer.getDuration());
            while(game.timer.getState() == true){
                System.out.println(game.timer.getDuration());
                Scanner sc = new Scanner(System.in);
                System.out.println(sc.nextLine());
            }
        }
    }

    public static class GameBuilder{
        private double vitesse;
        private double frequence_bonus;
        private CustomTimer timer;
        private GameMode gameMode;
        private int niveau;
        private Joueur joueur;
        private int nbWord;

        // [...]

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

        public GameBuilder joueur(Joueur j){
            this.joueur = j;
            return this;
        }

        public GameBuilder gameMode(GameMode gm){
            this.gameMode = gm;
            return this;
        }
        
        public GameBuilder timer(CustomTimer t){
            this.timer = t;
            return this;
        }

        public GameBuilder niveau(int n){
            this.niveau = n;
            return this;
        }

        public GameBuilder nbWord(int n){
            this.nbWord = n;
            return this;
        }



        //TODO : Fonction qui va vérifier si les paramètres sont adéquat.
        public boolean verification(){
            if (this.gameMode == GameMode.Normal){
                if (this.niveau > 0 || this.vitesse > 0 || this.frequence_bonus > 0){
                    return false;
                }
            } else if (this.gameMode == GameMode.Jeu){
                if (this.nbWord > 0){
                
                    return false;
                }
            }
            return true;
        }
    }
}

