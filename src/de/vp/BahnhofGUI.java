/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author hecker
 */
public class BahnhofGUI extends javax.swing.JFrame {

    private Bahnhof bhf;
    private Spielsteuerung strg;
    private boolean bau;

    /**
     * Creates new form BahnhofGUI
     */
    public BahnhofGUI(Bahnhof b, Spielsteuerung s) {
        bhf = b;
        strg = s;
        initComponents();
        name.setText(b.getName());
        bau = true;

        ImageIcon icon = new ImageIcon(getClass().getResource("images/bahnhof.png"));
        setIconImage(icon.getImage());
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bahnsteig.setText(Integer.toString(bhf.getBahnsteig()));
                anzahlLinien.setText(Integer.toString(bhf.getAnzahlLinien()));
                ein.setText(Integer.toString(bhf.getEingestiegen()));
                aus.setText(Integer.toString(bhf.getAusgestiegen()));
                kasse.setText(Integer.toString(bhf.getKasse()));

                Linie[] linien = bhf.getLinien();
                for (int i = 0; i < linien.length; i++) {
                    if (!linien[i].getBaubar()) {
                        bau = false;
                    }
                }
                if (bau) {
                    bhfAbreissen.setEnabled(true);
                } else {
                    bhfAbreissen.setEnabled(false);
                }
            }
        }, 0, 40);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        bhfAbreissen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bahnsteig = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        anzahlLinien = new javax.swing.JLabel();
        ein = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        aus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kasse = new javax.swing.JLabel();
        hintergrund = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bhf.getName());
        setMaximumSize(minimumSize());
        setMinimumSize(new java.awt.Dimension(274, 365));
        setPreferredSize(new java.awt.Dimension(274, 365));
        setResizable(false);
        getContentPane().setLayout(null);

        name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Bahnhof");
        getContentPane().add(name);
        name.setBounds(0, 11, 274, 22);

        bhfAbreissen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bomb.png"))); // NOI18N
        bhfAbreissen.setToolTipText("Bahnhof abreißen");
        bhfAbreissen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhfAbreissenActionPerformed(evt);
            }
        });
        getContentPane().add(bhfAbreissen);
        bhfAbreissen.setBounds(80, 253, 113, 76);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Wartende Passagiere:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 70, 133, 17);

        bahnsteig.setForeground(new java.awt.Color(255, 255, 255));
        bahnsteig.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        bahnsteig.setText("jLabel4");
        getContentPane().add(bahnsteig);
        bahnsteig.setBounds(190, 70, 34, 14);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Angebundene Linien:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 100, 128, 17);

        anzahlLinien.setForeground(new java.awt.Color(255, 255, 255));
        anzahlLinien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        anzahlLinien.setText("jLabel3");
        getContentPane().add(anzahlLinien);
        anzahlLinien.setBounds(190, 100, 34, 14);

        ein.setForeground(new java.awt.Color(255, 255, 255));
        ein.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ein.setText("ein");
        getContentPane().add(ein);
        ein.setBounds(183, 150, 40, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Verlauf:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 130, 46, 17);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Eingestiegen:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 150, 100, 14);

        aus.setForeground(new java.awt.Color(255, 255, 255));
        aus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        aus.setText("aus");
        getContentPane().add(aus);
        aus.setBounds(183, 170, 40, 14);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ausgestiegen:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 170, 110, 14);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kasse:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 210, 39, 17);

        kasse.setForeground(new java.awt.Color(255, 255, 255));
        kasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        kasse.setText("jLabel7");
        getContentPane().add(kasse);
        kasse.setBounds(114, 210, 110, 14);

        hintergrund.setForeground(new java.awt.Color(255, 255, 255));
        hintergrund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/BGBhf.png"))); // NOI18N
        hintergrund.setMaximumSize(new java.awt.Dimension(280, 360));
        hintergrund.setMinimumSize(new java.awt.Dimension(280, 360));
        hintergrund.setPreferredSize(new java.awt.Dimension(280, 360));
        getContentPane().add(hintergrund);
        hintergrund.setBounds(0, 0, 280, 340);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Nach einer Abfrage ob man sich sicher ist wird der Bahnhof abgerissen und dies der Spielsteuerung übermittelt
     * @param evt 
     */
    private void bhfAbreissenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhfAbreissenActionPerformed

        Object[] options = {"Ja", "Nein"};
        int s = JOptionPane.showOptionDialog(null, "Möchtest du den Bahnhof wirklich abreißen?", "Spiel Beenden",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (s == 0) {
            bhf.letzterSchritt();
            strg.bhfEntfernen(bhf);
            dispose();
        }

    }//GEN-LAST:event_bhfAbreissenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anzahlLinien;
    private javax.swing.JLabel aus;
    private javax.swing.JLabel bahnsteig;
    private javax.swing.JButton bhfAbreissen;
    private javax.swing.JLabel ein;
    private javax.swing.JLabel hintergrund;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel kasse;
    private javax.swing.JLabel name;
    // End of variables declaration//GEN-END:variables
}
