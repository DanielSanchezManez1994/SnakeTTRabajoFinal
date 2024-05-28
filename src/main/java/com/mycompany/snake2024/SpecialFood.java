/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author danielsanchez
 */
public class SpecialFood extends Food {
    
    private int timeAlive;
    
    private Timer despawnTimer;
    
    /* Create Method that starts inner Timer*/
    
    public SpecialFood(int row, int col) {
        
        super(row, col);
        
        if (despawnTimer != null && despawnTimer.isRunning()) {
            despawnTimer.stop();
        }
        
        despawnTimer = new Timer(ConfigData.getInstance().getDeltaTime() * 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
        timeAlive = (int) ((Math.random() * 20) + 10);
        
        despawnTimer.start();
        
    }

    /* Getter for the time that food will be up */
    
    public int getTimeAlive() {
        
        return timeAlive;
    }
    
    
    @Override
    
    /* PaintComponent */
    
    public void paint(Graphics g, int squareWidth, int squareHeigth) {

        Color color;

        color = Color.green;

        Util.drawSquare(g, this.getRow(), this.getCol(), color, squareWidth, squareHeigth);
    }
    
    /*Method called by the Timer, reduces timeAlive*/
    
    private void tick(){
        
        if(timeAlive > 0){
            
            timeAlive--;
            
        }
    }
    
    /* Stops the Timer*/
    
    public void stopTimer(){
        
        despawnTimer.stop();
        
    }
}
