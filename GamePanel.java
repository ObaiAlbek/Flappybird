package Übungen.Flappy_Bird;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    
    final static int WIDTH_WINDOW = 600;
    final static int HEIGH_WINDOW = 800;
    Dimension fensterSize = new Dimension(WIDTH_WINDOW, HEIGH_WINDOW);
    
    DrawImages world;
    DrawImages flappy;
    DrawImages ground1,ground2;
    DrawImages bottompipe,toppipe;
    
    Thread game;
    
    boolean gameOver;
    
    GamePanel() {
        drawImage();  
        this.setFocusable(true);
        this.setPreferredSize(fensterSize);
        this.addKeyListener(new Steuern());
        this.gameOver = false; 
        game = new Thread(this);
        game.start();
    }
    
    public void drawImage() {
        world = new DrawImages(0, 0, WIDTH_WINDOW, HEIGH_WINDOW, new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\world.png").getImage());
        flappy = new DrawImages(50, HEIGH_WINDOW / 2, 50, 50, new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\flappy Bird.png").getImage());
       
        
        toppipe = new DrawImages(WIDTH_WINDOW/2,0,40,HEIGH_WINDOW - 300,new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\toppipe.png").getImage());
        bottompipe = new DrawImages(WIDTH_WINDOW/2,HEIGH_WINDOW - 200,40,300,new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\bottompipe.png").getImage());
        
        // Zwei Bodenbilder nebeneinander zeichnen
        ground1 = new DrawImages(0, HEIGH_WINDOW - 100, WIDTH_WINDOW, 200, new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\ground.png").getImage());
        ground2 = new DrawImages(WIDTH_WINDOW, HEIGH_WINDOW - 100, WIDTH_WINDOW, 200, new ImageIcon("C:\\Users\\obaya\\git\\Programmierung2\\Programmierung2\\src\\Übungen\\Flappy_Bird\\ground.png").getImage());
    }
    
    public void paint(Graphics g) {
        super.paint(g); 
        world.draw(g);
        flappy.draw(g);
        toppipe.draw(g);
        bottompipe.draw(g);
        ground1.draw(g); 
        ground2.draw(g); 
     }
    
    public void move() {
        flappy.move();
        move_ground(); 
        move_pips();
    }
    
    public void move_ground() {
        ground1.x -= 3;  
        ground2.x -=3;
        
        // Sobald das erste Bodenbild komplett aus dem Bildschirm verschwindet
        if (ground1.x + ground1.width <= 0) {
            ground1.x = ground2.x + ground2.width ;  // Setze es rechts vom zweiten Bild
        }
        
        // Sobald das zweite Bodenbild komplett aus dem Bildschirm verschwindet
        if (ground2.x + ground2.width <= 0) {
            ground2.x = ground1.x + ground1.width;  // Setze es rechts vom ersten Bild
        }
    }
    
    public void move_pips() {
    	toppipe.x -= 1;
    	bottompipe.x -= 1;
    }
    
    public void checkKollision() {
    	if(flappy.intersects(ground1))
    		gameOver = true;
    	
    	if (flappy.intersects(toppipe)) 
    		gameOver = true;
    	
    		
    }
    
    

    @Override
    public void run() {
        while (!gameOver) {
            try {
                move();
                checkKollision();
                repaint();
                Thread.sleep(10);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private class Steuern extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            flappy.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            flappy.keyReleased(e);
        }
    }
}

