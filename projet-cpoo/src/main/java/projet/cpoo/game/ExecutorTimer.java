package projet.cpoo.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTimer implements Runnable{
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private int time = 60;
    private long passTime;
    private boolean state = false;

    public ExecutorTimer(int time){
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

    public long getPassTime(){
        return this.passTime;
    }

    public void setPassTime(long l){
        this.passTime = l;
    }

    public boolean getState(){
        return this.state;
    }

    public void start(){
        this.state = true;
        ScheduledFuture<?> execEnd = this.scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
        this.scheduler.schedule(new Runnable(){
            public void run(){execEnd.cancel(true);}
        }, this.time, TimeUnit.SECONDS);
    }


    @Override
    public void run() {
        time--;
        if (this.time <= 0){
            this.state = false;
        }
    }


   
    
}
