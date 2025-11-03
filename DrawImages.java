package Übungen.Flappy_Bird;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DrawImages extends Rectangle {

	Image image;
	int y_pos;
	int x_pos;
	int speed = 5;

	DrawImages(int x, int y, int width, int height, Image image) {
		super(x, y, width, height);
		this.image = image;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}

	public void move() {
		y += y_pos;
		x += x_pos;
	}
	

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) 
			set_y(-speed);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			set_y(speed);
	}

	public void set_y(int y) {
		y_pos = y;
	}
	
	public void set_x(int x) {
		x_pos = x;
	}

}
