/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.snake2024;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author danielsanchez
 */
public class Board extends javax.swing.JPanel {

    /* Anonymous Class myKeyAdapter extending
    keyAdapter and the change in the Events */
    
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:

                    if (snake.getAcualDirection() == Direction.LEFT && !turning) {
                        tick();
                    }

                    if (snake.getAcualDirection() != Direction.RIGHT && !turning) {

                        snake.setAcualDirection(Direction.LEFT);
                        
                        turning = true;

                    }

                    break;

                case KeyEvent.VK_RIGHT:

                    if (snake.getAcualDirection() == Direction.RIGHT && !turning) {
                        tick();
                    }
                    if (snake.getAcualDirection() != Direction.LEFT && !turning) {

                        snake.setAcualDirection(Direction.RIGHT);
                        
                        turning = true;

                    }

                    break;

                case KeyEvent.VK_UP:

                    if (snake.getAcualDirection() == Direction.UP && !turning) {
                        tick();
                    }

                    if (snake.getAcualDirection() != Direction.DOWN && !turning) {

                        snake.setAcualDirection(Direction.UP);
                        
                        turning = true;

                    }

                    break;
                case KeyEvent.VK_DOWN:

                    if (snake.getAcualDirection() == Direction.DOWN && !turning) {
                        tick();
                    }
                    if (snake.getAcualDirection() != Direction.UP && !turning) {

                        snake.setAcualDirection(Direction.DOWN);

                        turning = true;
                        
                    }

                    break;
                case KeyEvent.VK_R:
                    //                 resetGame();
                    break;
                case KeyEvent.VK_P:

                    if(timer.isRunning()){
                        
                        timer.stop();
                        
                    }else{
                        
                        timer.start();
                    }
                    
                     if(iaTimer.isRunning()){
                         
                        iaTimer.stop();
                        
                    }else{
                         
                        iaTimer.start();
                        
                    }
                     
