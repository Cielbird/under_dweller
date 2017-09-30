package terrains;


import java.util.Arrays;
import java.util.Collections;

import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import renderEngine.Loader;

public class Cavern {
	public static RawModel	generateModel(Loader loader) {
		

		
		//Setup skeleton

		float[] cavernSkeleton = {0f, 0f, 0f, 20f, 0f, 0f, 30f, 5f, 0f};
		
		//sliceVCount is the amount of vertices in one round slice of the tunnel.
		float[][] tunnelPatern ={{10, 0}/*, {20, 20}*/};
		int sliceVCount = tunnelPatern.length*2;
		int tunnelSize = 1;
		int V_COUNT = cavernSkeleton.length/3 * sliceVCount;
		
		System.out.println("V_Count = "+V_COUNT);
		System.out.println("sliceVCount = "+sliceVCount);
		

		//Setup returned parameters
		float[] vertices = new float[V_COUNT*3];
		float[] textureCoords = new float[V_COUNT*2];
		float[] normals = new float[V_COUNT*3];
		//int[] indices = {0, 3, 1, 0, 2, 3, 2, 5, 3, 2, 4, 5};
		int[] indices = new int[V_COUNT*6-(V_COUNT/sliceVCount+1)*6];
		
		
		//Construct vertices
		//Takes each xyz from "cavernSkeleton", and makes two xyz's that are slightly altered replicas
		//for the mesh
		
		//i/6*3
		
		//FOLLOWING LOOP TURNS SKELETON TO MESH
		//           V
		// 1 Loop per slice (and its vertices)
		for(int i=0; i<V_COUNT*3; i+=sliceVCount*3){
			System.out.println("A loop in skeleton to mesh code was just run");

			System.out.println("round " + i);
			//cavernSkeleton[i/6*3] is the skeleton's x
			//cavernSkeleton[i/6*3+1] is the skeleton's y
			//cavernSkeleton[i/6*3+2] is the skeleton's z
			
			
			
			//Find vector that points in the skeleton's point's direction
			Vector3f directionVec = new Vector3f();
			//If statement detects if the slice dealt with is the first or last, because when finding the vector of
			//                 the skeleton's direction, it uses the point in front of it and behind it.
			
			if((i/3+sliceVCount < V_COUNT) && (i != 0)){
				
				directionVec.x =  (cavernSkeleton[i/sliceVCount+3] - cavernSkeleton[i/sliceVCount]+
								-((cavernSkeleton[i/sliceVCount-3] - cavernSkeleton[i/sliceVCount])))/2; 
				
				directionVec.y =  (cavernSkeleton[i/sliceVCount+4] - cavernSkeleton[i/sliceVCount+1]+
								-((cavernSkeleton[i/sliceVCount-2] - cavernSkeleton[i/sliceVCount+1])))/2; 
				
				directionVec.z =  (cavernSkeleton[i/sliceVCount+5] - cavernSkeleton[i/sliceVCount+2]+
								-((cavernSkeleton[i/sliceVCount-1] - cavernSkeleton[i/sliceVCount+2])))/2; 
				
			}else if(i == 0){
				directionVec.x = (cavernSkeleton[i/sliceVCount+3] - cavernSkeleton[i/sliceVCount]);
				directionVec.y = (cavernSkeleton[i/sliceVCount+4] - cavernSkeleton[i/sliceVCount+1]);
				directionVec.z = (cavernSkeleton[i/sliceVCount+5] - cavernSkeleton[i/sliceVCount+2]);
				
			}else{
				directionVec.x = -(cavernSkeleton[i/sliceVCount-3] - cavernSkeleton[i/sliceVCount]);
				directionVec.y = -(cavernSkeleton[i/sliceVCount-2] - cavernSkeleton[i/sliceVCount+1]);
				directionVec.z = -(cavernSkeleton[i/sliceVCount-1] - cavernSkeleton[i/sliceVCount+2]);

			}
			directionVec.normalise();
			
			
			
			//Calculate distance
			double distance = Math.sqrt(directionVec.x*directionVec.x + directionVec.y*directionVec.y + directionVec.z*directionVec.z);

			//CALCULATE VERTECIES
			
			for(int j = 0; j<sliceVCount*3; j+=3){
				if(j/3<tunnelPatern.length){
					float[] paternDot = tunnelPatern[j/3];
					Collections.reverse(Arrays.asList(tunnelPatern));
					vertices[i+j] = (float) (cavernSkeleton[i/sliceVCount] - directionVec.z*paternDot[0]);
					vertices[i+j+1] = cavernSkeleton[i/sliceVCount+1]+paternDot[1];
					vertices[i+j+2] = (float) (cavernSkeleton[i/sliceVCount+2] + directionVec.x*paternDot[0]);
				}else{
					float[] paternDot = tunnelPatern[j/3-tunnelPatern.length];
					Collections.reverse(Arrays.asList(tunnelPatern));
					vertices[i+j] = (float) (cavernSkeleton[i/sliceVCount] + directionVec.z*paternDot[0]);
					vertices[i+j+1] = cavernSkeleton[i/sliceVCount+1]+paternDot[1];
					vertices[i+j+2] = (float) (cavernSkeleton[i/sliceVCount+2] - directionVec.x*paternDot[0]);
				}
			}
			
			

		}
		//-----END OF SKEL TO MESH-----//
		
		
		
		//Construct textureCoords
		for(int i=0; i<V_COUNT; i++){
			textureCoords[i] = 0;
		}
		//Construct normals
		for(int i=0; i<V_COUNT*3; i+=3){
			normals[i] = 0f;
			normals[i+1] = 1f;
			normals[i+2] = 0f;
		}
		
		
		//Construct indices		
		
        
		for(int i=0; i<V_COUNT-(sliceVCount+(cavernSkeleton.length/3)-1); i+=sliceVCount-1){
			//for every slice
			System.out.println(i);
			for(int j=0; j<sliceVCount-1; j+=1){
				System.out.println("123456789 "+j);
				//for every square
				indices[i*6 + j*6]=i*sliceVCount+j;
				indices[i*6 + j*6+1]=i*sliceVCount+j+sliceVCount+1;
				indices[i*6 + j*6+2]=i*sliceVCount+j+1;
				indices[i*6 + j*6+3]=i*sliceVCount+j;
				indices[i*6 + j*6+4]=i*sliceVCount+j+sliceVCount;
				indices[i*6 + j*6+5]=i*sliceVCount+j+sliceVCount+1;

			}
			
		}
		
		
		
		
		
		//TESTING
		//Loops to print data (for debugging).
        System.out.println("Vertices");
        for(float i:vertices){
            System.out.println(i);
        }
        System.out.println("TextureCoords");
        for(float i:textureCoords){
            System.out.println(i);
        }
        System.out.println("Normals");
        for(float i:normals){
            System.out.println(i);
        }
        System.out.println("Indices");
        for(int i:indices){
            System.out.println(i);
        }
		
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
		
}