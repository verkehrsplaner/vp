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
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Felix, Maxi
 */
public class LinienGUI extends JFrame {

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
                bilanz.setText(Integer.toString(linie.getGewinn()));
                //System.out.println("Gerundete Auslastung: " + last);

                if (linie.getZuege() == 0) {
                    minus.setEnabled(false);
                } else {
                    minus.setEnabled(true);
                }

                if (strg.getDepot() > 0) {
                    plus.setEnabled(true);
                } else {
                    plus.setEnabled(false);
                }

                if (strg.getBhfs() > 0 && linie.getBaubar()) {
                    jButtonBahnhof.setEnabled(true);
                } else {
                    jButtonBahnhof.setEnabled(false);
                }

                if (bhf.length > 0 && linie.getBaubar()) {
                    jButtonBahnhofWeg.setEnabled(true);
                } else {
                    jButtonBahnhofWeg.setEnabled(false);
                }

            }
        }, 0, 40);

        boolean istgruen = linie.getGruenesLicht();
        // System.out.println(istgruen);
        if (istgruen) {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelgreen.png")));
        } else {
            ampelButton.setIcon(new ImageIcon(getClass().getResource("images/ampelred.png")));
        }

        ImageIcon icon = new ImageIcon(getClass().getResource("images/linie.png"));
        setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
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
        kapazitaet = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auslastung = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gesamtLaenge = new javax.swing.JLabel();
        ampelButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        depot = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bilanz = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Linien Konfiguration");
        setPreferredSize(new java.awt.Dimension(606, 520));
        setResizable(false);

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

        jLabelName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelName.setText(linie.getName());

        jButtonBahnhofWeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bahnhofminus.png"))); // NOI18N
        jButtonBahnhofWeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBahnhofWegActionPerformed(evt);
            }
        });

        jButtonLöschen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bomb.png"))); // NOI18N
        jButtonLöschen.setText("Linie löschen");
        jButtonLöschen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLöschenActionPerformed(evt);
            }
        });

        jButtonFarbe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/palette.png"))); // NOI18N
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
        anzahlZuege.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anzahlZuege.setText(Integer.toString(linie.getZuege()));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Züge im Einsatz");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bahnhöfe");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Kapazität");

        kapazitaet.setText(Integer.toString(linie.kapazitaet()));
        kapazitaet.setName("kapazitaet"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Auslastung");

        auslastung.setText(Double.toString(linie.auslastung()));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Länge");

        gesamtLaenge.setText("jLabel6");

        ampelButton.setToolTipText("Ampel");
        ampelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ampelButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Depot");

        depot.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Bilanz");

        bilanz.setText("jLabel8");

        jButton1.setText("Liniennamen ändern");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonFarbe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(depot))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gesamtLaenge)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(auslastung)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(kapazitaet))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                .addComponent(ampelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(minus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(anzahlZuege, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plus)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonLöschen, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(bilanz))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButtonBahnhof, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBahnhofWeg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(anzahlZuege))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kapazitaet)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(auslastung))
                            .addComponent(ampelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bilanz)
                            .addComponent(depot)
                            .addComponent(gesamtLaenge))
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBahnhofWeg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonBahnhof, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonLöschen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFarbe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Zuerst wird ein neues Feld erstellt, in denen die Buttontexte festgelegt werden. Danach wird dem Benutzer ein JOptionPane angezeigt, in welchem er
     * gefragt wird, ob die Linien gelöscht werden soll. Wenn "Ja" geklickt wird (0), wird die Linie aus der Spielsteuerung gelöscht und das Linien Fenster
     * geschlossen.
     *
     * @param evt Neues ungenutztes Event
     */
    private void jButtonLöschenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLöschenActionPerformed
        Object[] options = {"Ja", "Nein"};
        int s = JOptionPane.showOptionDialog(null, "Möchtest du die Linie wirklich löschen?", "Spiel Beenden",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
        if (s == 0) {
            strg.linieEntfernen(linie);
            dispose();
        }


    }//GEN-LAST:event_jButtonLöschenActionPerformed

    /**
     * Dem Integer wird der ausgewählte Eintrag zugewiesen.
     * Nun kann man in einem OptionPane der Liste einen weiteren Bahnhof hinzufügen
     *
     * @param evt Neues ungenutztes Event
     */
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

    /**
     * Aus der Liste wird der ausgewählte Bahnhof entnommen
     *
     * @param evt
     */
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

    /**
     * In einem Java Color Chooser kann eine neue Farbe für die Linie ausgewählt werden
     *
     * @param evt
     */
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

    /**
     * Die Linie wird freigegeben oder und in der Klasse Linie wird setGruenesLicht auf true oder false gesetzt
     *
     * @param evt
     */
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

    /**
     * Ein neuer Name für die geöffnete Linie kann hier eingestellt werden
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String s = JOptionPane.showInputDialog("Bitte Name für die neue Line eingeben:"); //Fenster für Linienname
        if (s != null && !s.equals("")) {
            linie.setName(s);
        }
        this.repaint();
        jLabelName.setText(s);
        this.setTitle(s);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ampelButton;
    private javax.swing.JLabel anzahlZuege;
    private javax.swing.JLabel auslastung;
    private javax.swing.JLabel bilanz;
    private javax.swing.JLabel depot;
    private javax.swing.JLabel gesamtLaenge;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kapazitaet;
    private javax.swing.JButton minus;
    private javax.swing.JButton plus;
    // End of variables declaration//GEN-END:variables
}
