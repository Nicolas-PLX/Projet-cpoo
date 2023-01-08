package projet.cpoo.statistic;

import java.util.Stack;

public class Statistic {
    private int MPM;
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

    public int getMPM(){return this.MPM;}
    public double getAccuracy(){return this.accuracy;}
    public double getRegularity(){return this.regularity;}
    public int getGoodWord(){return this.nbrGoodWord;}
    public int getGoodKey(){return this.goodKey;}
    public int getFalseKey(){return this.falseKey;}

    private void setMPM(int MPM){this.MPM = MPM;}
    private void setAccuracy(double accuracy){this.accuracy = accuracy;}
    private void setRegularity(double regularity){this.regularity = regularity;}
    private void setnbrGoodWord(int goodWord){this.nbrGoodWord = goodWord;}

    public void calculMPM(int numb_min){
        int new_MPM = (this.goodKey / numb_min) / 6;
        this.setMPM(new_MPM);
    }

    public void calculAccuracy(){
        double new_acc = (this.goodKey / (goodKey + falseKey)) * 100;
        this.setAccuracy(new_acc);
    }

    public void calculRegularity(){
        int somme = 0; int size = this.stackList.size();
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

    public void ajoutStatsValidation(String tc,int nb){
        this.addFalseKey(nb);
        this.addGoodkey(tc.length()-nb);
        if (nb == 0){this.addGoodWord();}
        this.nbrWord++;
        this.accuracy = ((this.goodKey * 100) / (this.goodKey + this.falseKey));
    }

    public void ajoutStatsFinal(){
        this.calculMPM(1);
        this.calculRegularity();
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
