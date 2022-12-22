package projet.cpoo.game;

import java.util.ArrayList;

import projet.cpoo.wordGenerator.WordGenerator;

public class Tampon {
    private ArrayList<String> tampon;
    private WordGenerator wg;


    public Tampon(String s){
        this.tampon = new ArrayList<String>();
        this.wg = new WordGenerator(s);
    }
    //Va remplir le tampon avec 15 mots aléatoire
    public static Tampon initTampon(String s){
        Tampon res = new Tampon(s);
        for (int i = 0; i < 15;i++){
            res.tampon.add(res.wg.generateNewWord());
        }
        return res;
    }

    //Va ajouter un élément dans la liste. On fera une petite vérification de la taille du tampon (facultatif)
    public void addWord(String s){
        if (this.tampon.size() < 15){
            this.tampon.add(s);
        }
    }
    //Va retirer le premier élément de la liste (quand on appuis sur espace)
    public void removeWord(){
        this.tampon.remove(0);
    }

    public boolean motValide(){
        this.removeWord();
        this.addWord(this.wg.generateNewWord());
        return true;
    }
    
}
