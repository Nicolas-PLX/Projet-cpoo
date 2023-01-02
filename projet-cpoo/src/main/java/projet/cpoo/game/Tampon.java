package projet.cpoo.game;

import java.io.IOException;
import java.util.ArrayList;

import projet.cpoo.wordGenerator.WordGenerator;

public class Tampon {
    private ArrayList<String> tampon;
    private WordGenerator wg;

    public Tampon(){
        this.tampon = new ArrayList<String>();
        try {
            this.wg = WordGenerator.generatorFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.initTampon();
    }

    public Tampon(String s){
        this.tampon = new ArrayList<String>();
        this.wg = new WordGenerator(s);
        this.initTampon();
    }

    public ArrayList<String> getTampon(){
        return this.tampon;
    }

    public WordGenerator getWordGenerator(){
        return this.wg;
    }



    //Va remplir le tampon avec 15 mots aléatoire
    public static Tampon initTamponFromString(String s){
        Tampon res = new Tampon(s);
        for (int i = 0; i < 15;i++){
            res.tampon.add(res.wg.generateNewWord());
        }
        return res;
    }
    //Va remplir le tampon
    public void initTampon(){
        for (int i = 0; i < 15;i++){
            this.tampon.add(this.wg.generateNewWord());
        }
    }

    //Va ajouter un élément dans la liste. On fera une petite vérification de la taille du tampon (facultatif)
    public void addWord(){
        this.tampon.add(wg.generateNewWord());
    }
    //Va retirer le premier élément de la liste (quand on appuis sur espace)
    public void removeWord(){
        this.tampon.remove(0);
    }
    //Vide le tampon
    public void resetTampon(){
        this.tampon.clear();
    }

    //Change le generateur de mot. On doit également changer le tampon, on le vide et on le ré-initialise.
    public void changeWordGenerator(String s){
        this.wg = new WordGenerator(s);
        this.resetTampon();
        this.initTampon();
    }

    //On retire le mot. On en ajoute un directement juste après
    public boolean motValide(){
        this.removeWord();
        this.addWord();
        return true;
    }

    //Fonction a refaire (peut être) pour s'adapter à une interface graphique
    
    public String affichage_first_words(){
        String res = "";
        for (int i = 0; i < 15;i++){
            res += res + " " + this.tampon.get(i);
        }
        return res;
    }
    
}
