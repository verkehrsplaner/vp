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

    private AudioInputStream[] musikStreams;
    private Line musikLine, atmoLine, fxLine;
    private Clip musikClip, atmoClip, fxClip;
    private boolean musikAn, atmoAn, fxAn;
    private int musikPlayed;
    private URL[] musikFiles = {this.getClass().getResource("sound/Nerviger_Song.aiff"),
        this.getClass().getResource("sound/Action.aiff"),
        this.getClass().getResource("sound/Orgel.aiff"),
        this.getClass().getResource("sound/M_Style.aiff")};

    public Sound() {
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
//            System.out.println("Song " + rand + " wird gespielt!");
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
        System.out.println("Musik Aus!");
    }

    private class MusikListener implements LineListener {

        @Override
        public void update(LineEvent event) {
//            System.out.println("LineEvent! (" + event.getType() + ")");
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

}
