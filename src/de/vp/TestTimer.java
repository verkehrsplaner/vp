package de.vp;

import java.util.TimerTask;

/**
 *
 * @author Felix
 */
public class TestTimer extends TimerTask {

    Spielsteuerung strg;

    public TestTimer(Spielsteuerung s) {
        strg = s;
    }
    
    @Override
    public void run() {
        strg.stadtteilBauen();
    }
}
