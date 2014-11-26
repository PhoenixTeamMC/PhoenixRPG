package pheonixTeam.main.entity.living;

import java.util.List;

import pheonixTeam.main.Direction;
import pheonixTeam.main.entity.enums.PrimaryClasses;
import pheonixTeam.main.entity.enums.Races;
import pheonixTeam.main.entity.enums.SecondaryClasses;
import pheonixTeam.main.entity.skills.SkillFireball;
import pheonixTeam.main.item.Item;
import pheonixTeam.main.map.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

/**
 * @author Strikingwolf, chbachman
 */
public class EntityPlayer extends EntityLiving
{
    private int heldItemIndex = 0;
    public Item heldItem;
    public List<Item> inventory;

    public void switchHeldItemRight() {
        heldItemIndex++;
        heldItem = inventory.get(heldItemIndex);
    }

    public Item getHeldItem() {
        return inventory.get(heldItemIndex);
    }
    
    Texture texture;

    OrthographicCamera camera;
    
    public EntityPlayer(Map map, OrthographicCamera camera) {
        texture = new Texture("player.png");
        skills.add(new SkillFireball());
        this.camera = camera;
        this.x = map.width / 2;
        this.y = map.height / 2;
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
    public void display(Batch batch) {
        batch.draw(texture, x, y, 4, 4);
    }

    public static final float moveSpeed = 1;
    
    @Override
    public void update(Map map) {
    	
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	camera.translate(1, 0);
            this.move(Direction.RIGHT, moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        	camera.translate(-1, 0);
            this.move(Direction.LEFT, moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        	camera.translate(0, 1);
            this.move(Direction.UP, moveSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        	camera.translate(0, -1);
            this.move(Direction.DOWN, moveSpeed);
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.O)){
        	camera.zoom += .02;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.L)){
        	camera.zoom -= .02;
        }
        
        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/camera.viewportWidth);

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        //camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        //camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
        
        camera.update();
    }
}
