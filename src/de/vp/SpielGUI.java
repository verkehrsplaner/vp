
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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxi & Hecker
 */
public class SpielGUI extends javax.swing.JFrame {

    private Spielsteuerung strg;
    private int hoehe, breite;
    private int hPos, vPos, hView, vView;
    private DefaultListModel linienListe;
    private LinienCellRenderer linienRenderer;
    private Linie[] linien;
    private Sound sound;

    /**
     * Erstellt das Fenster. Zusätzlich wird ein Timer initialisert, welcher die
     * Uhrzeit automatisch aktualisiert.
     *
     * @param h
     * @param b
     */
    public SpielGUI(int h, int b) {
//        setExtendedState(JFrame.MAXIMIZED_BOTH); // Öffnet das Fenster in Fullscreen 
        strg = new Spielsteuerung(h, b);
        linienListe = new DefaultListModel();
        hoehe = h;
        breite = b;
        linienRenderer = new LinienCellRenderer();
        sound = new Sound();
        initComponents();
        
        strg.panelStarten(jPanel3);
        strg.setTicker(tickerPanel);
        
        sound.musikAn();

        hView = jScrollPane3.getViewport().getWidth();
        vView = jScrollPane3.getViewport().getHeight();

        jScrollPane3.getHorizontalScrollBar().setValue((jScrollPane3.getHorizontalScrollBar().getMaximum() - hView) / 2);
        jScrollPane3.getVerticalScrollBar().setValue((jScrollPane3.getVerticalScrollBar().getMaximum() - vView) / 2);

        hPos = jScrollPane3.getHorizontalScrollBar().getValue();
        vPos = jScrollPane3.getVerticalScrollBar().getValue();

        //Stellt die Scrollgeschwindigkeit ein
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane3.getHorizontalScrollBar().setUnitIncrement(10);

        // ========== Formatiert verschiedene Variabeln ==========
        final SimpleDateFormat formatDatum = new SimpleDateFormat("HH:mm"); // Erstellt neuen "Kalender"
        final NumberFormat formatGeld = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
        formatGeld.setCurrency(Currency.getInstance("EUR"));

        uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
        geldLabel.setText(formatGeld.format(strg.getGeld()));
        anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
        werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
        bilanzLabel.setText(formatGeld.format(strg.gesamtGewinn()));
        // ========== Ende Formatierung ==========
        
        
        linien = strg.getLinien();

        //Timer für Uhrzeit
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
                geldLabel.setText(formatGeld.format(strg.getGeld()));
                anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
                werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
                long bilanz = strg.gesamtGewinn();
                if (bilanz < 0) {
                    bilanzLabel.setForeground(Color.RED);
                } else {
                    bilanzLabel.setForeground(new Color(50, 158, 0));
                }
                bilanzLabel.setText(formatGeld.format(bilanz));
                Linie[] linienTemp = strg.getLinien();
                if (linienTemp.length != linien.length) {
                    linien = linienTemp;
                    linienListe.clear();
                    for (int i = 0; i < linien.length; i++) {
                        linienListe.addElement(linien[i]);
                    }
                }
            }
        }, 0, 40);
        
        tickerPanel.getPixel();
        Thread th = new Thread(tickerPanel);
        th.start();
        
        //Setzt das Programm Icon
        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());

        jPanel3.requestFocus();
        
        //Button Abfrage
        if (strg.getDepot() == 0) {
            minusButton.setEnabled(false);
        }
        else{
            minusButton.setEnabled(true);
        }
        
        if (strg.getWerkstatt() == 0) {
            reparierenButton.setEnabled(false);
        }
        else{
            reparierenButton.setEnabled(true);
        }
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
        jPanel3 = new SpielPanel(hoehe, breite, strg, this);
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
        plusButton = new javax.swing.JButton();
        bahnhofBauenButton = new javax.swing.JButton();
        linieBauenButton = new javax.swing.JButton();
        Einstellungen = new javax.swing.JButton();
        bilanzLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        tickerPanel = new TickerPanel();

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
        minusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/zug-.png"))); // NOI18N
        minusButton.setToolTipText("Zug verkaufen!");
        minusButton.setPreferredSize(new java.awt.Dimension(50, 50));
        minusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusButtonActionPerformed(evt);
            }
        });

        werkstattNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        werkstattNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        werkstattNameLabel.setText("Werkstatt:");

        reparierenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/hammer.png"))); // NOI18N
        reparierenButton.setToolTipText("Zug reparieren!");
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

        plusButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        plusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/zug+.png"))); // NOI18N
        plusButton.setToolTipText("Zug kaufen!");
        plusButton.setPreferredSize(new java.awt.Dimension(50, 50));
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });

        bahnhofBauenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bahnhofplus.png"))); // NOI18N
        bahnhofBauenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bahnhofBauenButtonActionPerformed(evt);
            }
        });

        linieBauenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/linie bauen transparent.png"))); // NOI18N
        linieBauenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linieBauenButtonActionPerformed(evt);
            }
        });

        Einstellungen.setText("Menü");
        Einstellungen.setToolTipText("Menü");
        Einstellungen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Einstellungen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EinstellungenActionPerformed(evt);
            }
        });

        bilanzLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bilanzLabel.setText("Gesamtgewinn");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jList1.setModel(linienListe);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCellRenderer(linienRenderer);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Einstellungen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bahnhofBauenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(linieBauenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5)
                    .addComponent(bilanzLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(uhrzeitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(geldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(depotnameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(anzahldepotLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(linienNameLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(werkstattAnzahlLabel))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(werkstattNameLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reparierenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(depotnameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anzahldepotLabel))
                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(werkstattNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(werkstattAnzahlLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reparierenButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linienNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(linieBauenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bahnhofBauenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(Einstellungen)
                .addContainerGap())
        );

        tickerPanel.setOpaque(false);

        javax.swing.GroupLayout tickerPanelLayout = new javax.swing.GroupLayout(tickerPanel);
        tickerPanel.setLayout(tickerPanelLayout);
        tickerPanelLayout.setHorizontalGroup(
            tickerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tickerPanelLayout.setVerticalGroup(
            tickerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tickerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addComponent(tickerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EinstellungenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EinstellungenActionPerformed
        System.out.println("Button 'Menü' wurde gedrückt!");
        jPanel3.requestFocus();
        JDialog f = new MenuGUI(sound, (SpielPanel)jPanel3, strg);
        f.setModal(true);
        f.setVisible(true);
    }//GEN-LAST:event_EinstellungenActionPerformed

    private void linieBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linieBauenButtonActionPerformed
        System.out.println("Button 'Linie Bauen!' wurde gedrückt!");
        // Neues Fenster machen, um Name für Linie eingeben zu können
        String s = JOptionPane.showInputDialog("Bitte Name für die neue Line eingeben:");
        if (s != null && !s.equals("")) {
            strg.neueLinie(s);
        }
        jPanel3.requestFocus();
    }//GEN-LAST:event_linieBauenButtonActionPerformed

    private void bahnhofBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bahnhofBauenButtonActionPerformed
        System.out.println("Button 'Bahnhof Bauen!' wurde gedrückt!");
        strg.setNextAction("bhf");
        jPanel3.requestFocus();
    }//GEN-LAST:event_bahnhofBauenButtonActionPerformed

    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
        System.out.println("Button 'Zug Kaufen!' wurde gedrückt!");
        strg.zugKaufen();
        jPanel3.requestFocus();
        if (strg.getDepot() == 0) {
            minusButton.setEnabled(false);
        }
        else{
            minusButton.setEnabled(true);
        }
    }//GEN-LAST:event_plusButtonActionPerformed

    private void reparierenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparierenButtonActionPerformed
        System.out.println("Button 'Zug Reparieren!' wurde gedrückt!");
        strg.zugReparieren();
        jPanel3.requestFocus();
        if (strg.getWerkstatt() == 0) {
            reparierenButton.setEnabled(false);
        }
        else{
            reparierenButton.setEnabled(true);
        }
    }//GEN-LAST:event_reparierenButtonActionPerformed

    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        System.out.println("Button 'Zug Verschrotten!' wurde gedrückt!");
        strg.zugVerschrotten();
        jPanel3.requestFocus();
        if (strg.getDepot() == 0) {
            minusButton.setEnabled(false);
        }
        else{
            minusButton.setEnabled(true);
        }
    }//GEN-LAST:event_minusButtonActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int pos = jList1.getSelectedIndex();
        if (pos >= 0) {
            JDialog f = new LinienGUI(linien[pos], strg);
            f.setModal(true);
            f.setVisible(true);
        }
    }//GEN-LAST:event_jList1MouseClicked

    public void saveScrollValues() {
        hPos = jScrollPane3.getHorizontalScrollBar().getValue();
        vPos = jScrollPane3.getVerticalScrollBar().getValue();
        hView = jScrollPane3.getViewport().getWidth();
        vView = jScrollPane3.getViewport().getHeight();
    }

    public void zoomIn() {
        jScrollPane3.getVerticalScrollBar().setValue(0);
        jScrollPane3.getVerticalScrollBar().setValue(vPos * 2 + vView / 2);
        jScrollPane3.getHorizontalScrollBar().setValue(hPos * 2 + hView / 2);
        this.saveScrollValues();
    }

    public void zoomOut() {
        jScrollPane3.getHorizontalScrollBar().setValue(hPos / 2 - hView / 4);
        jScrollPane3.getVerticalScrollBar().setValue(vPos / 2 - vView / 4);
        this.saveScrollValues();
    }

    public int getLinienNr() {
        return jList1.getSelectedIndex();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Einstellungen;
    private javax.swing.JLabel anzahldepotLabel;
    private javax.swing.JButton bahnhofBauenButton;
    private javax.swing.JLabel bilanzLabel;
    private javax.swing.JLabel depotnameLabel;
    private javax.swing.JLabel geldLabel;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
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
    /*
    private javax.swing.JPanel tickerPanel;
    */
    private TickerPanel tickerPanel;
    private javax.swing.JLabel uhrzeitLabel;
    private javax.swing.JLabel werkstattAnzahlLabel;
    private javax.swing.JLabel werkstattNameLabel;
    // End of variables declaration//GEN-END:variables

}
