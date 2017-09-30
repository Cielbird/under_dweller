package models;

public class RawModel {
	public int vaoID;
	public int vertexCount;
	
	//Raw model setup
	public RawModel (int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	//Setters for the vars
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
