package de.vp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] argv) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Build Start: " + format.format(Calendar.getInstance().getTime()));
        System.out.println("Starte GUI..");
        JFrame f = new SpielGUI();
        f.setVisible(true);
    }
    
}