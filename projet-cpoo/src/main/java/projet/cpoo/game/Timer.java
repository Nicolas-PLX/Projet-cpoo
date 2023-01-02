package projet.cpoo.game;

public class Timer {
    private long startTime;
    private long actualTime;
    public int actualDuration;
    private int duration; //en seconde

    public Timer(int duration){
        this.duration = duration; this.actualDuration = duration;
        this.startTime = -1; this.actualTime = -1;
    }

    public boolean TimerStart(){
        if((this.startTime == -1) && (this.actualTime == -1)){
            long time = System.currentTimeMillis();
            this.startTime = time; this.actualTime = time;
            return true;
        }
        return false;
    }

    //Temporaire : a refaire en fonction de ce que l'on veut en faire réellement
    public void refreshTimer(){
        this.actualTime = System.currentTimeMillis();
        if((this.actualTime - this.startTime) >= 1000){
            this.actualDuration--;
        }
    }
}
