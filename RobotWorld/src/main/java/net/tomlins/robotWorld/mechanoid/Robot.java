package net.tomlins.robotWorld.mechanoid;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tomlins.robotWorld.MissionControl;
import net.tomlins.robotWorld.command.ICommand;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.Position;

/**
 * 
 * @author jason
 * 
 * Defines a generic robot with basic common commands
 * and any additional commands
 *
 */
public abstract class Robot {
	
	static private final Logger log = LoggerFactory.getLogger(Robot.class);

	/**
	 *  Common default commands known to all robots
	 */
	public final static String PLACE_COMMAND = "PLACE";
	public final static String REPORT_COMMAND = "REPORT";	
	
	/**
	 * Map of the known world for this robot
	 */
	protected Map map;
	
	/**
	 * A dynamic set of commands this robot can execute, loaded
	 * from a resource
	 */
    protected static final HashMap<String, ICommand>
    			robotVocabulary = new HashMap<String, ICommand>();
    
    /**
     * Creates a new Robot with a Map and Commands its knows of
     * @param map
     * @param commandList
     */
    protected Robot(Map map, ResourceBundle commands) {
		this.map = map;
		loadCommands(commands);
    }

	/**
	 * Load the classes implementing the commands the robot can understand
	 * @param commands List of classes implementing command commands
	 * @return number of commands loaded
	 */
    protected final int loadCommands(ResourceBundle commands) {
		
		// Number of commands this robot knows how to execute
    	int numberOfCommands = 0;
		
    	if(commands == null) {
			// No additional commands
			return numberOfCommands;
		}
		
        // Instantiate a new command implementation and store in Map
		Enumeration<String> enumer = commands.getKeys();        
        while (enumer.hasMoreElements()) {
            String key = (String)enumer.nextElement();
            String value = commands.getString(key);            
            try {
                Object command = Class.forName( value).newInstance();
                robotVocabulary.put( key, (ICommand)command);
                ++numberOfCommands;
            }
            catch(Exception x) { 
            	// Simply ignore unknown commands we cannot instantiate
            }
        }
        
        log.info("Loaded ", numberOfCommands, " commands");
        
        return numberOfCommands;
	}
	
	/**
	 * Places our robot at a position within the boundaries
	 * of its map by setting that position within the map. If
	 * the position is outside of the map, do nothing.
	 * @param position
	 * @return position if successful, null if not
	 */
    protected Position place(Position position) {
		return map.setCurrentPosition(position);
	}
	
	/**
	 * Returns our current position as a string in the form
	 * X,Y,F - e.g. 2,2,WEST
	 * @return String of current location, otherwise null
	 */
    protected String report() {
    	Position currentPosition = map.getCurrentPosition();
		if(currentPosition==null) {
			return "unknown";
		}
		return currentPosition.toString();
	}
	
	/**
	 * Tries to execute the given command. If not know, ignore
	 * @param command should match from a list of known behaviour
	 * @return true if command executed, false otherwise
	 */
    public boolean recieveCommand(String command) {
		ICommand instruction = (ICommand)robotVocabulary.get(command);
		if(instruction==null) {
			log.warn("Unknown command : " + command);
			return false;
		}
		return instruction.execute(map);
	}
	
}
