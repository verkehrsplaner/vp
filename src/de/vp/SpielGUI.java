
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxi & Hecker
 */
public class SpielGUI extends javax.swing.JFrame {

    private Spielsteuerung strg;
    private int hoehe, breite;

    /**
     * Erstellt das Fenster. Zusätzlich wird ein Timer initialisert, welcher die
     * Uhrzeit automatisch aktualisiert.
     *
     * @param h
     * @param b
     */
    public SpielGUI(int h, int b) {
        strg = new Spielsteuerung(h, b);
        hoehe = h;
        breite = b;
        initComponents();
        strg.panelStarten(jPanel3);
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane3.getHorizontalScrollBar().setUnitIncrement(10);
        final SimpleDateFormat formatDatum = new SimpleDateFormat("HH:mm"); // Erstellt neuen "Kalender"
        final NumberFormat formatGeld = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
        formatGeld.setCurrency(Currency.getInstance("EUR"));
        uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
        geldLabel.setText(formatGeld.format(strg.getGeld()));
        anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
        werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
        bilanzLabel.setText(formatGeld.format(strg.gesamtGewinn()));
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Öffnet das Fenster in Fullscreen  

        //Timer für Uhrzeit
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
                geldLabel.setText(formatGeld.format(strg.getGeld()));
                anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
                werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
                int bilanz = strg.gesamtGewinn();
                if (bilanz < 0) {
                    bilanzLabel.setForeground(Color.RED);
                } else {
                    bilanzLabel.setForeground(Color.GREEN);
                }
                bilanzLabel.setText(formatGeld.format(bilanz));
            }
        }, 0, 40);

        //Seticon entweder
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
//        oder
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));
        setIconImage(icon.getImage());

        /**
         * Creates new form SpielGUI
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new SpielPanel(hoehe, breite, strg);
        jPanel1 = new javax.swing.JPanel();
        uhrzeitLabel = new javax.swing.JLabel();
        geldLabel = new javax.swing.JLabel();
        depotnameLabel = new javax.swing.JLabel();
        anzahldepotLabel = new javax.swing.JLabel();
        minusButton = new javax.swing.JButton();
        werkstattNameLabel = new javax.swing.JLabel();
        reparierenButton = new javax.swing.JButton();
        linienNameLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        werkstattAnzahlLabel = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        plusButton = new javax.swing.JButton();
        bahnhofBauenButton = new javax.swing.JButton();
        linieBauenButton = new javax.swing.JButton();
        Einstellungen = new javax.swing.JButton();
        bilanzLabel = new javax.swing.JLabel();
        jPanel4 = new TickerPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Verkehrsplaner");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel3);

        uhrzeitLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        uhrzeitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uhrzeitLabel.setText("18:37");

        geldLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        geldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        geldLabel.setText("100.000.000 €");

        depotnameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        depotnameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        depotnameLabel.setText("Depot");

        anzahldepotLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        anzahldepotLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anzahldepotLabel.setText("100");

        minusButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        minusButton.setText("-");
        minusButton.setPreferredSize(new java.awt.Dimension(50, 50));
        minusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusButtonActionPerformed(evt);
            }
        });

        werkstattNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        werkstattNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        werkstattNameLabel.setText("Werkstatt:");

        reparierenButton.setText("Zug reparieren");
        reparierenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reparierenButtonActionPerformed(evt);
            }
        });

        linienNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        linienNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        linienNameLabel.setText("Linien");

        werkstattAnzahlLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        werkstattAnzahlLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        werkstattAnzahlLabel.setText("100");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setPreferredSize(new java.awt.Dimension(159, 900));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        plusButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        plusButton.setText("+");
        plusButton.setPreferredSize(new java.awt.Dimension(50, 50));
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });

        bahnhofBauenButton.setText("Bahnhof bauen");
        bahnhofBauenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bahnhofBauenButtonActionPerformed(evt);
            }
        });

        linieBauenButton.setText("Neue Linie erstellen");
        linieBauenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linieBauenButtonActionPerformed(evt);
            }
        });

        Einstellungen.setText("Einstellungen");
        Einstellungen.setToolTipText("Sicher dass du diesen Knopf drücken willst?");
        Einstellungen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Einstellungen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EinstellungenActionPerformed(evt);
            }
        });

        bilanzLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bilanzLabel.setText("Gesamtgewinn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Einstellungen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(linieBauenButton, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
            .addComponent(bahnhofBauenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(depotnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reparierenButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(anzahldepotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(uhrzeitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(geldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bilanzLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(werkstattNameLabel)
                                .addGap(30, 30, 30)
                                .addComponent(werkstattAnzahlLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(linienNameLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uhrzeitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(geldLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bilanzLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(depotnameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anzahldepotLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(werkstattNameLabel)
                    .addComponent(werkstattAnzahlLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reparierenButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linienNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bahnhofBauenButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linieBauenButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(Einstellungen)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EinstellungenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EinstellungenActionPerformed
        System.out.println("Button 'Einstellungen' wurde gedrückt!");
        JOptionPane.showMessageDialog(null, "Diese Funktion ist noch nicht verfügbar.", "Fehler", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_EinstellungenActionPerformed

    private void linieBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linieBauenButtonActionPerformed
        System.out.println("Button 'Linie Bauen!' wurde gedrückt!");
        // Neues Fenster machen, um Name für Linie eingeben zu können
        String s = JOptionPane.showInputDialog("Bitte Name für die neue Line eingeben:");
        if (s != null && !s.equals("")) {
            strg.neueLinie(s);
        }
    }//GEN-LAST:event_linieBauenButtonActionPerformed

    private void bahnhofBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bahnhofBauenButtonActionPerformed
        System.out.println("Button 'Bahnhof Bauen!' wurde gedrückt!");
        strg.setNextAction("bhf");
    }//GEN-LAST:event_bahnhofBauenButtonActionPerformed

    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
        System.out.println("Button 'Zug Kaufen!' wurde gedrückt!");
        strg.zugKaufen();
    }//GEN-LAST:event_plusButtonActionPerformed

    private void reparierenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparierenButtonActionPerformed
        System.out.println("Button 'Zug Reparieren!' wurde gedrückt!");
        strg.zugReparieren();
    }//GEN-LAST:event_reparierenButtonActionPerformed

    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        System.out.println("Button 'Zug Verschrotten!' wurde gedrückt!");
        strg.zugVerschrotten();
    }//GEN-LAST:event_minusButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Einstellungen;
    private javax.swing.JLabel anzahldepotLabel;
    private javax.swing.JButton bahnhofBauenButton;
    private javax.swing.JLabel bilanzLabel;
    private javax.swing.JLabel depotnameLabel;
    private javax.swing.JLabel geldLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton linieBauenButton;
    private javax.swing.JLabel linienNameLabel;
    private javax.swing.JButton minusButton;
    private javax.swing.JButton plusButton;
    private javax.swing.JButton reparierenButton;
    private javax.swing.JLabel uhrzeitLabel;
    private javax.swing.JLabel werkstattAnzahlLabel;
    private javax.swing.JLabel werkstattNameLabel;
    // End of variables declaration//GEN-END:variables

}
