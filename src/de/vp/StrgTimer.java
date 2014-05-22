package de.vp;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Felix
 */
public class StrgTimer extends TimerTask {

    Date date;
    Spielsteuerung strg;

    public StrgTimer(Spielsteuerung s) {
        strg = s;
        date = new Date();
        date.setTime(0);
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
        date.setTime(date.getTime() + 1000);
        strg.step();
    }
}
