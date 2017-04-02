// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.ArrayList;

public abstract class Rover extends Observer {

	private int id;
	private Coordinate position;
    private int velocity = 1;
    private boolean isOnline = true;
    private ECardinalDirection orientation = ECardinalDirection.NORTH;
    private ArrayList<Coordinate> route = new ArrayList<Coordinate>();

    private String state = "IDLE";
    
    public Rover(Vector3d pos, String name) {
    	super(pos, name);
    }
    
    public int getId() {
    	return id;
    }
    
    public Coordinate getPosition() {
    	return position;
    }
    
    public ECardinalDirection getOrientation() {
    	return orientation;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getState() {
    	return state;
    }
  
    public void setState(String state) {
    	this.state = state;
    }
   
    public void setOrientation(ECardinalDirection ori) {
    	orientation = ori;
    }
     
    public void move(int dist) {
    }

    public void rotate(ECardinalDirection dir) {
        orientation = dir;
        System.out.println("Rover ["+getName()+"] rotates to "+orientation);
    }

    public void moveToCoord(Coordinate coord) {
    }

    public void shutdown() {
        isOnline = false;
        this.resetPosition();
        this.getCanBeTraversed();
        this.setState("FINAL");
        System.out.println("Rover ["+getName()+"] - Mission DONE");
    }

    public abstract void freeStorage();

    public boolean sendData() {
 	   System.out.println("Rover ["+getName()+"] sent data to " + subject.toString());
       return true;
     }
    
    public void missionDone() {
    	System.out.println("NEXT REQUEST IS: "+subject.getRequest().next());
    	subject.setRequest(subject.getRequest().next());
    }
    
/*    public void update() {
        if (subject.getRequest() == ERequest.EXPLORE) {
            setState("SCOUTING");
        } 
        else if (subject.getRequest() == ERequest.SCAN) {
            setState("SCANNING");
        }
        else if (subject.getRequest() == ERequest.MEASURE) {
            setState("MEASURING");
        }
    }*/

    /*****************************************************/
    /** Methods to adapt our implementation with Simbad **/
    /*****************************************************/
    
    /** Return the current location of the robot */
    public Point3d location(){
        Point3d loc = new Point3d();
        this.getCoords(loc);
        return loc;
    }
    
    /** Translate coords into Vector3d **/
    public Vector3d coords(int x, int y) {
        return new Vector3d(-y, 0, -x);
    }
        
    /** Change the robots direction to the opposite of its current direction */
    public void reverse() {
    	switch(getOrientation()) {
    		case NORTH: 
    			this.orientation = ECardinalDirection.SOUTH; break;
    		case SOUTH: 
    			this.orientation = ECardinalDirection.NORTH; break;
    		case EAST: 
    			this.orientation = ECardinalDirection.WEST; break;
    		case WEST: 
    			this.orientation = ECardinalDirection.EAST; break;
    		default: break;
    	}
    	this.rotateY(Math.PI);
    }
    
    /** Move the robot straight towards its current direction */
    public void drive() {
        this.setTranslationalVelocity(velocity);
    }
    
    /** Stop the robot and start rotating **/
    public void avoid() {
    	this.setTranslationalVelocity(0);
    	setRotationalVelocity(Math.PI / 2);
    }
    
    /** Timer to repeat actions every sec seconds **/
    public boolean timer(int sec) {
    	return this.getCounter() % sec == 0;
    }
        
    /** This method is called by the simulator engine on reset. */
    @Override
    public void initBehavior() {
        System.out.println("Spawning a new robot: " + getName());
        
        switch(getOrientation()) {
	        case NORTH: this.rotateY(Math.PI); break;
	        case EAST: this.rotateY(Math.PI / 2); break;
			case WEST: this.rotateY(-Math.PI / 2); break;
			case SOUTH: break;
			default: break;
	    }
    }    
    
};
