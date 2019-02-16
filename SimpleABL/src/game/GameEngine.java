package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import abl.generated.AdvancedAgent;
import game.input.*;
/**
 *  Simple "game" for showing how to interface an ABL agent.
 *
 * @author Ben Weber 3-7-11
 */
public class GameEngine extends JPanel implements KeyListener {

	/** scene dimensions */
	private Point dimensions = new Point(640, 480);

	/** location of the player character */
	private Point playerLocation = new Point((int)(dimensions.x*Math.random()), (int)(dimensions.y*Math.random()));

	/** trajectory of the player character */
	private Point playerTrajectory = new Point(0, 0);

	/** location of the chaser */
	private Point chaserLocation = new Point((int)(dimensions.x*Math.random()), (int)(dimensions.y*Math.random()));

	/** the list of bots */
	private ArrayList<Bot> bots = new ArrayList<Bot>();
	
	/** trajectory of the chaser */
	private Point chaserTrajectory = new Point(0, 0);

	/** size of the player character */
	private static final int playerSize = 10;

	/** size of the bullets */
	private static final int bulletSize = 4;

	/** speed of the player character */
	private static final int PlayerSpeed = 1;

	/** speed of the player character */
	public static final int BotSpeed = 1;

	/** keys held down */
	private boolean[] keyPresses = new boolean[256];

	/** a reference to the game */
	private static GameEngine gameEngine;

	/** did the player fire a bullet */
	private boolean spawnBullet = false;

	/** did the player spawn a new bot */
	private boolean spawnBot = false;
	
	/** did the player spawn four formation bots */
	private boolean spawnFourBots = false;
	private boolean spawnedFourBots = false; //makes it so this only happens once at most
	
	/** bullets which have been fired by both players */
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	/** spawn a chaser bullet? */
	private boolean chaserBullet = false;

	/** source position of the chaser bullet */
	private Point bulletSource;

	/** target position of the chaser bullet */
	private Point bulletTarget;
	
	public boolean angry = false;

	/** holds the input classes */
	private ArrayList<IInput> inputs = new ArrayList<IInput>();
	
	/**
	 * Starts the game.
	 */
	public static void main(String[] args) {
		gameEngine = new GameEngine();
		gameEngine.startAgent();
	}

	/**
	 * Returns a reference to the game.
	 */
	public static GameEngine getInstance() {
		return gameEngine;
	}

