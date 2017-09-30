package terrains;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import entities.entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class CaveSystem {

	//Make chunk array and texture
	private ArrayList<RawModel> terrainChunks = new ArrayList<RawModel>();
	private ModelTexture texture;
	private int chunkCount;
	private ArrayList<entity> terrainEntities = new ArrayList<entity>();
	
	
	//Main setup
	public CaveSystem(ArrayList<String> tunnelChunkNames, ArrayList<Vector3f> endPositions, ArrayList<Vector3f> endRotations, ModelTexture texture, Loader loader){
		this.terrainChunks = setupRawModels(tunnelChunkNames, loader);
		this.texture = texture;
		this.chunkCount = terrainChunks.size();
		
		Vector3f lastChunkPos = new Vector3f(0,0,0);
		
		//loop for every chunk
		for(RawModel currentChunk : terrainChunks){
			int i = terrainChunks.indexOf(currentChunk);
			
			TexturedModel texturedChunk = new TexturedModel(currentChunk, new ModelTexture(loader.loadTexture("white")));
			ModelTexture terrainTexture = texturedChunk.getTexture();
			terrainTexture.setShineDamper(10);
			terrainTexture.setReflectivity(1.0f);
			
			//process current chunk's entity

			System.out.println(lastChunkPos.x);
			System.out.println(lastChunkPos.y);
			System.out.println(lastChunkPos.z);
			terrainEntities.add(new entity(texturedChunk, new Vector3f(lastChunkPos.x, lastChunkPos.y, lastChunkPos.z),0,0,0,1));
			lastChunkPos.x += endPositions.get(i).x;
			lastChunkPos.y += endPositions.get(i).y;
			lastChunkPos.z += endPositions.get(i).z;
		}

		
		for(entity j : terrainEntities){
			System.out.println(j.getPosition().x);
			System.out.println(j.getPosition().y);
			System.out.println(j.getPosition().z);
		}
	}
	


	//Name to RawModel
	//Function to quickly deal with all the raw models used
	private ArrayList<RawModel> setupRawModels(ArrayList<String> tunnelChunkNames, Loader loader){
		ArrayList<RawModel> rawModels = new ArrayList<RawModel>();
		for (String chunkName : tunnelChunkNames){
			rawModels.add(OBJLoader.loadObjModel(chunkName, loader));
		}
		return rawModels;
	}
	
	
	
	
	//Getters
	public ArrayList<RawModel> getTerrainChunks() {
		return terrainChunks;
	}
	public ArrayList<entity> getTerrainEntities() {
		return terrainEntities;
	}

	public ModelTexture getTexture() {
		return texture;
	}
}
	