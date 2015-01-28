package org.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {

	private int x = Game.WIDTH / 2;
	private int y = Game.HEIGHT / 2;
	private int fallSpeed = 2;
	
	public void tick() {
		y += fallSpeed;
	}
	
	public void paint (Graphics g){
		g.setColor(Color.red);
		g.fillRect(x, y, 20, 20);
	} 
	
	public void setFallSpeed(int f) {
		this.fallSpeed = f;
	}
	
	public Rectangle getPlayerBounds() {
		return new Rectangle(x, y, 20, 20);
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x += x;
	}
}
