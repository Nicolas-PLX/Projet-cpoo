package projet.cpoo.statistic;

import java.util.Stack;
import java.util.function.DoubleToIntFunction;

public class Statistic {
    private double MPM;
    private double accuracy;
    private double regularity;
    private int goodKey;
    private int falseKey;
    private int nbrGoodWord;
    private int nbrWord;
    private Stack<Long> stackList = new Stack<Long>();


    public Statistic(){
        this.MPM = -1; this.accuracy = -1.0; this.regularity = -1; this.goodKey = 0; this.falseKey = 0; this.nbrGoodWord = 0; this.nbrWord = 0;
    }
    /* Setter et getter */
    public double getMPM(){return this.MPM;}
    public double getAccuracy(){return this.accuracy;}
    public double getRegularity(){return this.regularity;}
    public int getGoodWord(){return this.nbrGoodWord;}
    public int getGoodKey(){return this.goodKey;}
    public int getFalseKey(){return this.falseKey;}
    public int  getnbrWord(){return this.nbrWord;}

    private void setMPM(double MPM){this.MPM = MPM;}
    private void setAccuracy(double accuracy){this.accuracy = accuracy;}
    private void setRegularity(double regularity){this.regularity = regularity;}
    private void setnbrGoodWord(int goodWord){this.nbrGoodWord = goodWord;}

    /**
     * Calcul le MPM
     * @param numb_min nombre de mminutes
     */
    public void calculMPM(int numb_min){
        double min = (double)numb_min / 60;
        double new_MPM = ((double)this.goodKey / min) / 5.0;
        this.setMPM(new_MPM);
    }
    /**
     * Calcul la précision
     */
    public void calculAccuracy(){
        double new_acc = (this.goodKey / (goodKey + falseKey)) * 100;
        this.setAccuracy(new_acc);
    }

    /*
     * Calcul la régularité. Formule mathématique pour calculer l'écart type
     */
    public void calculRegularity(){
        int somme = 0; int size = this.stackList.size();
        if (size == 0){return;}
        Stack<Long> copy = new Stack<Long>();
        while(!this.stackList.empty()){
            long pop = (this.stackList.pop()) / 1000;
            somme += pop;
            copy.push(pop);
        }
        double moyenne = somme / size;

        double deviation = 0.0;
        while(!copy.empty()){
            deviation += Math.pow((copy.pop() - moyenne), 2);
        }
        double square = deviation / size;
        this.regularity = Math.sqrt(square);
    }

    public void addGoodkey(int nb){
        this.goodKey += nb;
    }

    public void addFalseKey(int nb){
        this.falseKey += nb;
    }

    public void addGoodWord(){
        this.nbrGoodWord++;
    }

    public void addStack(long l){
        this.stackList.add(l);
    }

    /* Modifie les statistiques */
    public void ajoutStatsValidation(String tc,int nb){
        this.addFalseKey(nb);
        this.addGoodkey(tc.length()-nb);
        if (nb == 0){this.addGoodWord();}
        this.nbrWord++;
        this.accuracy = ((this.goodKey * 100) / (this.goodKey + this.falseKey));
    }


    public void ajoutStatsFinal(int nb){
        this.calculMPM(nb);
        this.calculRegularity();
    }
    /*
     * Reset les statistiques de la partie (quand on en relance une)
     */
    public void resetStat(){
        this.MPM = -1; this.accuracy = -1.0; this.regularity = -1; this.goodKey = 0; this.falseKey = 0; this.nbrGoodWord = 0; this.nbrWord = 0;
    }

    @Override
    public String toString(){
        String s = "";
        s += "mots total / mots correct / mot par minute : " + this.nbrWord + " / " + this.nbrGoodWord + " / " + this.MPM + "\n";
        s += "caractère correct / caractère incorrect : " + this.goodKey + " / " + this.falseKey + "\n";
        s += "précision / régularité : " + this.accuracy + "% / " + this.regularity + "\n";
       return s;
    }

    
}
