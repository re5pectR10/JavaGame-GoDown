package org.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Obstacle {

	private int y = Game.HEIGHT;
	private int obstacleHeight = 40;
	private int minHoleSize = 70;
	private int holeStart;
	private int holeSize;
	private int speed = 2;
	
	public Obstacle() {
		generateObstacle();
	}
	
	public Obstacle(int y) {
		generateObstacle();
		this.y = y;
	}
	
	private void generateObstacle() {
		Random r = new Random();
		this.holeSize = r.nextInt(50) + minHoleSize;
		this.holeStart = r.nextInt(Game.WIDTH - holeSize);
	}
	
	public void tick() {
		y -= speed;
	}
	
	public void updateSpeed(int speed) {
		this.speed = speed;
	}
	
	public void paint (Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, y, holeStart, obstacleHeight);
		g.fillRect(holeStart + holeSize, y, Game.WIDTH - (holeSize + holeStart), obstacleHeight);
	} 
	
	public int getY() {
		return this.y;
	}
	
	public Rectangle[] getBounds() {
		return new Rectangle[] {new Rectangle(0, y, holeStart, obstacleHeight),
				new Rectangle(holeStart + holeSize, y, Game.WIDTH - (holeSize + holeStart), obstacleHeight)};
	}
}
