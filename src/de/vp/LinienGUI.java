/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Felix
 */
public class LinienGUI extends JDialog {

    private Linie linie;
    private Spielsteuerung strg;
    private DefaultListModel bhfListe;
    private Bahnhof[] bhf;

    /**
     * Creates new form LinienGUI
     */
    public LinienGUI(Linie l, Spielsteuerung s) {
        linie = l;
        bhfListe = new DefaultListModel();
        bhf = linie.getBahnhof();
        bhfListe.addElement("<Anfang>");
        for (int i = 0; i < bhf.length; i++) {
            bhfListe.addElement(bhf[i].getName());
        }
        this.setTitle(linie.getName());
        strg = s;
        initComponents();
        jLabelName.setForeground(linie.getFarbe());
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                anzahlZuege.setText(Integer.toString(linie.getZuege()));
                kapazitaet.setText(Integer.toString(linie.kapazitaet()));
                auslastung.setText(Double.toString(linie.auslastung()));
            }
        }, 0, 40);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButtonBahnhof = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        jButtonBahnhofWeg = new javax.swing.JButton();
        jButtonLöschen = new javax.swing.JButton();
        jButtonFarbe = new javax.swing.JButton();
        minus = new javax.swing.JButton();
        plus = new javax.swing.JButton();
        anzahlZuege = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        kapazitaet = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auslastung = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.setModel(bhfListe);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jButtonBahnhof.setText("Bahnhof hinzufügen");
        jButtonBahnhof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBahnhofActionPerformed(evt);
            }
        });

        jLabelName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelName.setText(linie.getName());

        jButtonBahnhofWeg.setText("Bahnhof entfernen");
        jButtonBahnhofWeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBahnhofWegActionPerformed(evt);
            }
        });

        jButtonLöschen.setText("Linie löschen");
        jButtonLöschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLöschenActionPerformed(evt);
            }
        });

        jButtonFarbe.setText("Farbe ändern");
        jButtonFarbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFarbeActionPerformed(evt);
            }
        });

        minus.setText("-");
        minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusActionPerformed(evt);
            }
        });

        plus.setText("+");
        plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusActionPerformed(evt);
            }
        });

        anzahlZuege.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        anzahlZuege.setText(Integer.toString(linie.getZuege()));

        jLabel2.setText("Züge im Einsatz:");

        jLabel1.setText("Bahnhöfe:");

        jLabel3.setText("Kapazität:");

        kapazitaet.setText(Integer.toString(linie.kapazitaet()));
        kapazitaet.setName("kapazitaet"); // NOI18N

        jLabel4.setText("Auslastung:");

        auslastung.setText(Double.toString(linie.auslastung()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonLöschen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFarbe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(minus)
                                .addGap(50, 50, 50)
                                .addComponent(anzahlZuege)
                                .addGap(27, 27, 27)
                                .addComponent(plus))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kapazitaet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(auslastung))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonBahnhof, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jButtonBahnhofWeg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plus, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(anzahlZuege))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kapazitaet)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(auslastung)
                        .addGap(125, 125, 125))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonBahnhof)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBahnhofWeg)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButtonFarbe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLöschen)
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLöschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLöschenActionPerformed
        strg.linieEntfernen(linie);
        dispose();
    }//GEN-LAST:event_jButtonLöschenActionPerformed

    private void jButtonBahnhofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBahnhofActionPerformed
        int pos = jList1.getSelectedIndex();
        if (pos <= 0) {
            Bahnhof[] liste = strg.getBahnhofListe();
            if (liste.length > 0) {
                Bahnhof bhf = (Bahnhof) JOptionPane.showInputDialog(this,
                        "Bahnhof auswählen:",
                        "Bahnhof",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        liste,
                        liste[0]);
                if (bhf != null) {
                    linie.bahnhofHinzufuegen(bhf, null);
                }
            }
        } else if (pos > 0) {
            pos = pos - 1;
            Bahnhof[] liste = strg.getBahnhofListe();
            if (liste.length > 0) {
                Bahnhof bhf = (Bahnhof) JOptionPane.showInputDialog(this,
                        "Bahnhof auswählen:",
                        "Bahnhof",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        liste,
                        liste[0]);
                if (bhf != null) {
                    linie.bahnhofHinzufuegen(bhf, linie.getBahnhof()[pos]);
                }
            }
        }
        bhfListe.clear();
        bhfListe.addElement("<Anfang>");
        bhf = linie.getBahnhof();
        for (int i = 0; i < bhf.length; i++) {
            bhfListe.addElement(bhf[i].getName());
        }
    }//GEN-LAST:event_jButtonBahnhofActionPerformed

    private void jButtonBahnhofWegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBahnhofWegActionPerformed
        int pos = jList1.getSelectedIndex() - 1;
        if (pos >= 0) {
            linie.bahnhofEntfernen(bhf[pos]);
        }
        bhfListe.clear();
        bhfListe.addElement("<Anfang>");
        bhf = linie.getBahnhof();
        for (int i = 0; i < bhf.length; i++) {
            bhfListe.addElement(bhf[i].getName());
        }
    }//GEN-LAST:event_jButtonBahnhofWegActionPerformed

    private void jButtonFarbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFarbeActionPerformed
        Color farbe = JColorChooser.showDialog(this, "Neue Farbe wählen", linie.getFarbe());
        if (farbe != null) {
            linie.setFarbe(farbe);
        }
    }//GEN-LAST:event_jButtonFarbeActionPerformed

    /**
     * Ein Zug wird der Linie hinzugefügt
     *
     * @param evt
     */
    private void minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusActionPerformed
        strg.zugInsDepot(linie);
        anzahlZuege.setText(String.valueOf(linie.getZuege()));
        kapazitaet.setText(String.valueOf(linie.kapazitaet()));
        auslastung.setText(String.valueOf(linie.auslastung()));
    }//GEN-LAST:event_minusActionPerformed

    /**
     * Ein Zug wird der Linie entnommen
     *
     * @param evt
     */
    private void plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusActionPerformed
        strg.zugEinstellen(linie);
        anzahlZuege.setText(String.valueOf(linie.getZuege()));
        kapazitaet.setText(String.valueOf(linie.kapazitaet()));
        auslastung.setText(String.valueOf(linie.auslastung()));
    }//GEN-LAST:event_plusActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anzahlZuege;
    private javax.swing.JLabel auslastung;
    private javax.swing.JButton jButtonBahnhof;
    private javax.swing.JButton jButtonBahnhofWeg;
    private javax.swing.JButton jButtonFarbe;
    private javax.swing.JButton jButtonLöschen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel kapazitaet;
    private javax.swing.JButton minus;
    private javax.swing.JButton plus;
    // End of variables declaration//GEN-END:variables
}
