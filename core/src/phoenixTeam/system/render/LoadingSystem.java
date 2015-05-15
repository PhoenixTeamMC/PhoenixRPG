package phoenixTeam.system.render;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import phoenixTeam.component.ComponentMappers;
import phoenixTeam.component.movement.BoundingBoxComponent;
import phoenixTeam.component.render.AnimationComponent;
import phoenixTeam.component.render.AnimationLoaderComponent;
import phoenixTeam.component.render.LoaderComponent;
import phoenixTeam.component.render.RenderComponent;

import static phoenixTeam.PrimalJourney.assetManager;

public class LoadingSystem extends IteratingSystem{

	public LoadingSystem() {
		super(Family.one(LoaderComponent.class, AnimationLoaderComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		assetManager.update();
		
		if(ComponentMappers.animationloader.has(entity)){
			AnimationLoaderComponent a = ComponentMappers.animationloader.get(entity);
			
			if(!a.hasStarted){
				assetManager.load(a.toLoad, Animation.class);
				a.hasStarted = true;
			}
			
			if(assetManager.isLoaded(a.toLoad, Animation.class)){
				AnimationComponent c = new AnimationComponent(assetManager.get(a.toLoad, Animation.class), a.mode);
				
				entity.add(c);
				
				entity.remove(AnimationLoaderComponent.class);
				
				addRenderComponent(entity, c.animation.getKeyFrame(0));
			}
		} else {
			LoaderComponent l = ComponentMappers.loader.get(entity);
			
			if(!l.hasStarted){
				assetManager.load(l.toLoad, Texture.class);
				l.hasStarted = true;
			}
			
			if(assetManager.isLoaded(l.toLoad, Texture.class)){
				addRenderComponent(entity, assetManager.get(l.toLoad, TextureRegion.class));
				entity.remove(LoaderComponent.class);
			}
		}
		
	}
	
	private void addRenderComponent(Entity entity, TextureRegion t){
		if(ComponentMappers.boundingBox.has(entity)){
			
			BoundingBoxComponent box = ComponentMappers.boundingBox.get(entity);
			
			entity.add(new RenderComponent(t, box.width, box.height));
		}else{
			entity.add(new RenderComponent(t));
		}
	}

}
