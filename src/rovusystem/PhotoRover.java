// -------photos-------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.sim.RobotFactory;

public class PhotoRover extends Rover {

	/** The storage should be an ArrayList<Scan>, for the sake of
	 *  simplicity it will Strings instead. Otherwise it would require
	 *  to create a Photo (composed of Coords, see ScoutingRover).
	 */
    private ArrayList<String> storage;
    
    public PhotoRover(Vector3d pos, String name) {
        super(pos, name);
        storage = new ArrayList<String>();
        System.out.println("Creating a new PhotoRover object");
        
        RobotFactory.addCameraSensor(this);
    }

    /** The Rover stops to scan a position and sends the location of the scanned area
     *  If the storage is full (ie. size is 100 in our impl) then the rover clears it.
     */
    public void scanPosition() {
    	this.setRotationalVelocity(0);
    	this.setTranslationalVelocity(0);
    	if (storage.size() == 100) freeStorage();
    	String scan = new String("Rover ["+getName()+"] scanned "+location());
    	storage.add(scan);
		System.out.println(scan);
    }

    /** Empty the storage **/
    public void freeStorage() {
        storage.clear();
        System.out.println("Rover ["+getName()+"] has cleared storage");
    }
    
    /** Informs that the Rover is updating,
     *  Changes its State from IDLE to SCANNING(active) if the request is SCAN
     *  If the state is FINAL, the rover does nothing (same as state-chart)
     */
    public void update() {
    	super.update();
    	if (getState() == "FINAL") return;
        if (subject.getRequest() == ERequest.SCAN) {
            setState("SCANNING");
        }
    }
   
    /** Informs that the Rover's mission is complete,
     *  the Central Station goes to its next request
     */
    public void missionDone() {
    	super.missionDone();
    	subject.setRequest(ERequest.MEASURE);
    	subject.notifyObservers();
    }
    
    /** This method is called cyclically (20 times per second) by the simulator engine. */
    @Override
    public void performBehavior() {
    		
		// We consider that the rover has finished his work when the counter hits 500 (easier sim).
		// The counter increases even in IDLE state so we add 500 more time unit.
		if (getState() != "FINAL") {
			if (this.collisionDetected() | this.anOtherAgentIsVeryNear()) {
	    		setState("COLLISION");
			}
			// We consider that the rover has finished his work when the counter hits 500 (easier sim).
			if (this.getCounter() == 1000) {
				setState("ENDING");
			}
    	}
    		
		switch(getState()) {
			case "IDLE" :
				break;	
			case "SCANNING":
				setColor(new Color3f(Color.GREEN));
				drive();
				if (timer(5)) scanPosition(); if (storage.size() == 100) sendData();
				if (timer(100)) setRotationalVelocity(Math.PI / 2 * (0.5 - Math.random()));
				break;
			case "COLLISION":
				setColor(new Color3f(Color.RED));
				avoid();
				setState("SCANNING");
				break;
			case "ENDING":
				setColor(new Color3f(Color.GREEN));
				shutdown();
				missionDone();
				break;
			case "FINAL":
				return;
		}
    }
};
