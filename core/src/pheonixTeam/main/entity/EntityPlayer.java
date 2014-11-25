package pheonixTeam.main.entity;

import pheonixTeam.main.Direction;
import pheonixTeam.main.Map;
import pheonixTeam.main.entity.enums.PrimaryClasses;
import pheonixTeam.main.entity.enums.Races;
import pheonixTeam.main.entity.enums.SecondaryClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Strikingwolf, chbachman
 */
public class EntityPlayer extends EntityLiving
{
	
	SpriteBatch batch;
	Texture texture;
	
	public EntityPlayer(){
		batch = new SpriteBatch();
		texture = new Texture("player.png");
	}
	
    //Race
    public Races race = Races.NONE;

    //Class
    public PrimaryClasses primaryClass = PrimaryClasses.NONE;
    public SecondaryClasses secondaryClass = SecondaryClasses.NONE;

    //Stats
    int constitution = 0;
    int strength = 0;
    int dexterity = 0;
    int wisdom = 0;

    //Skills
    //TODO
    
    @Override
    public void display(){
    	batch.begin();
    	
    	batch.draw(texture, x, y, 32, 32);
    	
    	batch.end();
    }
    
    @Override
    public void update(Map map){
    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
    		this.move(Direction.RIGHT, 5);
    	}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
    		this.move(Direction.LEFT, 5);
    	}else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
    		this.move(Direction.UP, 5);
    	}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
    		this.move(Direction.DOWN, 5);
    	}
    }
}