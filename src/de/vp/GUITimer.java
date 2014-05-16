package de.vp;

import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author Felix
 */
public class GUITimer extends TimerTask{
    JPanel panel;
    
    public GUITimer(JPanel p) {
        panel = p;
    }
    
    @Override
    public void run() {
        panel.repaint();
    }
    
}
