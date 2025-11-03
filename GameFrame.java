package Übungen.Flappy_Bird;

import javax.swing.*;

public class GameFrame extends JFrame {
	
	GameFrame(){
		this.add(new GamePanel());
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
