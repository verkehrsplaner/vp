/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.vp;

/**
 *
 * @author Hecker
 */
public class EinstellungsGUI extends javax.swing.JFrame {

    /**
     * Creates new form EinstellungsGUI
     */
    public EinstellungsGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hMusik = new javax.swing.JCheckBox();
        soundeffekte = new javax.swing.JCheckBox();
        Menü = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        spielBeenden = new javax.swing.JButton();
        spielSpeichern = new javax.swing.JButton();
        spielSpeichern1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());

        hMusik.setText("Hintergrundmusik");

        soundeffekte.setText("Soundeffekte");
        soundeffekte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soundeffekteActionPerformed(evt);
            }
        });

        Menü.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Menü.setText("Menü");

        spielBeenden.setText("Spiel beenden");
        spielBeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spielBeendenActionPerformed(evt);
            }
        });

        spielSpeichern.setText("Spiel speichern");

        spielSpeichern1.setText("Spiel pausieren");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(Menü))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spielSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spielBeenden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spielSpeichern1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(166, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(soundeffekte)
                        .addComponent(hMusik)))
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Menü)
                .addGap(33, 33, 33)
                .addComponent(hMusik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundeffekte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spielSpeichern1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spielSpeichern)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spielBeenden)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void soundeffekteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundeffekteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soundeffekteActionPerformed

    private void spielBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spielBeendenActionPerformed
        System.exit(0);
    }//GEN-LAST:event_spielBeendenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Menü;
    private javax.swing.JCheckBox hMusik;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox soundeffekte;
    private javax.swing.JButton spielBeenden;
    private javax.swing.JButton spielSpeichern;
    private javax.swing.JButton spielSpeichern1;
    // End of variables declaration//GEN-END:variables
}
