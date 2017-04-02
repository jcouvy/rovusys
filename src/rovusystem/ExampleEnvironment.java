package rovusystem;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class ExampleEnvironment extends EnvironmentDescription {
	
	public static final int WORLD_SIZE =  20;

	public ExampleEnvironment() {	
	        light1IsOn = true;
	        light2IsOn = true;
	        this.setUsePhysics(false);
	        this.setWorldSize(WORLD_SIZE);
	        this.initializeComponents();
	        }
	    
	    /** Translate coords **/
	    public Vector3d coords(int x, int y) {
	        return new Vector3d(-y, 0, -x);
	    }
	    
	    
	    /** Add environment objects here **/  
	    private void initializeComponents() {
	        
	        /** Add 4 walls */
	        Wall topWall = new Wall(new Vector3d(-WORLD_SIZE/2, 0, 0), WORLD_SIZE, 2, this);
	        topWall.setColor(new Color3f(Color.BLACK));
	        topWall.rotate90(1);
	        add(topWall);
	        
	        Wall bottomWall = new Wall(new Vector3d(WORLD_SIZE/2, 0, 0), WORLD_SIZE, 2, this);
	        bottomWall.setColor(new Color3f(Color.GREEN));
	        bottomWall.rotate90(1);
	        add(bottomWall);
	        
	        Wall leftWall = new Wall(new Vector3d(0, 0, WORLD_SIZE/2), WORLD_SIZE, 2, this);
	        leftWall.setColor(new Color3f(Color.RED));
	        add(leftWall);
	        
	        Wall rightWall = new Wall(new Vector3d(0, 0, -WORLD_SIZE/2), WORLD_SIZE, 2, this);
	        rightWall.setColor(new Color3f(Color.MAGENTA));
	        add(rightWall);
	        
	        /** Adding a garage to store rovers when inactive (they need to be on the map in Simbad) **/
	        Wall garageWall1 = new Wall(new Vector3d(7.5, 0, 5), 5, 1, this);
	        garageWall1.setColor(new Color3f(Color.YELLOW));
	        add(garageWall1);
	        
	        Wall garageWall2 = new Wall(new Vector3d(5, 0, 7.5), 5, 1, this);
	        garageWall2.setColor(new Color3f(Color.YELLOW));
	        garageWall2.rotate90(1);
	        add(garageWall2);
	        
	        /** Add 2 boxes around the center point */	        
	        Box box1 = new Box(coords(0, -5), new Vector3f(3, 1, 3), this);
	        box1.setColor(new Color3f(Color.YELLOW));
	        add(box1);

	        Box box2 = new Box(coords(0, 5), new Vector3f(3, 1, 1), this);
	        box2.setColor(new Color3f(Color.YELLOW));
	        add(box2);
	    }
}
