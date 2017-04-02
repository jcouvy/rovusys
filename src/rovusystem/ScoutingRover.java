// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.sim.RobotFactory;

public class ScoutingRover extends Rover {

    private ArrayList<Coordinate> storage;

    public ScoutingRover(Vector3d pos, String name) {
        super(pos, name);
        storage = new ArrayList<Coordinate>();
        System.out.println("Creating a new ScoutingRover object");
        
        RobotFactory.addBumperBeltSensor(this, 12);
    }

    public void savePosition() {
        System.out.println("Rover ["+getName()+"] found an obstacle at "+location());
    }

    public boolean sendData() {
	   System.out.println("Sending data to " + subject.toString());
	   return true;
    }

    public void freeStorage() {
        storage.clear();
        System.out.println("Clearing Storage");
    }

    
    /** This method is called cyclically (20 times per second) by the simulator engine. */
    @Override
    public void performBehavior() {    	
    	
    	if (subject.getRequest() == ERequest.EXPLORE) {
    		if (this.collisionDetected() | this.anOtherAgentIsVeryNear()) {
        		setState("COLLISION");
        	} else {
        		update();
        	}

    	   	switch(getState()) {
        		case "SCOUTING":
        			setColor(new Color3f(Color.CYAN));
        	   		drive();
                    if (timer(100)) setRotationalVelocity(Math.PI / 2 * (0.5 - Math.random()));
                    break;
        		case "COLLISION":
           			setColor(new Color3f(Color.RED));
           			savePosition(); sendData();
           			reverse();
                 	break;
        		case "ENDING":
             		shutdown();
         			break;
        		default: break;
        	}
    	}
    }
};
