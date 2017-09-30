package models;

import textures.ModelTexture;

//Takes in raw model and texture and puts them together
public class TexturedModel {
	
	//Make raw model and texture
	private RawModel rawModel;
	private ModelTexture texture;
	
	//Main setup
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel = model;
		this.texture = texture;
		
	}
	
	//Getters
	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}
	
	
	
}
