package projet.cpoo.game;

import java.util.TimerTask;

public class CustomTimer extends TimerTask{
    
    private int actualDuration; // en seconde
    private int duration; //en seconde
    private boolean state;

    public CustomTimer(int duration){
        this.duration = duration; 
        this.actualDuration = duration;
    }

    public int getDuration(){
        return this.duration;
    }

    public boolean getState(){
        return this.state;
    }

    private void setDuration(int duration){
        this.duration = duration;
        this.actualDuration = duration;
    }

    public void timerRestart(){
        this.setDuration(this.duration);
        this.state = false;
    }
  
    @Override
    public void run() {
        this.state = true;
        this.actualDuration--;
        if (this.actualDuration <= 0){
            this.state = false;
            this.cancel();
        }
    }
}
