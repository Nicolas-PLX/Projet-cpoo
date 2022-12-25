package projet.cpoo.statistic;

public class Statistic {
    private int MPM;
    private double accuracy;
    private int regularity;
    private int goodKey;
    private int falseKey;


    public Statistic(){
        this.MPM = -1; this.accuracy = -1.0; this.regularity = -1; this.goodKey = 0; this.falseKey = 0;
    }

    public int getMPM(){return this.MPM;}
    public double getAccuracy(){return this.accuracy;}
    public int getRegularity(){return this.regularity;}

    private void setMPM(int MPM){this.MPM = MPM;}
    private void setAccuracy(double accuracy){this.accuracy = accuracy;}
    private void setRegularity(int regularity){this.regularity = regularity;}

    public void calculMPM(int numb_min){
        int new_MPM = (this.goodKey / numb_min) / 5;
        this.setMPM(new_MPM);
    }

    public void calculAccuracy(){
        double new_acc = (this.goodKey / (goodKey + falseKey)) * 100;
        this.setAccuracy(new_acc);
    }

    public void addGoodkey(){
        this.goodKey++;
    }

    public void addFalseKey(){
        this.falseKey++;
    }
    //TODO : calcul de la régularité avec l'écart type entre chaque caractère utile.
    public void calculRegularity(){
        
    }

    
}
