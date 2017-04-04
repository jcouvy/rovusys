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

        CentralStation cs = CentralStation.getInstance(); // Singleton Design Pattern
        RoverFactory rf = new RoverFactory();
        ArrayList<Rover> swarm = new ArrayList<Rover>();
        
        PhotoRover photo1 = (PhotoRover) rf.createRover("photo", env.coords(-1, -1), "Photo 1"); 
        PhotoRover photo2 = (PhotoRover) rf.createRover("photo", env.coords( 1,  1), "Photo 2");
        PhotoRover photo3 = (PhotoRover) rf.createRover("photo", env.coords( 1, -1), "Photo 3");
        PhotoRover photo4 = (PhotoRover) rf.createRover("photo", env.coords(-1,  1), "Photo 4");
        photo1.setOrientation(ECardinalDirection.WEST);
        photo2.setOrientation(ECardinalDirection.SOUTH);
        photo3.setOrientation(ECardinalDirection.EAST);
        photo4.setOrientation(ECardinalDirection.NORTH);

        ScoutingRover scout1 = (ScoutingRover) rf.createRover("scouting", env.coords(9,-9), "Scout 1");
        ScoutingRover scout2 = (ScoutingRover) rf.createRover("scouting", env.coords(9, 9), "Scout 2");

        CarbonRover carbon1 = (CarbonRover) rf.createRover("carbon", new Vector3d(0, 0, .5), "Carbon 1"); 
        CarbonRover carbon2 = (CarbonRover) rf.createRover("carbon", new Vector3d(.5, 0, 0), "Carbon 2");
        CarbonRover carbon3 = (CarbonRover) rf.createRover("carbon", new Vector3d(0, 0, -.5), "Carbon 3");
        CarbonRover carbon4 = (CarbonRover) rf.createRover("carbon", new Vector3d(-.5, 0, 0), "Carbon 4");
        carbon1.setOrientation(ECardinalDirection.WEST);
        carbon2.setOrientation(ECardinalDirection.SOUTH);
        carbon3.setOrientation(ECardinalDirection.EAST);
        carbon4.setOrientation(ECardinalDirection.NORTH);

        swarm.clear();
        
        swarm.add(photo1);
        swarm.add(photo2);
        swarm.add(photo3);
        swarm.add(photo4);

        swarm.add(scout1);
        swarm.add(scout2);

        swarm.add(carbon1);
        swarm.add(carbon2);
        swarm.add(carbon3);
        swarm.add(carbon4);
        
        for (Observer obs : swarm) {
        	obs.subject = cs;
        	cs.attach(obs);
        	if (obs instanceof PhotoRover) {
           		obs.setColor(new Color3f(Color.GREEN));
        	}
        	else if (obs instanceof ScoutingRover) {
           		obs.setColor(new Color3f(Color.CYAN));        		
        	}
        	else if (obs instanceof CarbonRover) {
           		obs.setColor(new Color3f(Color.WHITE));
        	}       	
        	env.add(obs);
        }
                
        Simbad frame = new Simbad(env, false);
        frame.update(frame.getGraphics());
        
        /** Mission Start **/
        System.out.println("----- MISSION START ------");
        cs.setRequest(ERequest.EXPLORE);
        cs.notifyObservers();
    }

} 