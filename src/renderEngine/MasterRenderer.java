package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Camera;
import entities.Light;
import entities.entity;

import models.TexturedModel;
import shaders.StaticShader;

public class MasterRenderer {
	
	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<entity>> entities = new HashMap<TexturedModel, List<entity>>();

	public void render(Light sun, Camera camera){
		renderer.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		entities.clear();
		
	}
	
	public void prossesEntity(entity entity){
		TexturedModel entityModel = entity.getModel();
		List<entity> batch = entities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		}else{
			List<entity> newBatch = new ArrayList<entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
			
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}
}