	/**
	 * Instantiates the game and places the instance in a JFrame.
	 */
	public GameEngine() {
		setPreferredSize(new Dimension(dimensions.x, dimensions.y));
		JFrame frame = new JFrame("ABL Chaser");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setVisible(true);

		
		this.initializeInputs();
		
		//spawn a single default bot
		Bot b = new Bot();
		b.setLocation(new Point(dimensions.x/2, dimensions.y/2));
		b.setNewb();
		bots.add(b);
		
		spawnFourBots = true;
		
		// spawn an update thread
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						repaint();
						Thread.sleep(50);
					}
					catch (Exception e) {}
				}
			}
		}.start();
	}

	/**
	 * Starts the ABL agent.
	 *
	 * Note: this method does not return, the ABL agent decision cycle claims the thread.
	 */
	public void startAgent() {
		 //ChaserAgent agent = new ChaserAgent();
		 //agent.startBehaving();
		 //StarterAgent starterAgent = new StarterAgent();
		 //starterAgent.startBehaving();
		 //IntermediateAgent intermediateAgent = new IntermediateAgent();
		 //intermediateAgent.startBehaving();
		 AdvancedAgent advancedAgent = new AdvancedAgent();
		 advancedAgent.startBehaving();
	}

	/**
	 * Updates the positions of objects, and draws the scene.
	 */
	@Override
	public void paint(Graphics g) {
		updateLocations();
		updateBullets();
		updateBots();
		updateEmotion();
	
		super.paint(g);

		g.setColor(Color.BLUE);
		g.fillRect(playerLocation.x, playerLocation.y, playerSize, playerSize);
	
		for (Bot bot : this.bots) {
			g.setColor(bot.getColor());
			g.fillRect(bot.getX(), bot.getY(), bot.getSize(), bot.getSize());
		}
		
		g.setColor(Color.BLACK);
		for (Bullet bullet : bullets) {
			g.fillRect(bullet.getX() + (playerSize - bulletSize)/2, bullet.getY() + (playerSize - bulletSize)/2, bulletSize, bulletSize);
		}
	}

	/**
	 * Updates bullet locations and spawns new bullets.
	 */
	public void updateBullets() {

		// update bullet positions
		for (Bullet bullet : bullets) {
			bullet.update();
		}

		// check for out of boundary bullets
		ArrayList<Bullet> remove = new ArrayList<Bullet>();
		for (Bullet bullet : bullets) {
			if (bullet.x < 0) {
				remove.add(bullet);
			}
			else if (bullet.x > dimensions.x) {
				remove.add(bullet);
			}
			else if (bullet.y < 0) {
				remove.add(bullet);
			}
			else if (bullet.y > dimensions.y) {
				remove.add(bullet);
			}
		}
		bullets.removeAll(remove);

		// spawn player bullets
		if (spawnBullet) {
			spawnBullet = false;

			Bullet bullet = new Bullet(playerLocation, chaserLocation);
			if (!bullet.isIdle()) {
				bullets.add(bullet);
			}
		}

		// spawn chaser bullets
		if (chaserBullet) {
			chaserBullet = false;

			Bullet bullet = new Bullet(bulletSource, bulletTarget);
			if (!bullet.isIdle()) {
				bullets.add(bullet);
			}
		}
	}

	
	public void updateEmotion() {
		if (keyPresses[KeyEvent.VK_A]) {
			angry =true ;
			
		}
		
		if (keyPresses[KeyEvent.VK_H]) {
			angry =false ;
			
		}
			
	}
	
	
	public void updateBots() {
		// spawn bot 
		if (spawnBot) {
			spawnBot = false;

			//spawn a single bot
			Bot b = new Bot();
			b.setLocation(new Point(dimensions.x/2, dimensions.y/2));
			bots.add(b);
		}
		if (spawnFourBots && !spawnedFourBots) {
			spawnFourBots = false;
			spawnedFourBots = true;
			
			//spawn four bots 
			Bot b1 = new Bot();
			b1.setLocation(new Point(playerLocation.x + 25, playerLocation.y + 25));
			b1.setfBot();
			
			Bot b2 = new Bot();
			b2.setLocation(new Point(playerLocation.x + 25, playerLocation.y - 25));
			b2.setfBot();
			
			Bot b3 = new Bot();
			b3.setLocation(new Point(playerLocation.x - 25, playerLocation.y + 25));
			b3.setfBot();
			
			Bot b4 = new Bot();
			b4.setLocation(new Point(playerLocation.x - 25, playerLocation.y - 25));
			b4.setfBot();
			
			b1.setFormation(new Point(25,25));
			b2.setFormation(new Point(25,-25));
			b3.setFormation(new Point(-25,25));
			b4.setFormation(new Point(-25,-25));
			
			bots.add(b1);
			bots.add(b2);
			bots.add(b3);
			bots.add(b4);
		}
	}
	
	/**
	 * Updates the positions of objects in the scene based on their trajectories and the dimensions of the scene.
	 */
	public void updateLocations() {

		// compute the player trajectory
		int dx = 0;
		int dy = 0;

		if (keyPresses[KeyEvent.VK_LEFT]) {
			dx -= PlayerSpeed;
		}
		if (keyPresses[KeyEvent.VK_RIGHT]) {
			dx += PlayerSpeed;
		}
		if (keyPresses[KeyEvent.VK_UP]) {
			dy -= PlayerSpeed;
		}
		if (keyPresses[KeyEvent.VK_DOWN]) {
			dy += PlayerSpeed;
		}

		playerTrajectory = new Point(dx, dy);

		// update player location
		int playerX = playerLocation.x + playerTrajectory.x;
		playerX = Math.max(0, playerX);
		playerX = Math.min(dimensions.x, playerX);

		int playerY = playerLocation.y + playerTrajectory.y;
		playerY = Math.max(0, playerY);
		playerY = Math.min(dimensions.y, playerY);

		playerLocation = new Point(playerX, playerY);

		// update bot locations
		for(Bot b : this.bots) {
			int botX = b.getLocation().x + b.getTrajectory().x;
			botX = Math.max(0, botX);
			botX = Math.min(dimensions.x, botX);

			int botY = b.getLocation().y + b.getTrajectory().y;
			botY = Math.max(0, botY);
			botY = Math.min(dimensions.y, botY);

			b.setLocation(new Point(botX, botY));
		}
		
		
	}

	/**
	 * Sets the trajectory of the chaser object.
	 *
	 * Note: This is invoked via ABL physical acts
	 */
	public void setChaserTrajectory(Point trajectory) {
		chaserTrajectory = trajectory;
	}

	/**
	 * Fires a bullet from the chaser at the player.
	 */
	public void fireBullet(Point source, Point target) {
		bulletSource = source;
		bulletTarget = target;
		chaserBullet = true;
	}

	/**
	 * Returns the location of the player.
	 */
	public Point getPlayerLocation() {
		return playerLocation;
	}

	public boolean getPlayerEmotion() {
		return angry;
	}
	
	/**
	 * Returns the trajectory of the player.
	 */
	public Point getPlayerTrajectory() {
		return playerTrajectory;
	}

	/**
	 * Returns the location of the chaser.
	 */
	public Point getChaserLocation() {
		return chaserLocation;
	}

	/**
	 * Returns the trajectory of the chaser.
	 */
	public Point getChaserTrajectory() {
		return chaserTrajectory;
	}
	
	public ArrayList<Bot> getBots() {
		return bots;
	}
	
	/**
	 * Initializes input handlers.
	 */
	public void initializeInputs() {
		this.inputs.add(new Exit());
	}


	/**
	 * Records keystate.
	 *
	 * Note: tracks presses and releases with a boolean value to avoid duplicate key presses.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		

		if (e.getKeyCode() == KeyEvent.VK_SPACE && keyPresses[KeyEvent.VK_SPACE] == false) {
			spawnBullet = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_B && keyPresses[KeyEvent.VK_B] == false) {
			spawnBot = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_F && keyPresses[KeyEvent.VK_F] == false) {
			//spawnFourBots = true;
		}
		
		if (e.getKeyCode() < keyPresses.length) {
			keyPresses[e.getKeyCode()] = true;
		}
		
		for(IInput handler : this.inputs) {
			handler.process(gameEngine, e.getKeyCode());
		}
	}

	/**
	 * Release key state.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < keyPresses.length) {
			keyPresses[e.getKeyCode()] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
