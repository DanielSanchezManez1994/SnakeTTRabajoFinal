/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.Color;

/**
 *
 * @author danielsanchez
 */
public class Node {
    
    /* Position in the Board That the Node occupies*/
    
    private int row;
    private int col;
    
    public Node(int row, int col){
        
        this.row = row;
        this.col = col;
        
    }
    
    /* Getters for the Row and the Col*/
    
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
}
