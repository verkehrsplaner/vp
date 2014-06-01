package de.vp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felix
 */
public class LinienCellRenderer extends JLabel implements ListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        Linie linie = (Linie) value;
        
        this.setText(linie.getName());
        Font font = this.getFont();
        Font newFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        this.setFont(newFont);
        this.setOpaque(true);
        
        if (isSelected) {
            // Schriftfarbe, wenn ausgewählt
            this.setForeground(Color.BLACK);
            // Hintergrund, wenn ausgewählt
            this.setBackground(linie.getFarbe());
        } else {
            // Schriftfarbe
            this.setForeground(linie.getFarbe());
            // Hintergrund
            this.setBackground(Color.WHITE);
        }
        
        return this;
    }
    
}