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
public class Food extends Node {

    public Food(int row, int col) {

        super(row, col);

    }
    
    /* PaintComponent*/
    
    public void paint(Graphics g, int squareWidth, int squareHeigth) {

        Color color;

        color = Color.yellow;

        Util.drawSquare(g, this.getRow(), this.getCol(), color, squareWidth, squareHeigth);
        
    }
    
    /* Given the food and a row and a col compares them and returns a boolean*/
    
     public boolean checkFood(Food food, int row, int col) {

        if (food.getRow() == row && food.getCol() == col) {

            return true;
        }

        return false;
    }

}
