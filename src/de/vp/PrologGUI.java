/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Felix, Nicolai, Christine, Maxi
 *
 */
public class PrologGUI extends javax.swing.JFrame {

    private int hoehe;
    private int breite;


    private String[] text = {
        /*1*/"<html><center><p>Hallo und Herzlich Willkommen!</p><p> Ich freue mich sehr Sie kennen zu lernen!</p></center></html>",
        /*2*/ "<html><center><p>Mein Name ist Horst Bichler und ich bin Bürgermeister dieser bescheidenen Kommune...</p><p>Seit meiner Wahl bewegt sich hier einiges,und neue Industrie- und Wohngebiete entstehen.</p></center></html>",
        /*3*/ "<html><center><p>Um dem immer dichteren Verkehrsaufkommen in der Stadt eine Alternative zu bieten, hat sich der Gemeinderat dazu entschlossen eine Verkehrsgesellschaft zu gründen und diese mit den Bau eines U-Bahnnetzes zu beauftragen!</p></center></html>",
        /*4*/ "<html><center><p>Sie haben sich bei unserem Wettbewerb um die leitende Stelle in der Verkehrsgesellschaft durchgesetzt! Ich denke,damit haben wir die richtige Entscheidung getroffen! Ich denke wir werden gut miteinander zurecht kommen.</p></center></html>",
        /*5*/ "<html><center><p>Die Anforderungen an Ihr Unternehmen sind einfach zu erklären: Ziel soll es sein, möglichst schnell alle Stadtteile miteinander zu vernetzen! Jeder unangebundene Stadtteil wir mit einer kleinen Strafe auf sich aufmerksam machen!</p></center></html>",
        /*6*/ "<html><center><p>In der rechten Seitenleiste finden sie alles, um ihr Unternehmen zu leiten und auszubauen:</p></center></html>",
        /*7*/ "<html><center><p>Einen neuen Bahnhof bauen sie, indem Sie hier drücken und danach auf den gewünschten Platz im Spielfeld klicken.</p></center></html>",
        /*8*/ "<html><center><p>Eine neue Linie wird mit diesem Knopf erstellt!</p></center></html>",
        /*9*/ "<html><center><p>Hier können Sie neue Züge für Ihre Linien kaufen bzw. verkaufen.</p><p>Diese Züge stehen dann in einem Depot bereit, sind jedoch noch keiner Linie zugewiesen.</p></center></html>",
        /*10*/ "<html><center><p>Haben sie eine neue Linie erstellt können sie diese in der Seitenleiste aufrufen!</p><p>Hier können sie einen Bahnhof und so viele Züge wie sie haben der Linie zu weisen. Außerdem sind hier alle Informationen zur Linie gesammelt.</p></center></html>",
        /*11*/ "<html><center><p>Klickt man auf einen Bahnhof, öffnet sich ebenfalls ein Fenster mit allen dort nötigen Informationen.</p></center></html>",
        /*12*/ "<html><center><p>Die Zahl neben dem Bahnhof ist die Zahl der Personen die dort auf einen Zug warten. Ist die Zahl sehr hoch, sollten sie über eine bessere U-Bahn-Anbindung nachdenken; Ist die Zahl bei 0 ist alles im grünen Bereich.</p></center></html>",
        /*13*/ "<html><center><p>Der News-Ticker am oberen Rand informiert Sie immer über alles was passiert. Ihn zu lesen schadet nicht.</p></center></html>",
        /*14*/ "<html><center><p>Ich denke damit wäre alles gesagt. Stoßen wir auf eine erfolgreiche Zukunft an! Aber vergessen sie nicht ihre Pflichten!</p></center></html>"}; /*"<html><p></p></html>"*/

    private Image[] bild;
    private int nummer;

