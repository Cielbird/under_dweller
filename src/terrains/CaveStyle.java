package terrains;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.util.vector.Vector3f;

import textures.ModelTexture;

public class CaveStyle {
	private ArrayList<String> chunkNames = new ArrayList<String>(Arrays.asList("straight_tunnel_1", "straight_tunnel_2","straight_tunnel_3"));
	private ArrayList<Vector3f> endPositions = new ArrayList<Vector3f>(Arrays.asList(new Vector3f(0,0,-10),new Vector3f(0,0,-10),new Vector3f(0,0,-10)));
	private ArrayList<Vector3f> endRotations = new ArrayList<Vector3f>(Arrays.asList(new Vector3f(0,-10,0),new Vector3f(0,0,0),new Vector3f(0,0,0)));
	private String caveTextureName = "white";
	
	
	public ArrayList<String> getChunkNames() {
		return chunkNames;
	}
	public ArrayList<Vector3f> getEndPositions() {
		return endPositions;
	}
	public ArrayList<Vector3f> getEndRotations() {
		return endRotations;
	}
	public String getCaveTextureName() {
		return caveTextureName;
	}
}
