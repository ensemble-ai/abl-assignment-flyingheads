package abl.actions;

import game.GameEngine;
import game.Bot;

public class IsNew extends BaseAction {

	@Override
	public void execute(Object[] args) {
		for(Bot b:GameEngine.getInstance().getBots()) {
			if(b.getId() == (Integer)args[0]) {
				b.setNewb();
				System.out.printf("WOWOMG");
			}
		}
	}
}