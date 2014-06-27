/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private AudioInputStream[] atmoStreams;
    private AudioInputStream musikStream;
    private Clip musikClip, atmoClip;
    private boolean musikAn, atmoAn;
    private int atmoPlayed;
    private URL[] atmoFiles = {this.getClass().getResource("sound/Atmo1_mixdown.aiff"),
        this.getClass().getResource("sound/Atmo2_mixdown.aiff"),
        this.getClass().getResource("sound/Atmo3_mixdown.aiff")};

    public Sound() {
        try {
            // Musik einlesen
            musikStream = AudioSystem.getAudioInputStream(this.getClass().getResource("sound/Soundtrack.wav"));
        } catch (UnsupportedAudioFileException ex) {
            System.err.println("Unbekanntes Audio-Format!");
        } catch (IOException ex) {
            System.err.println("Fehler an Musik-Datei!");
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
            try {
                musikAn = true;
                AudioInputStream musik = musikStream;
                AudioFormat format = musik.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                musikClip = (Clip) AudioSystem.getLine(info);
                musikClip.open(musik);
                musikClip.loop(Clip.LOOP_CONTINUOUSLY);
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
    }

    public boolean getMusikAn() {
        return musikAn;
    }

    public void musikAus() {
        musikAn = false;
        if (musikClip != null) {
            musikClip.stop();
        }
        try {
            // Musik einlesen
            musikStream = AudioSystem.getAudioInputStream(this.getClass().getResource("sound/Soundtrack.wav"));
        } catch (UnsupportedAudioFileException ex) {
            System.err.println("Unbekanntes Audio-Format!");
        } catch (IOException ex) {
            System.err.println("Fehler an Musik-Datei!");
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
