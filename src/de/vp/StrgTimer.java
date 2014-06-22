package de.vp;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Felix
 */
public class StrgTimer extends TimerTask {

    private Date date;
    private Spielsteuerung strg;

    public StrgTimer(Spielsteuerung s) {
        strg = s;
        date = new Date();
        date.setTime(43200000);
    }

    public StrgTimer(Spielsteuerung s, Date d) {
        strg = s;
        date = d;
    }

    public Date getTime() {
        return date;
    }

    @Override
    public void run() {
        date.setTime(date.getTime() + 60000);
        strg.step();
    }
    
    public long getTicks() {
        return (date.getTime() % 86400000) / 3600;
    }
}
