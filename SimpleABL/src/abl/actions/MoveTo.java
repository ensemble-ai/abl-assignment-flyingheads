package abl.actions;

import game.Bot;
import game.GameEngine;

import java.awt.Point;

public class MoveTo extends BaseAction {

	@Override
	public void execute(Object[] args) {
		int x,y;
		for(Bot b:GameEngine.getInstance().getBots()) {
			if(b.getId() == (Integer)args[2]) {
				if ((Integer)args[0] >= 1) x = GameEngine.BotSpeed;
				else if ((Integer)args[0] <= -1) x = -GameEngine.BotSpeed;
				else x = 0;
				
				if ((Integer)args[1] >= 1) y = GameEngine.BotSpeed;
				else if ((Integer)args[1] <= -1) y = -GameEngine.BotSpeed;
				else y = 0;
				b.setTrajectory(new Point(x, y));
			}
		}
	}
}
