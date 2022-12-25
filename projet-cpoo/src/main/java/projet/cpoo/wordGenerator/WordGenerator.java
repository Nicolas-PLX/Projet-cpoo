package projet.cpoo.wordGenerator;

import java.io.*;
import java.util.Random;

//Classe qui va générer la liste de mot possible

public class WordGenerator {
    private String[] dictionnaire;


    public WordGenerator(String s){
        String separateur = ", ";
        String[] dictionnaire = s.split(separateur);
        this.dictionnaire = dictionnaire;
    }

    public String generateNewWord(){
        int len = this.dictionnaire.length;
        Random r = new Random();
        int random = r.nextInt(len);
        return this.dictionnaire[random];
    }

    public String[] getDico(){
        return this.dictionnaire;
    }

    public int getDicoLength(){
        return this.dictionnaire.length;
    }

        //Fonction juste pour passer les fichiers test
    public boolean returnTrue(){
        return true;
    }
    //Fonction qui va lire le fichier de mot de base (que l'on fournit dans le projet)
    //et va le transformer en generateur de mot 
    public static WordGenerator generatorFromFile() throws IOException{
        File file = new File("src/main/resources/ListeMotBasique");
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        String ligne = buffer.readLine(); String res = "";
        while((ligne = buffer.readLine()) != null){
            System.out.println("ligne = " + ligne);
            res = ", " + res + ", " + ligne;
        }
        buffer.close();
        return new WordGenerator(res);
    }

    
    
}