    /**
     * Creates new form PrologGUI
     */
    public PrologGUI(int h, int b) {
        try {

            this.bild = new Image[]{
                /*1*/ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*2*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*3*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*4*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),
                /*5*/ ImageIO.read(this.getClass().getResource("images/linien.png")),
                /*6*/ ImageIO.read(this.getClass().getResource("images/anleitung1.png")),
                /*7*/ ImageIO.read(this.getClass().getResource("images/anleitung2.png")),
                /*8*/ ImageIO.read(this.getClass().getResource("images/anleitung3.png")),
                /*9*/ ImageIO.read(this.getClass().getResource("images/anleitung4.png")),
                /*10*/ ImageIO.read(this.getClass().getResource("images/anleitung5.png")),
                /*11*/ ImageIO.read(this.getClass().getResource("images/anleitung6.png")),
                /*12*/ ImageIO.read(this.getClass().getResource("images/anleitung7.png")),
                /*13*/ ImageIO.read(this.getClass().getResource("images/anleitung8.png")),
                /*14*/ ImageIO.read(this.getClass().getResource("images/mayor.png")),};

        } catch (IOException ex) {
            System.err.println("Prolog konnte nicht geladen werden!");
        }
        breite = b;
        hoehe = h;
        nummer = 0;
        Font myFont = new Font("Comic Sans MS", Font.BOLD, 15);
        initComponents();
        monolog.setText(text[nummer]);
        monolog.setFont(myFont);
        monolog.setHorizontalAlignment(monolog.CENTER);

        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vor = new javax.swing.JButton();
        zurueck = new javax.swing.JButton();
        skip = new javax.swing.JButton();
        hintergrund = new BildPanel();
        monolog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prolog");
        setMaximumSize(new java.awt.Dimension(800, 560));
        setMinimumSize(new java.awt.Dimension(800, 560));
        setPreferredSize(new java.awt.Dimension(800, 560));
        setResizable(false);

        vor.setText(">");
        vor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vorActionPerformed(evt);
            }
        });

        zurueck.setText("<");
        zurueck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zurueckActionPerformed(evt);
            }
        });

        skip.setText("Überspringen");
        skip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipActionPerformed(evt);
            }
        });

        hintergrund.setOpaque(false);
        hintergrund.setPreferredSize(new java.awt.Dimension(512, 288));

        javax.swing.GroupLayout hintergrundLayout = new javax.swing.GroupLayout(hintergrund);
        hintergrund.setLayout(hintergrundLayout);
        hintergrundLayout.setHorizontalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        hintergrundLayout.setVerticalGroup(
            hintergrundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        monolog.setText("jLabel1");
        monolog.setMaximumSize(new java.awt.Dimension(512, 200));
        monolog.setMinimumSize(new java.awt.Dimension(512, 200));
        monolog.setPreferredSize(new java.awt.Dimension(512, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(monolog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(zurueck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vor)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(skip))))
            .addComponent(hintergrund, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(skip, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hintergrund, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(monolog, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vor)
                    .addComponent(zurueck))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vorActionPerformed
        nummer++;
        if (nummer < text.length) {
            monolog.setText(text[nummer]);
            hintergrund.repaint();
            zurueck.setEnabled(true);
        } else {
            JFrame f = new SpielGUI(hoehe, breite);
            f.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_vorActionPerformed

    private void skipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipActionPerformed
        JFrame f = new SpielGUI(hoehe, breite);
        f.setVisible(true);
        dispose();
    }//GEN-LAST:event_skipActionPerformed

    private void zurueckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zurueckActionPerformed
        if (nummer - 1 >= 0) {
            nummer--;
            monolog.setText(text[nummer]);
            hintergrund.repaint();
        }

    }//GEN-LAST:event_zurueckActionPerformed

    private class BildPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // g.drawImage(bild[nummer], 0, 0, null);
            g.drawImage(bild[nummer], ((this.getWidth() - bild[nummer].getWidth(null)) / 2), 0, null);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel hintergrund;
    private javax.swing.JLabel monolog;
    private javax.swing.JButton skip;
    private javax.swing.JButton vor;
    private javax.swing.JButton zurueck;
    // End of variables declaration//GEN-END:variables
}
