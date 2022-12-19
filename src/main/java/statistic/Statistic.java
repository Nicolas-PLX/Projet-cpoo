public class Statistic {
    private int MPM;
    private double accuracy;
    private int regularity;


    public Statistic(){
        this.MPM = -1; this.accuracy = -1.0; this.regularity = -1;
    }

    public int getMPM(){return this.MPM;}
    public int getAccuracy(){return this.accuracy;}
    public int getRegularity(){return this.regularity;}

    private void setMPM(int MPM){this.MPM = MPM;}
    private void setAccuracy(double accuracy){this.accuracy = accuracy;}
    private void setRegularity(int regularity){this.regularity = regularity;}

    public void calculMPM(int useful_char, int numb_min){
        int new_MPM = (useful_char / numb_min) / 5;
        this.setMPM(new_MPM);
    }

    public void calculAccuracy(int useful_char, int numb_press_key){
        double new_acc = (useful_char / numb_press_key) * 100;
        this.setAccuracy(new_acc);
    }
    //TODO : calcul de la régularité avec l'écart type entre chaque caractère utile.
    public void calculRegularity(int useful_char){
        
    }

    
}
