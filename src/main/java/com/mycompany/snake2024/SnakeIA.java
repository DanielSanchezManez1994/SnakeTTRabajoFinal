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
public class SnakeIA extends Snake {

    /* Attribute necessary for the correct work of Ia's movement*/
    
    private boolean turning;

    public SnakeIA() {

        super();

        turning = false;

        body.clear();

        for (int i = ConfigData.getInstance().getNumCols()-6; i < ConfigData.getInstance().getNumCols()-2; i++) {

            Node node = new Node((ConfigData.getInstance().getNumRows() / 2), i);
            body.add(node);

        }

        actualDirection = Direction.LEFT;

    }
    
    /* PaintComponent for the IA*/
    
    @Override
    public void paint(Graphics g, int squareWidth, int squareHeigth) {

        boolean first = true;

        Color color;

        for (Node node : body) {
            if (first) {
                color = Color.black;
                first = false;
            } else {
                color = Color.GRAY;
            }
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeigth);
        }
    }

    /* Brain of the IA*/
    
    public void goForFood(Food food, Snake snake) {

        /* Switch for every Direction case*/
        
        switch (actualDirection) {

            case DOWN:
                
                /*Case Col from Head is Smaller than Col from food and the movement to do*/
                
                if (colIsSmaller(food)) {

                    checkDirection(Direction.RIGHT, snake);
                    
                    /*Case Col from Head is Bigger than Col from food and the movement to do*/
                
                } else if (colIsBigger(food)) {

                    checkDirection(Direction.LEFT, snake);

                    /* Case Col from Head is equal to Col from food 
                    and the row from the food is bigger than the 
                    row of the head*/
                    
                } else if (colIsEqual(food) && rowIsSmaller(food) && turning == true) {

                    /* Taking the decision of where to turn.
                    Depends in the position in the table*/
                    
                    if (body.get(0).getCol() < ConfigData.getInstance().getNumCols() / 2) {

                        checkDirection(Direction.RIGHT, snake);

                    } else {

                         checkDirection(Direction.LEFT, snake);

                    }

                    /* Keeps the same direction in order to reach the food */
                    
                }else if (colIsEqual(food)) {

                     checkDirection(Direction.DOWN, snake);
                }

                turning = false;

                break;
                
                /* The rest of the cases are equal to the
                down case changing the parameters*/
                
            case LEFT:

                if (rowIsBigger(food)) {

                     checkDirection(Direction.UP, snake);

                } else if (rowIsSmaller(food)) {

                     checkDirection(Direction.DOWN, snake);

                } else if (rowIsEqual(food) && colIsSmaller(food) && turning == true) {

                    if (body.get(0).getRow() < ConfigData.getInstance().getNumRows() / 2) {

                        checkDirection(Direction.DOWN, snake);

                    } else {

                        checkDirection(Direction.UP, snake);
                    }

                } else if (rowIsEqual(food)) {

                    checkDirection(Direction.LEFT, snake);
                }

                turning = false;

                break;

            case RIGHT:

                if (rowIsBigger(food)) {

                    checkDirection(Direction.UP, snake);

                } else if (rowIsSmaller(food)) {

                    checkDirection(Direction.DOWN, snake);

                } else if (rowIsEqual(food) && colIsBigger(food) && turning == true) {

                    if (body.get(0).getRow() < ConfigData.getInstance().getNumRows() / 2) {

                        checkDirection(Direction.UP, snake);

                    } else {

                        checkDirection(Direction.DOWN, snake);

                    }

                } else if (rowIsEqual(food)) {

                    checkDirection(Direction.RIGHT, snake);
                }

                turning = false;

                break;

            case UP:

                if (colIsBigger(food)) {

                    checkDirection(Direction.LEFT, snake);

                } else if (colIsSmaller(food)) {

                    checkDirection(Direction.RIGHT, snake);

                } else if (colIsEqual(food) && rowIsBigger(food) && turning == true) {

                    if (body.get(0).getCol() < ConfigData.getInstance().getNumCols() / 2) {

                    checkDirection(Direction.RIGHT, snake);

                    } else {

                    checkDirection(Direction.LEFT, snake);

                    }

                } else if (colIsEqual(food)) {

                    checkDirection(Direction.UP, snake);
                }

                turning = false;

                break;

        }
    }

    /* Modification of the move that adds the goForFood method 
    in order to get the correct Direction*/
    
    public void move(Food food, Snake snake) {

        goForFood(food, snake);

        super.move(snake);

    }

    /* setter for the boolean turning*/
    
    public void setTurning(boolean turning) {

        this.turning = turning;

    }

    private boolean colIsSmaller(Food food) {

        if (body.get(0).getCol() < food.getCol()) {

            return true;

        } else {

            return false;
        }

    }

    private boolean colIsBigger(Food food) {

        if (body.get(0).getCol() > food.getCol()) {

            return true;

        } else {

            return false;
        }
    }

    private boolean colIsEqual(Food food) {

        if (body.get(0).getCol() == food.getCol()) {

            return true;

        } else {

            return false;
        }
    }

    private boolean rowIsSmaller(Food food) {

        if (body.get(0).getRow() < food.getRow()) {

            return true;

        } else {

            return false;
        }
    }

    private boolean rowIsBigger(Food food) {

        if (body.get(0).getRow() > food.getRow()) {

            return true;

        } else {

            return false;
        }
    }

    private boolean rowIsEqual(Food food) {

        if (body.get(0).getRow() == food.getRow()) {

            return true;

        } else {

            return false;
        }
    }

    private Direction getNewDirection(Direction direction){
        
        switch (direction) {
            case UP:
                
                return Direction.RIGHT;

            case DOWN:
                
                return Direction.LEFT;
                
            case RIGHT:
                
                return Direction.DOWN;
                
            case LEFT:
                
                return Direction.UP;
                
        }
        
        return null;
    }
    
    
    private void checkDirection(Direction direction, Snake snake){
        
        actualDirection = direction;
        
        for(int i = 0; i < 4; i++){
            
            if(canMove(snake)){
                
                break;
                
            }else{
                
                actualDirection = getNewDirection(actualDirection);
                
            }
        }
    }
    
    /* Advanved IA brain*/
    
    private void checkDirectionUpgrade(Direction direction, Snake snake){
        
    }
}
