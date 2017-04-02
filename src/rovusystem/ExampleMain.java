package rovusystem;


import simbad.gui.*;
import simbad.sim.*;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import java.awt.Color;
import java.util.ArrayList;


public class ExampleMain {

    public static void main(String[] args) {
        System.setProperty("j3d.implicitAntialiasing", "true");
        
        ExampleEnvironment env = new ExampleEnvironment();        

        CentralStation cs = CentralStation.getInstance();
        RoverFactory rf = new RoverFactory();
        ArrayList<Rover> swarm = new ArrayList<Rover>();
        
        PhotoRover photo1 = (PhotoRover) rf.createRover("photo", env.coords(-9, -9), "Photo 1"); 
        photo1.setOrientation(ECardinalDirection.WEST);
        PhotoRover photo2 = (PhotoRover) rf.createRover("photo", env.coords(-9, -8), "Photo 2");
        photo2.setOrientation(ECardinalDirection.SOUTH);
        PhotoRover photo3 = (PhotoRover) rf.createRover("photo", env.coords(-9, -7), "Photo 3");
        photo3.setOrientation(ECardinalDirection.EAST);
        PhotoRover photo4 = (PhotoRover) rf.createRover("photo", env.coords(-9, -6), "Photo 4");
        photo4.setOrientation(ECardinalDirection.NORTH);

        ScoutingRover scout1 = (ScoutingRover) rf.createRover("scouting", env.coords(9,-9), "Scout 1");
        ScoutingRover scout2 = (ScoutingRover) rf.createRover("scouting", env.coords(9, 9), "Scout 2");

/*        CarbonRover carbon1 = (CarbonRover) rf.createRover("carbon", new Vector3d(0, 0, .5), "Carbon 1"); 
        carbon1.setOrientation(ECardinalDirection.WEST);
        CarbonRover carbon2 = (CarbonRover) rf.createRover("carbon", new Vector3d(.5, 0, 0), "Carbon 2");
        carbon2.setOrientation(ECardinalDirection.SOUTH);
        CarbonRover carbon3 = (CarbonRover) rf.createRover("carbon", new Vector3d(0, 0, -.5), "Carbon 3");
        carbon3.setOrientation(ECardinalDirection.EAST);
        CarbonRover carbon4 = (CarbonRover) rf.createRover("carbon", new Vector3d(-.5, 0, 0), "Carbon 4");
        carbon4.setOrientation(ECardinalDirection.NORTH);
*/
        swarm.clear();
        
        swarm.add(photo1);
        swarm.add(photo2);
        swarm.add(photo3);
        swarm.add(photo4);

        swarm.add(scout1);
        swarm.add(scout2);

/*        swarm.add(carbon1);
        swarm.add(carbon2);
        swarm.add(carbon3);
        swarm.add(carbon4);
*/        
        for (Observer obs : swarm) {
        	obs.subject = cs;
        	cs.attach(obs);
        	if (obs instanceof PhotoRover) {
           		obs.setColor(new Color3f(Color.GREEN));
        	}
        	if (obs instanceof ScoutingRover) {
           		obs.setColor(new Color3f(Color.CYAN));        		
        	}
        	if (obs instanceof CarbonRover) {
           		obs.setColor(new Color3f(Color.DARK_GRAY));        		
        	}       	
        	env.add(obs);
        }
                
        Simbad frame = new Simbad(env, false);
        frame.update(frame.getGraphics());
        
    }

} 