                     break;
                //                   pauseGame();
                default:
                    break;
            }

            repaint();
        }
    }

    /* Atributes from Board Class*/
    
    private ScoreInterface scoreInterface;
    
    private boolean turning;
    
    private Snake snake;
    
    private SnakeIA snakeIA;

    private Food banana;

    private SpecialFood pickleRick;

    private MyKeyAdapter keyAdapter;

    private Timer timer;
    
    private Timer iaTimer;

    private int secondsToSpawn;
    
    public static boolean gameOver;

    /* Creates new Board */
    
    public Board() {

        initComponents();
            
        gameOver = false;
        
        snakeIA = new SnakeIA();
        
        turning = false;
        
        snake = new Snake();

        keyAdapter = new MyKeyAdapter();

        addKeyListener(keyAdapter);

        secondsToSpawn = (int) ((Math.random() * 50) + 10);

        setFocusable(true);

        generateValidFood();
        
        /* Instanciates Snake's tick */
        
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer( ConfigData.getInstance().getDeltaTime(), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                tick();
                repaint();

            }
        });
        
        setBackground(Color.white);
        timer.start();

        /* Instanciates iaSnake's timer*/
        
        if(iaTimer != null && timer.isRunning()){
            timer.stop();
        }
        iaTimer = new Timer((int) (ConfigData.getInstance().getDeltaTime()*1.4), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                iaTick();
                repaint();

            }
        });
        
        iaTimer.start();
    }

    /* getter of the width of the square*/
    
    public int getSQUARE_WIDTH() {
        
        return getWidth() / ConfigData.getInstance().getNumCols();
        
    }

    /* getter of the height of the square*/
    public int getSQUARE_HEIGTH() {
        
        return getHeight() / ConfigData.getInstance().getNumRows();
        
    }

    /* PaintComponent that calls the other paint methods from the Clases */
    
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        snake.paint(g, getSQUARE_WIDTH(), getSQUARE_HEIGTH());

        banana.paint(g, getSQUARE_WIDTH(), getSQUARE_HEIGTH());
        
        snakeIA.paint(g, getSQUARE_WIDTH(), getSQUARE_HEIGTH());

        if (pickleRick != null) {

            pickleRick.paint(g, getSQUARE_WIDTH(), getSQUARE_HEIGTH());

        }

        Toolkit.getDefaultToolkit().sync();
    }

    /* Method tick that processes snakes's movement, if snake eats any food,
    the reduction of the timer of the SF, SF reset, the process of he Game Over */
    
    private void tick() {

       
        snake.move(snakeIA);
        
        processGameOver();
        
        checkSnakeHitsFood();
        
        reduceTimeForSFOrGenerateSF();

        resetSF();
        
        processGameOver();

        repaint();
        
        turning = false;
        
    }
    
    /* Method tick that processes the obejctive of the Ia,
    the process of the game over and if iAsnake hits food*/
    
    private void iaTick(){
        
         if(pickleRick != null){
            
            snakeIA.move(pickleRick,snake);
            
        }else{
             
            snakeIA.move(banana, snake);
            
        }
         
         checkIASnakeHitsFood();
    }

    /* Method that creates new food if the given random
    numbers do not belong to the snake or the snakeIa */
    
    private void generateValidFood() {

        boolean validFood = false;

        while (validFood == false) {

            int row = (int) (Math.random() * (ConfigData.getInstance().getNumRows()));
            int col = (int) (Math.random() * (ConfigData.getInstance().getNumCols()));

            if (snake.belongsToSnake(row, col,snakeIA) == false && checkSuperFood(row, col) == false && snakeIA.belongsToSnake(row, col,snakeIA) == false) {

                banana = new Food(row, col);
                
                validFood = true;
                
                snakeIA.setTurning(true);

            }
        }

    }

    /* Method that creates new SpecialFood if the given random
    numbers do not belong to the snake or the snakeIa */
    private void generateValidSuperFood() {

        boolean validFood = false;

        while (validFood == false) {

            int row = (int) (Math.random() * (ConfigData.getInstance().getNumRows()));
            int col = (int) (Math.random() * (ConfigData.getInstance().getNumCols()));

            if (snake.belongsToSnake(row, col,snakeIA) == false && checkFood(row, col) == false && snakeIA.belongsToSnake(row, col,snake) == false) {

                pickleRick = new SpecialFood(row, col);

                validFood = true;
                
                snakeIA.setTurning(true);

            }
        }
    }
    
    /* If the snake Hits any Food 
    replaces it with a new one increasing 
    the score and the nodesToGrow*/
    
    private void checkSnakeHitsFood(){
        
         if (banana.checkFood(banana,snake.getFirstNode().getRow(), snake.getFirstNode().getCol())) {

            banana = null;

            generateValidFood();

            snake.setNodesToGrow(1);

            scoreInterface.incrementScore();
        }

        if (pickleRick != null && pickleRick.checkFood(pickleRick, snake.getFirstNode().getRow(), snake.getFirstNode().getCol())) {

            pickleRick = null;

            secondsToSpawn = (int) ((Math.random() * 50) + 10);

            snake.setNodesToGrow(3);
            
            scoreInterface.incrementSpScore();
            
        }
        
    }
    
    /* If the snakeIa Hits any Food 
    replaces it with a new one increasing 
    the score and the nodesToGrow*/
    
    private void checkIASnakeHitsFood(){
        
         if (banana.checkFood(banana,snakeIA.getFirstNode().getRow(), snakeIA.getFirstNode().getCol())) {

            banana = null;

            generateValidFood();

            snakeIA.setNodesToGrow(1);
            
            scoreInterface.decrementScore();
        }

        if (pickleRick != null && pickleRick.checkFood(pickleRick, snakeIA.getFirstNode().getRow(), snakeIA.getFirstNode().getCol())) {

            pickleRick = null;

            secondsToSpawn = (int) ((Math.random() * 50) + 10);

            snakeIA.setNodesToGrow(3);
            
            scoreInterface.decrementSpScore();
        }
        
    }

    /* Method that returns true if the given row and col 
    are the same as the row and col of the SF*/
    
    private boolean checkSuperFood(int row, int col) {

        if (pickleRick == null) {
            
            return false;
        }

        if (pickleRick.getRow() == row && pickleRick.getCol() == col) {

            return true;
        }

        return false;
    }

    /* Method that returns true if the given row and col 
    are the same as the row and col of the SF */
    
    private boolean checkFood(int row, int col) {

        if (banana.getRow() == row && banana.getCol() == col) {

            return true;
        }

        return false;
    }

    /* Method that checks if Snake's head hit's 
    the food and increments the score via scoreInterface*/
    
    public boolean snakeHitsFood() {

        if (banana.getCol() == snake.getFirstNode().getCol() && banana.getRow() == snake.getFirstNode().getRow()) {

            scoreInterface.incrementScore();
            
            return true;

        } else {

            return false;

        }
    }

    /* Method that checks if Snake's head hit's 
    the food and increments the score via scoreInterface */
    
    public boolean snakeHitsSF() {

        if (pickleRick != null) {
            
            if (pickleRick.getCol() == snake.getFirstNode().getCol() && pickleRick.getRow() == snake.getFirstNode().getRow()) {

                scoreInterface.incrementSpScore();
                return true;

            } else {

                return false;

            }
        }else{
            
            return false;
            
        }

    }
    
    /* Method that reduces the timer for SF or spawns a new one */

    public void reduceTimeForSFOrGenerateSF() {

        if (pickleRick == null) {

            if (secondsToSpawn > 0) {

                secondsToSpawn--;

            } else {

                generateValidSuperFood();
            }
        }

    }
    
    /* Method that resets the SF */

    public void resetSF() {

        if (pickleRick != null) {

            if (pickleRick.getTimeAlive() <= 0) {

                pickleRick.stopTimer();

                secondsToSpawn = (int) ((Math.random() * 50) + 10);

                pickleRick = null;

            }
        }

    }

    /* Setter for the innterface */
    
    public void setScoreInterface(ScoreInterface scoreInterface) {
        this.scoreInterface = scoreInterface;
    }
    
    /* Method that processess the game over */
    
    private void processGameOver(){
        
        if(gameOver == true){
            
            iaTimer.stop();
            
            timer.stop();
            
            AboutDialog gameOver = new AboutDialog();
            
            gameOver.showMessageDialog(gameOver, "GameOver... Score: " + scoreInterface.getScore());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
