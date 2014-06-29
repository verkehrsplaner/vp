package de.vp;

import javax.swing.JFrame;

public class Main {

    /**
     * Der Splashscreen wird geöffnet
     * 
     * @param argv Alle Kommandozeilenparameter
     */
    public static void main(String[] argv) {
        
        JFrame s = new SplashScreen();
        s.setVisible(true);
    }

}
