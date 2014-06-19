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
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
                double eigentlichelast = linie.auslastung();
                //System.out.print("Eigentliche Auslastung: " + eigentlichelast + "      ");
                double last = linie.auslastung();
                last = Math.round(last * 1000) / 10.0;
                auslastung.setText(Double.toString(last) + " %");
                gesamtLaenge.setText(Integer.toString(linie.gesamtLaenge()));
                depot.setText(Integer.toString(linie.getDepot()));
                //System.out.println("Gerundete Auslastung: " + last);

                if (!linie.getBaubar()) {
                    jButtonBahnhof.setEnabled(false);
                    jButtonBahnhofWeg.setEnabled(false);
                } else {
                    jButtonBahnhof.setEnabled(true);
                    jButtonBahnhofWeg.setEnabled(true);
                }
                
                
            }
        }, 0, 40);

        boolean istgruen = linie.getGruenesLicht();
        System.out.println(istgruen);
        if (istgruen) {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelgreen.png")));
        } else {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelred.png")));
        }

        ImageIcon icon = new ImageIcon(getClass().getResource("images/linie.png"));
        setIconImage(icon.getImage());
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
        jLabel5 = new javax.swing.JLabel();
        gesamtLaenge = new javax.swing.JLabel();
        ampelButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        depot = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Linien Konfiguration");

        jList1.setModel(bhfListe);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jButtonBahnhof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bahnhofplus.png"))); // NOI18N
        jButtonBahnhof.setToolTipText("Bahnhof hinzufügen");
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

        jLabel5.setText("Länge:");

        gesamtLaenge.setText("jLabel6");

        ampelButton.setToolTipText("Ampel");
        ampelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ampelButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Depot:");

        depot.setText("jLabel7");

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
                                .addComponent(plus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kapazitaet))
                                    .addComponent(auslastung)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(gesamtLaenge))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(depot)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(ampelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonBahnhof, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBahnhofWeg, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(kapazitaet)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(auslastung)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(gesamtLaenge)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(ampelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(depot))
                        .addGap(53, 53, 53))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonBahnhofWeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonBahnhof, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButtonFarbe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLöschen)
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLöschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLöschenActionPerformed
        strg.linieEntfernen(linie);
        dispose();
    }//GEN-LAST:event_jButtonLöschenActionPerformed

    private void jButtonBahnhofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBahnhofActionPerformed
        int pos = jList1.getSelectedIndex();
        int x = -1;
        if (pos < 0) {
            Bahnhof[] inLinie = linie.getBahnhof();
            Bahnhof[] liste = strg.getBahnhofListe(inLinie);

            if (liste.length > 0) {
                Bahnhof bhf = (Bahnhof) JOptionPane.showInputDialog(this,
                        "Bahnhof auswählen:",
                        "Bahnhof",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        liste,
                        liste[0]);
                if (bhf != null) {
                    if (inLinie.length > 0) {
                        linie.bahnhofHinzufuegen(bhf, inLinie[inLinie.length - 1]);
                    } else {
                        linie.bahnhofHinzufuegen(bhf, null);
                    }
                }
            }
        } else if (pos == 0) {
            Bahnhof[] inLinie = linie.getBahnhof();
            Bahnhof[] liste = strg.getBahnhofListe(inLinie);

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
            Bahnhof[] inLinie = linie.getBahnhof();
            Bahnhof[] liste = strg.getBahnhofListe(inLinie);

            if (liste.length > 0) {
                Bahnhof bhf = (Bahnhof) JOptionPane.showInputDialog(this,
                        "Bahnhof auswählen:",
                        "Bahnhof",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        liste,
                        liste[0]);
                if (bhf != null) {
                    linie.bahnhofHinzufuegen(bhf, inLinie[pos]);
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
        strg.zugRausnehmen(linie);
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

    private void ampelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ampelButtonActionPerformed
        boolean istgruen = linie.getGruenesLicht();
        if (istgruen) {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelred.png")));
            linie.setGruenesLicht(false);
        } else {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelgreen.png")));
            linie.setGruenesLicht(true);
        }
    }//GEN-LAST:event_ampelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ampelButton;
    private javax.swing.JLabel anzahlZuege;
    private javax.swing.JLabel auslastung;
    private javax.swing.JLabel depot;
    private javax.swing.JLabel gesamtLaenge;
    private javax.swing.JButton jButtonBahnhof;
    private javax.swing.JButton jButtonBahnhofWeg;
    private javax.swing.JButton jButtonFarbe;
    private javax.swing.JButton jButtonLöschen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel kapazitaet;
    private javax.swing.JButton minus;
    private javax.swing.JButton plus;
    // End of variables declaration//GEN-END:variables
}
