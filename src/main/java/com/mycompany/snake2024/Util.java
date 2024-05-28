/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author danielsanchez
 */
public class Util {
    
    /*Util class that draws a Square*/
    
     public static void drawSquare(Graphics g, int row, int col, Color color,
                                    int squareWidth, int squareHeight) {
         
        int x = col * squareWidth;
        int y = row * squareHeight;

        g.setColor(color);
        
        g.fillRect(x + 1, y + 1, squareWidth - 2, squareHeight - 2);
  
        g.setColor(color.brighter());
        
        g.drawLine(x, y + squareHeight -1, x, y);
        g.drawLine(x, y, x + squareWidth -1, y);
        g.drawLine(x + squareWidth -1, y, x + squareWidth - 1, y + squareHeight - 1);
        g.drawLine(x + squareWidth -1, y + squareHeight - 1, x, y + squareHeight - 1);
    }
    
}
