package abl.wmes;

import java.awt.Color;
import java.awt.Point;

import wm.WME;
/**
 * Stores information about the bot.
 * 
 * @author Ben Weber 3-7-11
 * @author Josh McCoy 1-23-2019
 */
public class BotWME extends WME {

	/** Location of the bot */
	private Point location;
	
	/** Trajectory of the bot */
	private Point trajectory;
	
	/** ID of the bot */
	private int id;
	
	private Color color;
	
	/** True if bot is new **/
	private int newb;
	
	/** True if a formation bot (for intermediate task) **/
	private int fBot;
	
	/** Formation if fBot **/
	private Point formation;
	
	/**
	 * Instantiates a working memory element for tracking a bot.
	 */
	public BotWME(Point location, Point trajectory, int id, Color color, int newb, int fBot, Point formation) {
		this.location = location;
		this.trajectory = trajectory;
		this.id = id;
		this.color = color;
		this.newb = newb;
		this.fBot = fBot;
		this.formation = formation;
	}
	
	/**
	 * Returns the x location of the bot. 
	 */
	public int getLocationX() {
		return location.x;
	}
	
	/**
	 * Returns the y location of the bot. 
	 */
	public int getLocationY() {
		return location.y;
	}
	
	/**
	 * Returns the x direction of the bot. 
	 */
	public int getTrajectoryX() {
		return trajectory.x;
	}
	
	/**
	 * Returns the y direction of the bot. 
	 */
	public int getTrajectoryY() {
		return trajectory.y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getNewb() {
		return newb;
	}
	
	public int getFBot() {
		return fBot;
	}
	
	public int getFormationX() {
		return formation.x;
	}
	
	public int getFormationY() {
		return formation.y;
	}
}
