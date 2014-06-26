/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Felix
 */
public class Sound {

    private AudioInputStream[] musikStreams, atmoStreams;
    private Clip musikClip, atmoClip;
    private boolean musikAn, atmoAn;
    private int musikPlayed, atmoPlayed;
    private URL[] musikFiles = {this.getClass().getResource("sound/Soundtrack.wav")};
    private URL[] atmoFiles = {this.getClass().getResource("sound/Atmo1_mixdown.aiff"),
        this.getClass().getResource("sound/Atmo2_mixdown.aiff"),
        this.getClass().getResource("sound/Atmo3_mixdown.aiff")};

    public Sound() {
        // Musik einlesen
        musikStreams = new AudioInputStream[musikFiles.length];
        for (int i = 0; i < musikFiles.length; i++) {
            try {
                musikStreams[i] = AudioSystem.getAudioInputStream(musikFiles[i]);
            } catch (UnsupportedAudioFileException ex) {
                System.err.println("Unbekanntes Audio-Format!");
            } catch (IOException ex) {
                System.err.println("Fehler an Musik-Datei!");
            }
        }
        
        // Atmo einlesen
        atmoStreams = new AudioInputStream[atmoFiles.length];
        for (int i = 0; i < atmoFiles.length; i++) {
            try {
                atmoStreams[i] = AudioSystem.getAudioInputStream(atmoFiles[i]);
            } catch (UnsupportedAudioFileException ex) {
                System.err.println("Unbekanntes Audio-Format!");
            } catch (IOException ex) {
                System.err.println("Fehler an Atmo-Datei!");
            }
        }
    }

    public void musikAn() {
        if (!musikAn) {
            musikAn = true;
            musikSpielen();
        }
    }

    public boolean getMusikAn() {
        return musikAn;
    }
    
    private void musikSpielen() {
        try {
            int rand = (int) Math.round(Math.random() * (musikStreams.length - 1));
            // Nicht zweilmal hintereinnander spielen
            while (rand == musikPlayed) {
                rand = (int) Math.round(Math.random() * (musikStreams.length - 1));
            }
            musikPlayed = rand;
            AudioInputStream musik = musikStreams[rand];
            AudioFormat format = musik.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            musikClip = (Clip) AudioSystem.getLine(info);
            musikClip.addLineListener(new MusikListener());
            musikClip.open(musik);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    musikClip.start();
                }
            }).start();
        } catch (LineUnavailableException ex) {
            System.err.println("Keine Line verfügbar!");
        } catch (IOException ex) {
            System.err.println("Fehler an Musik-Datei!");
        }
    }

    public void musikAus() {
        musikAn = false;
        if (musikClip != null) {
            musikClip.stop();
        }
    }

    private class MusikListener implements LineListener {

        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                musikClip.close();
                try {
                    musikStreams[musikPlayed] = AudioSystem.getAudioInputStream(musikFiles[musikPlayed]);
                } catch (UnsupportedAudioFileException ex) {
                    System.err.println("Unbekanntes Audio-Format!");
                } catch (IOException ex) {
                    System.err.println("Fehler an Musik-Datei!");
                }
                if (musikAn) {
                    musikSpielen();
                }
            }
        }
    }

    // ======== Atmo ===========

    public void atmoAn() {
        if (!atmoAn) {
            atmoAn = true;
            atmoSpielen();
        }
    }

    public boolean getAtmoAn() {
        return atmoAn;
    }
    
    private void atmoSpielen() {
        try {
            int rand = (int) Math.round(Math.random() * (atmoStreams.length - 1));
            // Nicht zweilmal hintereinnander spielen
            while (rand == atmoPlayed) {
                rand = (int) Math.round(Math.random() * (atmoStreams.length - 1));
            }
            atmoPlayed = rand;
            AudioInputStream atmo = atmoStreams[rand];
            AudioFormat format = atmo.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            atmoClip = (Clip) AudioSystem.getLine(info);
            atmoClip.addLineListener(new AtmoListener());
            atmoClip.open(atmo);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    atmoClip.start();
                }
            }).start();
        } catch (LineUnavailableException ex) {
            System.err.println("Keine Line verfügbar!");
        } catch (IOException ex) {
            System.err.println("Fehler an Atmo-Datei!");
        }
    }

    public void atmoAus() {
        atmoAn = false;
        if (atmoClip != null) {
            atmoClip.stop();
        }
    }

    private class AtmoListener implements LineListener {

        @Override
        public void update(LineEvent event) {
            if (event.getType() == LineEvent.Type.STOP) {
                atmoClip.close();
                try {
                    atmoStreams[atmoPlayed] = AudioSystem.getAudioInputStream(atmoFiles[atmoPlayed]);
                } catch (UnsupportedAudioFileException ex) {
                    System.err.println("Unbekanntes Audio-Format!");
                } catch (IOException ex) {
                    System.err.println("Fehler an Atmo-Datei!");
                }
                if (atmoAn) {
                    atmoSpielen();
                }
            }
        }
    }
}
