/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vp;

import java.nio.file.Path;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxi
 */
public class Start extends javax.swing.JFrame {

    /**
     * Creates new form Start
     */
    public Start() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("images/icon.png"));
        setIconImage(icon.getImage());
        getRootPane().setDefaultButton(jButton1); // Wenn man jetzt Enter drückt gelangt man in den Prolog
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        hintergrund = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Verkehrsplaner");
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1094, 619));
        jPanel1.setLayout(null);

        jButton1.setText("Spiel Starten");
        jButton1.setToolTipText("Startet das Spiel!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(170, 140, 110, 29);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Höhe:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(152, 115, 50, 16);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "500", "1000" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(190, 110, 90, 27);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "500", "1000" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(330, 110, 88, 27);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Breite:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(287, 115, 50, 16);

        jButton2.setText("Credits");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(480, 250, 90, 29);

        jButton3.setText("Spiel laden");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(300, 140, 100, 29);

        hintergrund.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/vp/images/Startbild.png"))); // NOI18N
        hintergrund.setToolTipText("");
        jPanel1.add(hintergrund);
        hintergrund.setBounds(0, 0, 590, 290);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(584, 305));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Path file = fc.getSelectedFile().toPath();
            JFrame f = new SpielGUI(file);
            f.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFrame f = new CreditsGUI();
        f.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int hoehe = Integer.parseInt((String) jComboBox1.getModel().getElementAt(jComboBox1.getSelectedIndex())); //Setzt hoehe = ComboBox
        int breite = Integer.parseInt((String) jComboBox2.getModel().getElementAt(jComboBox2.getSelectedIndex()));
        try {
            if (hoehe < 50 && breite < 50) {
                System.out.println("Zahl kleiner als 50!");
                JOptionPane.showMessageDialog(null, "Das Spielfeld muss mindestens 50x50 groß sein!", "Fehler", JOptionPane.ERROR_MESSAGE);
            } else {
                JFrame f = new PrologGUI(hoehe, breite);
                f.setVisible(true);
                dispose();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Keine Zahl!");
            JOptionPane.showMessageDialog(null, "Bitte eine gültige Zahl eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hintergrund;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
