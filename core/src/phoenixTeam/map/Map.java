package phoenixTeam.map;

import phoenixTeam.entity.Entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * 
 * @author chbachman
 *
 */
public class Map{
	
	

	public TiledMap map;
	public OrthogonalTiledMapRenderer renderer;
	
	private Engine engine;
	
	public final int width;
	public final int height;

	
	public Map(Engine engine, int width, int height){
		map = new TiledMap();
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		
		map.getLayers().add(new TiledMapTileLayer(width, height, 16, 16));
		
		new MapGenerator((TiledMapTileLayer)map.getLayers().get(0)).generate();
		
		this.width = width;
		this.height = height;
		
	}
	
	public void display(OrthographicCamera camera, SpriteBatch batch){
		renderer.setView(camera);
		renderer.render();
	}
	
	
	/**
	 * Spawn the entity into the world
	 * @param entity
	 */
	public void spawnEntity(Entity entity){
		
		Game main = (Game) Gdx.app.getApplicationListener();
		
		main.getScreen()
		
		entity.onSpawn(this);

		engine.addEntity(entity);
	}
	
	/**
	 * Kill the entity
	 * @param entity
	 */
	public void killEntity(Entity entity){
		
		entity.onDeath(this);
		
		entityList.remove(entity);
	}
}
