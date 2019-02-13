package abl.actions;

import game.Bot;
import game.GameEngine;

import java.awt.Color;
import java.awt.Point;
/**
 * Fires a bullet. 
 * 
 * @author Ben Weber 3-7-11
 */
public class SetColor extends BaseAction {

	/**
	 * Fires a bullet at the target location.
	 * 
	 * Args:
	 *  - 0: source x position
	 *  - 1: source y position
	 *  - 2: target x position
	 *  - 3: target y position
	 */
	@Override
	public void execute(Object[] args) {
		System.out.println("Inside the SetColor Function");
		for(Bot b:GameEngine.getInstance().getBots()) {
			if(b.getId() == (Integer)args[2]) {
				Color c =new Color((Integer)args[0], (Integer)args[1], (Integer)args[2]);
				b.setColor(c);
			}
		}
	}
}