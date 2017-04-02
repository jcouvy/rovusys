// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

public class CarbonRover extends Rover {

    private ArrayList<Integer> storage;

    public CarbonRover(Vector3d pos, String name) {
        super(pos, name);
        storage = new ArrayList<Integer>();
        System.out.println("Creating a new CarbonRover object");
    }

    public void measurePosition() {
        System.out.println("Rover ["+getName()+"] measured "+location());
    }

   public boolean sendData() {
	   System.out.println("Sending data to " + subject.toString());
       return true;
    }
 
    public void freeStorage() {
        storage.clear();
        System.out.println("Clearing storage");
    }
    
    /** This method is called cyclically (20 times per second) by the simulator engine. */
    @Override
    public void performBehavior() {
		
		if (subject.getRequest() == ERequest.MEASURE) {		
			if (this.collisionDetected() | this.anOtherAgentIsVeryNear()) {
	    		setState("COLLISION");
	    	} else {
	    		update();
	    	}
	    	
	    	switch(getState()) {
	    		case "MEASURING":
	    			setColor(new Color3f(Color.DARK_GRAY));
	    	   		drive();
	    	   		if (timer(5)) measurePosition(); sendData();
	                if (timer(100)) setRotationalVelocity(Math.PI / 2 * (0.5 - Math.random()));
	                break;
	    		case "COLLISION":
	       			setColor(new Color3f(Color.RED));
	       			avoid();
	             	break;
	    		case "ENDING":
	    			shutdown();
	    			break;
	    		default: break;
	    	}
		}
	}
};
