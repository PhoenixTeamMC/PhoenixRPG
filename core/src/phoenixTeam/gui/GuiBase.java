package phoenixTeam.gui;

import phoenixTeam.PrimalJourney;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public abstract class GuiBase implements InputProcessor{
	
	private boolean shouldMove = true;
	
	Array<ElementBase> elements = new Array<ElementBase>();
	
	public GuiBase(){
		PrimalJourney.inputHandler.addProcessor(0, this);
	}
	
	public GuiBase setMoveable(boolean movable){
		shouldMove = movable;
		return this;
	}
	
	public abstract void render();
	
	

}
