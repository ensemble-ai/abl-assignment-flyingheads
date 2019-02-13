package abl.actions;

import game.Bot;
import game.GameEngine;

import java.awt.Point;
/**
 * Sets the trajectory of the chaser. 
 * 
 * @author Ben Weber 3-7-11
 */
public class Move extends BaseAction {

	/**
	 * Sets the trajectory of the player to move right.
	 * args[0] - bot id
	 */
	@Override
	public void execute(Object[] args) {
		for(Bot b:GameEngine.getInstance().getBots()) {
			if(b.getId() == (Integer)args[2]) {
				b.setTrajectory(new Point((Integer)args[0],(Integer)args[1]));
			}
		}
	}
}