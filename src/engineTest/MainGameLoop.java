/*
 - UnderDweller - 

Version: Alpha (0.0.15)

Needed:
- Transparency
- Cave chunk assembly

- Low-poly rendering techniques
*/

package engineTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import entities.entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.CaveStyle;
import terrains.CaveSystem;
import terrains.Cavern;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {
	public static void main(String[] args) {
		String version = "Alpha Test (0.0.11)";           
		
		
		
		DisplayManager.createDisplay(version);
		
		
		
		//Set up loader
		Loader loader = new Loader();
		
		//Set up Model, Textured model, camera, and full entity
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1.0f);

		entity stallEntity = new entity(staticModel, new Vector3f(0,0,0),0,0,0,1);
			
		//Setup Cave style,...
		CaveStyle normalCaveStyle = new CaveStyle();
		CaveSystem caveSystem = new CaveSystem(normalCaveStyle.getChunkNames(),
											   normalCaveStyle.getEndPositions(), 
											   normalCaveStyle.getEndRotations(),  
											   new ModelTexture(loader.loadTexture(normalCaveStyle.getCaveTextureName())),
											   loader);
		

		//LIGHTS... CAMERA...
		Light light = new Light(new Vector3f(200,200,100), new Vector3f(1,1,1));
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		//... ACTION!
		
		//Main Game Loop
		while(!Display.isCloseRequested()){
			//game logic
			
			
			renderer.prossesEntity(stallEntity);
			stallEntity.increaseRotation(0, 0.1f, 0);
			
			//Cave:
			for(entity terrainChunk : caveSystem.getTerrainEntities()){
				renderer.prossesEntity(terrainChunk);
			}
			
			
			camera.move();
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		
		
		
		
		
		
		//Show is over..
		//Clean up
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
