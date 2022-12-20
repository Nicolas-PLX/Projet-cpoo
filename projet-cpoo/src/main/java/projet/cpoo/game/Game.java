package projet.cpoo.game;

import java.util.*;

public class Game {
    private double vitesse;
    private double frequence_bonus;
    private ArrayList<String> listWord;
    // [...] A rajouter éventuellement, en tout cas on passera par un builder comme il est FORTEMENT sous entendu ...

    private Game(GameBuilder b){
        this.vitesse = b.vitesse;
        this.frequence_bonus = b.frequence_bonus;
        this.listWord = b.listWord;
    }

    public static GameBuilder builder(){
        return new GameBuilder();
    }




    public static class GameBuilder{
        private double vitesse;
        private double frequence_bonus;
        private ArrayList<String> listWord;
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

        public GameBuilder listWord(ArrayList<String> liste){
            this.listWord = liste;
            return this;
        }
        //TODO : Fonction qui va vérifier si les paramètres sont adéquat.
        public boolean verification(){
            return true;
        }
    }
}
