
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxi, Hecker
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
     * Erstellt das Fenster. Zusätzlich wird ein Timer initialisert, welcher die Uhrzeit automatisch aktualisiert.
     *
     * @param h
     * @param b
     */
    public SpielGUI(int h, int b, Sound s) {
        strg = new Spielsteuerung(h, b);
        linienListe = new DefaultListModel();
        hoehe = h;
        breite = b;
        linienRenderer = new LinienCellRenderer();
        sound = s;
        initComponents();
        initRest();
    }

    public SpielGUI(Path file, Sound s) {
        strg = new Spielsteuerung(file);
        linienListe = new DefaultListModel();
        hoehe = strg.getHoehe();
        breite = strg.getBreite();
        linienRenderer = new LinienCellRenderer();
        sound = s;
        initComponents();
        initRest();
    }

    /**
     * Panel wird initialisiert Ticker ebenso Musik auch Das Panel wird in die Mitte gesetzt Durch unitincrement wird die Scrollgeschwindigkeit der Scrollbars
     * eingestellt jLabels werden formatiert Ein Timer wird gestartet, welcher die jLabel regelmäßig aktualisiert und Button überprüft
     */
    private void initRest() {
        strg.panelStarten(jPanel3);
        strg.setTicker(tickerPanel);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyControlls());

        sound.atmoAn();

        hView = jScrollPane3.getViewport().getWidth();
        vView = jScrollPane3.getViewport().getHeight();

        // Ansicht zentrieren
        jScrollPane3.getHorizontalScrollBar().setValue((jScrollPane3.getHorizontalScrollBar().getMaximum() - hView) / 2);
        jScrollPane3.getVerticalScrollBar().setValue((jScrollPane3.getVerticalScrollBar().getMaximum() - vView) / 2);
        hPos = jScrollPane3.getHorizontalScrollBar().getValue();
        vPos = jScrollPane3.getVerticalScrollBar().getValue();

        //Stellt die Scrollgeschwindigkeit ein
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(25);
        jScrollPane3.getHorizontalScrollBar().setUnitIncrement(25);

        // ========== Formatiert verschiedene Variabeln ==========
        final SimpleDateFormat formatDatum = new SimpleDateFormat("HH:mm"); // Erstellt neuen "Kalender"
        formatDatum.setTimeZone(TimeZone.getTimeZone("GMT"));
        final NumberFormat formatGeld = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
        formatGeld.setCurrency(Currency.getInstance("EUR"));

        uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
        geldLabel.setText(formatGeld.format(strg.getGeld()));
        anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
        werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
        bilanzLabel.setText(formatGeld.format(strg.gesamtGewinn()));
        // ========== Ende Formatierung ==========

        linien = strg.getLinien();
        linienListe.clear();
        for (int i = 0; i < linien.length; i++) {
            linienListe.addElement(linien[i]);
        }

        //Timer für Uhrzeit und andere Dinge
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                // ========== Initialisierung und Formatierung der JLabels =========
                if (strg.getPause() == false) {
                    uhrzeitLabel.setText(formatDatum.format(strg.getTime()));
                } else {
                    uhrzeitLabel.setText("PAUSE");
                }

                if (strg.isVerloren()) {
                    uhrzeitLabel.setText("GAME OVER!");
                }

                geldLabel.setText(formatGeld.format(strg.getGeld()));
                anzahldepotLabel.setText(Integer.toString(strg.getDepot()));
                werkstattAnzahlLabel.setText(Integer.toString(strg.getWerkstatt()));
                long bilanz = strg.getBilanz();
                if (bilanz < 0) {
                    bilanzLabel.setForeground(Color.RED);
                } else {
                    bilanzLabel.setForeground(new Color(50, 158, 0));
                }
                bilanzLabel.setText(formatGeld.format(bilanz));
                Linie[] linienTemp = strg.getLinien();

                // ========== Formatierung Ende ==========
                if (linienTemp.length != linien.length) {
                    linien = linienTemp;
                    linienListe.clear();
                    for (int i = 0; i < linien.length; i++) {
                        linienListe.addElement(linien[i]);
                    }
                }

                // De - aktiviert den Bahnhof verkaufen Button
                if (strg.getDepot() == 0) {
                    minusButton.setEnabled(false);
                } else {
                    minusButton.setEnabled(true);
                }


                // ======== Abfrage ob Sachen noch kaufbar sind oder nicht ========
                // Zugkaufen Button
                if (strg.istkaeuflich(strg.getPreisZug())) {
                    plusButton.setEnabled(true);
                } else {
                    plusButton.setEnabled(false);
                }

                // Linie Button
                if (strg.istkaeuflich(strg.getPreisLinie())) {
                    linieBauenButton.setEnabled(true);
                } else {
                    linieBauenButton.setEnabled(false);
                }

                //  Bahnhofkaufen Button
                if (strg.istkaeuflich(strg.getPreisBhf())) {
                    bahnhofBauenButton.setEnabled(true);
                } else {
                    bahnhofBauenButton.setEnabled(false);
                }

                // Reparieren Button
                if (strg.istkaeuflich(strg.getReparatur()) && strg.getWerkstatt() > 0) {
                    reparierenButton.setEnabled(true);
                } else {
                    reparierenButton.setEnabled(false);
                }

                // ======== Überprüfung ENDE ========
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
        } else {
            minusButton.setEnabled(true);
        }

        if (strg.getWerkstatt() == 0) {
            reparierenButton.setEnabled(false);
        } else {
            reparierenButton.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
        minusButton.setToolTipText("Schrottpreis: " + strg.getGeldZugZurueck() + " €");
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
        reparierenButton.setToolTipText("Reparieren: " + strg.getReparatur() +  " €");
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
        plusButton.setToolTipText("Kosten: " + strg.getPreisZug() + " €");
        plusButton.setPreferredSize(new java.awt.Dimension(50, 50));
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });

        bahnhofBauenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/bahnhofplus.png"))); // NOI18N
        bahnhofBauenButton.setToolTipText("Kosten: " + strg.getPreisBhf() + " €");
        bahnhofBauenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bahnhofBauenButtonActionPerformed(evt);
            }
        });

        linieBauenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/linie bauen transparent.png"))); // NOI18N
        linieBauenButton.setToolTipText("Kosten: " + strg.getPreisLinie() + " €");
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Öffnet neue MenueGUI
     *
     * @param evt Neues ungenutztes Event
     */
    private void EinstellungenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EinstellungenActionPerformed
        JDialog f = new MenuGUI(sound, (SpielPanel) jPanel3, strg); //Öffnet neue GUI
        f.setModal(true);
        f.setVisible(true);
    }//GEN-LAST:event_EinstellungenActionPerformed

    /**
     * Es erscheint ein OptionPane, in welchem der Variable s ein neuer Wert zugewiesen wird, welcher der neue Linien Name sein soll. Wenn der String s nicht
     * leer ist, wird eine neue Linie in der Spielsteuerung erzeugt.
     *
     * @param evt Neues ungenutztes Event
     */
    private void linieBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linieBauenButtonActionPerformed

        String s = JOptionPane.showInputDialog("Bitte Name für die neue Line eingeben:"); //Fenster für Linienname
        if (s != null && !s.equals("")) {
            strg.neueLinie(s);
        }
    }//GEN-LAST:event_linieBauenButtonActionPerformed

    private void bahnhofBauenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bahnhofBauenButtonActionPerformed
        strg.setNextAction("bhf");
    }//GEN-LAST:event_bahnhofBauenButtonActionPerformed

    /**
     * In der Klasse Spielsteuerung wird ein Zug erstellt. Zusätzlich wird überprüft, ob das Depot leer ist. Falls dies der Fall ist, wird der Button
     * deaktiviert.
     *
     * @param evt Neues ungenutztes Event
     */
    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
        strg.zugKaufen();
        if (strg.getDepot() == 0) {
            minusButton.setEnabled(false);
        } else {
            minusButton.setEnabled(true);
        }
    }//GEN-LAST:event_plusButtonActionPerformed

    private void reparierenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparierenButtonActionPerformed
        strg.zugReparieren();
        if (strg.getWerkstatt() == 0) {
            reparierenButton.setEnabled(false);
        } else {
            reparierenButton.setEnabled(true);
        }
    }//GEN-LAST:event_reparierenButtonActionPerformed

    /**
     * In der Steuerung wird ein Zug gelöscht. Zusätzlich wird überprüft, ob das Depot leer ist. Ist dies der Fall, wirdder Button deaktiviert.
     *
     * @param evt Neues ungenutztes Event
     */
    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        strg.zugVerschrotten();
        if (strg.getDepot() == 0) {
            minusButton.setEnabled(false);
        } else {
            minusButton.setEnabled(true);
        }
    }//GEN-LAST:event_minusButtonActionPerformed

    /**
     * Sobald in die Liste mit der Maustaste geklickt wurde, wird einem Int der ausgewählte Eintrag zugewiesen. Wenn die Liste nicht leer ist, wird eine
     * LinienGUI für die jeweilig ausgewählte Linie erstellt.
     *
     * @param evt Neues ungenutztes Event
     */
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int pos = jList1.getSelectedIndex();
        if (pos >= 0) {
            JFrame f = new LinienGUI(linien[pos], strg);
            f.setVisible(true);
        }
    }//GEN-LAST:event_jList1MouseClicked

    /**
     * Speichert die Positionen der Scrollbalken und die Größe des Viewports, damit diese beim Zoomen später verwendet weden können.
     */
    public void saveScrollValues() {
        hPos = jScrollPane3.getHorizontalScrollBar().getValue();
        vPos = jScrollPane3.getVerticalScrollBar().getValue();
        hView = jScrollPane3.getViewport().getWidth();
        vView = jScrollPane3.getViewport().getHeight();
    }

    /**
     * Zoomt rein und schiebt die Mitte wieder in die Mitte
     */
    public void zoomIn() {
        jScrollPane3.getVerticalScrollBar().setValue(0);
        jScrollPane3.getVerticalScrollBar().setValue(vPos * 2 + vView / 2);
        jScrollPane3.getHorizontalScrollBar().setValue(hPos * 2 + hView / 2);
        this.saveScrollValues();
    }

    /**
     * Zoomt raus und schiebt die Mitte wieder in die Mitte
     */
    public void zoomOut() {
        jScrollPane3.getHorizontalScrollBar().setValue(hPos / 2 - hView / 4);
        jScrollPane3.getVerticalScrollBar().setValue(vPos / 2 - vView / 4);
        this.saveScrollValues();
    }

    /**
     * Gibt die Nummer der aktuell ausgewählten Linie zurück
     *
     * @return Nummer der gewählten Linie
     */
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

    private class KeyControlls implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == KeyEvent.VK_B) {
                    strg.setNextAction("bhf");
                } else if (e.getKeyCode() == KeyEvent.VK_J) {
                    strg.langsamer();
                } else if (e.getKeyCode() == KeyEvent.VK_L) {
                    strg.schneller();
                } else if (e.getKeyCode() == KeyEvent.VK_K || e.getKeyCode() == KeyEvent.VK_P) {
                    strg.setPause(!strg.getPause());
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                    strg.geldCheat();
                } else if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == 93 || e.getKeyCode() == KeyEvent.VK_X) { // Das zweite ist für Maxis Mac und insgesamt gehts hier um Zoomen
                    strg.zoomIn();
                } else if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 47 || e.getKeyCode() == KeyEvent.VK_Y) {
                    strg.zoomOut();
                }
            }
            return false;
        }

    }
}
