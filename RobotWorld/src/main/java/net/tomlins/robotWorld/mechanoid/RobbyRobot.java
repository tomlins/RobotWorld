package net.tomlins.robotWorld.mechanoid;

import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tomlins.robotWorld.MissionControl;
import net.tomlins.robotWorld.geo.Facing;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.Position;

/**
 * Concrete implementation of a Robot which we can control
 * given a Map and a list of commands it knows how to execute
 * 
 * @author jason
 *
 */
public class RobbyRobot extends Robot {
	
	static final Logger log = LoggerFactory.getLogger(RobbyRobot.class);

	/**
	 * Creates a new RobbyRobot
	 * @param map Map for the robot to navigate
	 * @param behaviour this robot can perform
	 */
	public RobbyRobot(Map map, ResourceBundle behaviour) {
		super(map, behaviour);
	}
	
	@Override
	public boolean recieveCommand(String command) {
		
		// robot receives a command
		
		if(command==null) {
			log.warn("No command recieved");
			return false;
		}
		
		if(command.startsWith(PLACE_COMMAND)) {
			// Set the robots location in the map
			Position position = place(command);
			return position == null ? false : true;
		}
		
		else if(map.getCurrentPosition() == null) {			
			// If we're not on the known map, we can't do anything
			log.warn("Command received but We're not on the map! Ignore.");
			return false;
		}
		
		else if(REPORT_COMMAND.equals(command)) {
			// Report our current position
			report();
			return true;
		}
		
		// Execute any customizable command
		return super.recieveCommand(command);
		
	}
	
	// Private helper methods follow...
	
	/**
	 * Places the robot in the Map by setting the maps position
	 * @param command e.g. 0,0,NORTH
	 * @return position if successful, null if not
	 */
	protected Position place(String command) {
		
		int x, y = 0;
		Facing facing = null;
		
		// Extract position. Different 'types' of robots may choose
		// to interpret this string differently e.g. with/without spaces,
		// which is why we do this here and not in the base class.
		try {
			String coords = command.substring(PLACE_COMMAND.length());
			coords = coords.trim();
			StringTokenizer st = new StringTokenizer(coords, ",");
			x = Integer.valueOf(st.nextToken());
			y = Integer.valueOf(st.nextToken());
			facing = Facing.valueOf(st.nextToken());
		}
		catch(Exception e) {
			// Catch all exception, non-parsable command, ignore
			return null;
		}
		
		// place robot at the given position. If position
		// of the map, the robot will not be placed and will
		// either be at limbo or last known position.
		return place(new Position(x,y, facing));

	}
	
	/**
	 * Report on the robots current location, e.g. '3,3,WEST'
	 * 
	 * Method exposed for type RobbyRobot for convenience so we can
	 * report directly on this object as well as being able to issue
	 * a command.
	 * 
	 * @return robots position as a string, null otherwise
	 */
	@Override
	public String report() {
		String sPosition = super.report();

		if(sPosition!=null) {
			//TODO: should really be a View object, not sys out!
			System.out.println(sPosition);
		}
		
		return sPosition;
	}
}
