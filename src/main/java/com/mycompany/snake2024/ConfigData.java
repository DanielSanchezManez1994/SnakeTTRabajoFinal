/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.snake2024;

/**
 *
 * @author danielsanchez
 */
public class ConfigData {

    /* Attributes of the Singletone */
    private static ConfigData instance;
    
    private final int NUM_ROWS = 30;
    private final int NUM_COLS = 60;

    public int delta_time;
    
    private final int SQUARE_WIDTH = 25;
    private final int SQUARE_HEIGHT = 25;

    private ConfigData() {

        delta_time = 100;
        
    }
    
    /* Getters and Setters */
    
    public static ConfigData getInstance() {
        if (instance == null) {
            instance = new ConfigData();
        }

        return instance;
    }

    public int getNumRows() {

        return NUM_ROWS;
    }

    public int getNumCols() {
        return NUM_COLS;
    }

    public int getDeltaTime() {
        return delta_time;
    }

    public int getSQUARE_WIDTH() {
        return SQUARE_WIDTH;
    }

    public int getSQUARE_HEIGHT() {
        return SQUARE_HEIGHT;
    }  
}
 