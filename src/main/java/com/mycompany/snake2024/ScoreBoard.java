/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.color.ColorSpace;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 *
 * @author danielsanchez
 */
public class ScoreBoard extends javax.swing.JPanel implements ScoreInterface {

    /* JLabels for the ScoreBoard */
   private JLabel scoreLabel;
   private JLabel textLabel;

   /* Create method for ScoreBoard */
   
    public ScoreBoard() {

        setLayout(new FlowLayout());

        setBackground(Color.MAGENTA);

        scoreLabel = new JLabel();
        scoreLabel.setText("0");

        textLabel = new JLabel();
        textLabel.setText("Score: ");

        Font f = new Font("serif", Font.PLAIN, 50);

        scoreLabel.setFont(f);
        textLabel.setFont(f);

        add(textLabel);
        add(scoreLabel);

    }

    /* Method that increments 2 Points of the score */
    
    public void incrementScore() {
        
        scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) + 2));
        
    }
    
    /* Method that increments 6 Points of the score */
    
    public void incrementSpScore() {
        
        scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) + 6));
        
    }
    
    /* Method that decrements 1 Point of the score */
    
    public void decrementScore(){
        
        scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) - 1));
        
    }
    
    /* Method that decrements 3 Points of the score */
    
    public void decrementSpScore(){
        
        scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) - 3));
        
    }
    
   /* Method that returns the Score as a String*/
    
    public String getScore(){
        
        return scoreLabel.getText();
        
    }
}
