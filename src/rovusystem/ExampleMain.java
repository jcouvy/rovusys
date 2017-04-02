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
        
        PhotoRover photo1 = (PhotoRover) rf.createRover("photo", new Vector3d(0, 0, .5), "Photo 1"); 
        photo1.setOrientation(ECardinalDirection.WEST);
        PhotoRover photo2 = (PhotoRover) rf.createRover("photo", new Vector3d(.5, 0, 0), "Photo 2");
        photo2.setOrientation(ECardinalDirection.SOUTH);
        PhotoRover photo3 = (PhotoRover) rf.createRover("photo", new Vector3d(0, 0, -.5), "Photo 3");
        photo3.setOrientation(ECardinalDirection.EAST);
        PhotoRover photo4 = (PhotoRover) rf.createRover("photo", new Vector3d(-.5, 0, 0), "Photo 4");
        photo4.setOrientation(ECardinalDirection.NORTH);

        ScoutingRover scout1 = (ScoutingRover) rf.createRover("scouting", env.coords(4, -4), "Scout 1");
        ScoutingRover scout2 = (ScoutingRover) rf.createRover("scouting", env.coords(4,  4), "Scout 2");
        
        swarm.clear();
        
        swarm.add(photo1);
        swarm.add(photo2);
        swarm.add(photo3);
        swarm.add(photo4);

        swarm.add(scout1);
        swarm.add(scout2);
        
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
           		obs.setColor(new Color3f(Color.GRAY));        		
        	}       	
        	env.add(obs);
        }
                
        Simbad frame = new Simbad(env, false);
        frame.update(frame.getGraphics());
    }

} 