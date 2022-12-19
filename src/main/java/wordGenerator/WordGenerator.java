import java.util.Random;

//Classe qui va générer la liste de mot possible

public class WordGenerator {
    private String[] dictionnaire;

    public WordGenerator(String s){
        String separateur = ", ";
        String[] dictionnaire = s.split(separateur);
        this.dictionnaire = dictionnaire;
    }

    public Word generateNewWord(){
        int len = this.dictionnaire.length;
        Random r = new Random();
        int random = r.nextInt(len);
        return this.dictionnaire[random];
    }

    
    
}
