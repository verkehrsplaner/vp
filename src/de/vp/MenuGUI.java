/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.io.File;
import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Hecker & Maxi
 */
public class MenuGUI extends JDialog {

    private Sound sound;
    private SpielPanel panel;
    Spielsteuerung strg;

    /**
     * Creates new form EinstellungsGUI
     */
    public MenuGUI(Sound s, SpielPanel p, Spielsteuerung ss) {
        sound = s;
        panel = p;
        strg = ss;
        initComponents();

        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());
        boolean pause = strg.getPause();
        if (pause == true) {
           
           spielPause.setIcon(new ImageIcon(getClass().getResource("images/play.png")));
        } else {
           
           spielPause.setIcon(new ImageIcon(getClass().getResource("images/pause.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hMusik = new javax.swing.JCheckBox();
        Menü = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        spielBeenden = new javax.swing.JButton();
        spielSpeichern = new javax.swing.JButton();
        blinken = new javax.swing.JCheckBox();
        hAtmo = new javax.swing.JCheckBox();
        spielPause = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menü");
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(551, 400));
        setMinimumSize(new java.awt.Dimension(551, 400));
        setPreferredSize(getMinimumSize());
        setResizable(false);
        getContentPane().setLayout(null);

        hMusik.setForeground(new java.awt.Color(255, 255, 255));
        hMusik.setSelected(sound.getMusikAn());
        hMusik.setText("Hintergrundmusik");
        hMusik.setToolTipText("Musik an / aus");
        hMusik.setContentAreaFilled(false);
        hMusik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hMusikActionPerformed(evt);
            }
        });
        getContentPane().add(hMusik);
        hMusik.setBounds(55, 164, 140, 23);

        Menü.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Menü.setForeground(new java.awt.Color(255, 255, 255));
        Menü.setText("Menü");
        getContentPane().add(Menü);
        Menü.setBounds(252, 19, 70, 29);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(55, 228, 429, 10);

        spielBeenden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/exit.png"))); // NOI18N
        spielBeenden.setToolTipText("Spiel Beenden");
        spielBeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielBeendenActionPerformed(evt);
            }
        });
        getContentPane().add(spielBeenden);
        spielBeenden.setBounds(353, 250, 131, 98);

        spielSpeichern.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/speichern transparent.png"))); // NOI18N
        spielSpeichern.setToolTipText("Spiel Speichern");
        spielSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielSpeichernActionPerformed(evt);
            }
        });
        getContentPane().add(spielSpeichern);
        spielSpeichern.setBounds(204, 250, 131, 98);

        blinken.setForeground(new java.awt.Color(255, 255, 255));
        blinken.setSelected(panel.getBlinken());
        blinken.setText("Nicht versorgte Bahnhöfe blinken");
        blinken.setToolTipText("Bahnhof blinken an / aus");
        blinken.setContentAreaFilled(false);
        blinken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blinkenActionPerformed(evt);
            }
        });
        getContentPane().add(blinken);
        blinken.setBounds(55, 187, 220, 23);

        hAtmo.setForeground(new java.awt.Color(255, 255, 255));
        hAtmo.setSelected(sound.getAtmoAn());
        hAtmo.setText("Atmosphäre");
        hAtmo.setToolTipText("Atmosphäre an / aus");
        hAtmo.setContentAreaFilled(false);
        hAtmo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hAtmoActionPerformed(evt);
            }
        });
        getContentPane().add(hAtmo);
        hAtmo.setBounds(55, 141, 110, 23);

        spielPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/pause.png"))); // NOI18N
        spielPause.setToolTipText("Spiel pausieren");
        spielPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielPauseActionPerformed(evt);
            }
        });
        getContentPane().add(spielPause);
        spielPause.setBounds(55, 250, 131, 98);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/BGMenu.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(551, 380));
        jLabel1.setMinimumSize(new java.awt.Dimension(551, 380));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -2, 550, 380);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Falls in dem OptionPane Speichern ausgewählt wird, wird das Spiel gespeichert und anschließend geschlossen
     * Falls ohne Speichern schließen gewählt wird, wird das Spiel sofort beendet.
     *
     * @param evt
     */
    private void spielBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielBeendenActionPerformed
        Object[] options = {"Abbrechen", "Nein", "Ja"};
        int s = JOptionPane.showOptionDialog(this, "Möchtest du das Spiel vor dem Beenden speichern?", "Spiel Speichern",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
         // System.out.println(s);
         if (s == 1) {
            System.exit(0);
            
        }
        else if (s == 2) {
            int b = speichern();
            if (b == JFileChooser.APPROVE_OPTION) {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_spielBeendenActionPerformed

    /**
     * 
     * Speichert das aktuelle Spiel
     */
    private int speichern() {
        JFileChooser fc = new JFileChooser();
        fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                return file.getName().toLowerCase().endsWith(".vp");
            }
            public String getDescription() {
                return "Spielstände";
            }
            
        });
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Path file = fc.getSelectedFile().toPath();
            strg.speichern(file);
        }
        return returnVal;
    }
    
    /**
     * Die Funktion speichern() wird aufgerufen
     *
     * @param evt
     */
    private void spielSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielSpeichernActionPerformed
        speichern();
    }//GEN-LAST:event_spielSpeichernActionPerformed

    /**
     * Musik wird in der Klasse Sound an oder aus gestellt
     * 
     * @param evt
     */
    private void hMusikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hMusikActionPerformed
        if (hMusik.isSelected() == true) {
            sound.musikAn();
        } else {
            sound.musikAus();
        }
    }//GEN-LAST:event_hMusikActionPerformed

    /**
     * Das Blinken der nicht-verbundenen Häuser kann hier aktiviert oder deaktiviert werden
     * 
     * @param evt 
     */
    private void blinkenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blinkenActionPerformed
        if (blinken.isSelected() == true) {
            panel.setBlinken(true);
        } else {
            panel.setBlinken(false);
        }
    }//GEN-LAST:event_blinkenActionPerformed

    /**
     * Atmosphäre Sounds werden in der Klasse Sound an oder aus gestellt
     * 
     * @param evt 
     */
    private void hAtmoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hAtmoActionPerformed
        if (hAtmo.isSelected() == true) {
            sound.atmoAn();
        } else {
            sound.atmoAus();
        }
    }//GEN-LAST:event_hAtmoActionPerformed

    private void spielPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielPauseActionPerformed
        if(strg.getPause()) {
            strg.setPause(false);
            spielPause.setIcon(new ImageIcon(getClass().getResource("images/pause.png")));
        }
        else {
            strg.setPause(true);
            spielPause.setIcon(new ImageIcon(getClass().getResource("images/play.png")));
            
        }
    }//GEN-LAST:event_spielPauseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menü;
    public javax.swing.JCheckBox blinken;
    private javax.swing.JCheckBox hAtmo;
    private javax.swing.JCheckBox hMusik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton spielBeenden;
    private javax.swing.JButton spielPause;
    private javax.swing.JButton spielSpeichern;
    // End of variables declaration//GEN-END:variables
}
