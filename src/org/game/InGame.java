package org.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InGame extends JPanel implements KeyListener{
	
	private ArrayList<Obstacle> obstacles;
	private int obstacleCount = 4;
	private double speed = 2;
	private Player player;
	private double playerFallSpeed = 2;
	private boolean intersect = false;
	private int direction = 0;
	private double playerSpeed = 3;
	private Obstacle intersectedObastacel;
	private Obstacle forRemove;
	
	public InGame() {
		obstacles = new ArrayList<Obstacle>();
		player = new Player();
		this.addKeyListener(this);
		this.setFocusable(true);
		for (int i = 0; i < obstacleCount; i++) {
			obstacles.add(new Obstacle(Game.HEIGHT / obstacleCount * i));
		}
	}
	
	public void tick() {
		forRemove = null;
		intersect = false;
		for (Obstacle o : obstacles) {
			o.updateSpeed((int)speed);
			speed += 0.0008;
			o.tick();
			
			// check if has any obstacle for remove
			if (o.getY() < 0) {
				forRemove = o;
			}
			
			// check if the player is on the ground(obstacle)
			for (Rectangle r : o.getBounds()) {
				if (r.intersects(player.getPlayerBounds())) {
					if (o.getY() >= player.getY()) {
						intersect = true;
						intersectedObastacel = o;
					}
				}
			}
		}
		
		// remove the obstacle if have any and add new 
		if (forRemove != null) {
			obstacles.remove(forRemove);
			obstacles.add(new Obstacle());
		}
		
		// if the player is on the ground, he moves up with it, else he is falling down
		if (intersect) {
			player.setFallSpeed(0);
			player.setY(intersectedObastacel.getY() - 20);
			playerFallSpeed = 2;
		} else {
			player.setFallSpeed((int)playerFallSpeed);
			playerFallSpeed += 0.5;
		}
		
		playerSpeed += 0.005;
		// set the movement of the player
		player.setX(direction);
		player.tick();
		
		// if player is out of the field(down), he stands on the bottom and wait for the next obstacle
		if (player.getY() > Game.HEIGHT - 20) {
			player.setY(Game.HEIGHT - 20);
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		player.paint(g);
		for (Obstacle o : obstacles) {
			o.paint(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			direction = (int) (playerSpeed * -1);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			direction = (int) playerSpeed;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		direction = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
