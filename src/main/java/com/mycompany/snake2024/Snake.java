/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

/**
 *
 * @author danielsanchez
 */
public class Snake {

    /* Atributes for the Snake */
    List<Node> body;
    Direction actualDirection;
    private int nodesToGrow;

    /* Create Method for Snake */
    public Snake() {

        body = new ArrayList<Node>();
        for (int i = 0; i < 4; i++) {

            Node node = new Node((ConfigData.getInstance().getNumRows() / 2), (4 - i));
            body.add(node);

        }

        actualDirection = Direction.RIGHT;

        nodesToGrow = 0;
    }

    /* PaintComponent for the Snake */
    
    public void paint(Graphics g, int squareWidth, int squareHeigth) {

        boolean first = true;

        Color color;

        for (Node node : body) {
            if (first) {
                color = Color.red;
                first = false;
            } else {
                color = Color.CYAN;
            }
            Util.drawSquare(g, node.getRow(), node.getCol(), color, squareWidth, squareHeigth);
        }
    }

    /* Getters and Setters */
    public List<Node> getBody() {
        return body;
    }

    public Direction getAcualDirection() {
        return actualDirection;
    }

    public void setAcualDirection(Direction acualDirection) {
        this.actualDirection = acualDirection;
    }

    /* Adds a node into the body of the Snake*/
    public void addNode() {

        int row = body.get(0).getRow();
        int col = body.get(0).getCol();

        switch (actualDirection) {

            case UP:

                row = row - 1;

                break;
            case DOWN:

                row = row + 1;

                break;
            case LEFT:

                col = col - 1;

                break;
            case RIGHT:

                col = col + 1;

                break;
        }

        Node node = new Node(row, col);

        body.add(0, node);

    }

    /* Removes the Last node if the Snake
        it's not Growing*/
    public void removeLastNode() {

        if (nodesToGrow == 0) {

            body.remove(body.size() - 1);
        }

    }

    /* Method that returns true in case that 
        movement is possible using the ActualDirection */
    public boolean canMove(Snake snake) {

        int row = body.get(0).getRow();
        int col = body.get(0).getCol();
        int maxNumRows = ConfigData.getInstance().getNumRows();
        int maxNumCols = ConfigData.getInstance().getNumCols();

        switch (actualDirection) {

            case UP:

                if (row - 1 < 0) {

                    return false;
                }

                if (belongsToSnake(row - 1, col, snake)) {

                    return false;
                }

                if (snake.belongsToSnake(row - 1, col, snake)) {

                    return false;
                }

                return true;

            case DOWN:

                if (row + 1 >= maxNumRows) {

                    return false;
                }

                if (belongsToSnake(row + 1, col, snake)) {

                    return false;
                }

                if (snake.belongsToSnake(row + 1, col, snake)) {

                    return false;
                }

                return true;

            case LEFT:

                if (col - 1 < 0) {

                    return false;

                }

                if (belongsToSnake(row, col - 1, snake)) {

                    return false;
                }

                if (snake.belongsToSnake(row, col - 1, snake)) {

                    return false;
                }

                return true;

            case RIGHT:

                if (col + 1 >= maxNumCols) {

                    return false;

                }

                if (belongsToSnake(row, col + 1, snake)) {

                    return false;
                }

                if (snake.belongsToSnake(row, col + 1, snake)) {

                    return false;
                }

                return true;

        }

        return false;
    }

    /* Method that compares a given row and col with the body of a given snake*/
    
    public boolean belongsToSnake(int row, int col, Snake snake) {

        for (Node n : body) {

            if (snake.getBody().get(snake.getBody().size() - 1).getRow() == row && snake.getBody().get(snake.getBody().size() - 1).getCol() == col) {
                return true;
            }

            if (n.getRow() == body.get(body.size() - 1).getRow() && n.getCol() == body.get(body.size() - 1).getCol()) {

                if (n.getRow() == row && n.getCol() == col && nodesToGrow <= 0) {

                    return false;

                }

            }

            if (n.getRow() == row && n.getCol() == col) {

                return true;

            }
        }

        return false;

    }

    /* More Getters and Setters*/
    
    public Node getFirstNode() {

        return body.get(0);

    }

    public void setNodesToGrow(int nodesToGrow) {

        this.nodesToGrow = nodesToGrow;

    }

    public int getNodesToGrow() {
        return nodesToGrow;
    }

    /* Method With the logic for 
    the growth and the movement of the snake and the Game Over*/
    
    public void move(Snake snake) {

        if (canMove(snake)) {

            addNode();

            if (nodesToGrow > 0) {

                nodesToGrow = nodesToGrow - 1;

            } else {

                removeLastNode();

            }

        } else {

            Board.gameOver = true;
        }

    }

}
