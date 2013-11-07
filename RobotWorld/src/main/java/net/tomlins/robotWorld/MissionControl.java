package net.tomlins.robotWorld;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tomlins.robotWorld.controller.ControllerFactory;
import net.tomlins.robotWorld.controller.IController;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.TableMap;
import net.tomlins.robotWorld.mechanoid.RobbyRobot;
import net.tomlins.robotWorld.mechanoid.Robot;

public class MissionControl {
		
	static private final Logger log = LoggerFactory.getLogger(MissionControl.class);

	public static void main(String[] args) {
				
		// Creates a default map for robot to navigate
		Map theKnownWorld = new TableMap();
		
		// Obtain a class list of ICommand implementations to give our robot capabilities
		ResourceBundle commands = null;
		try {
			commands = ResourceBundle.getBundle("net.tomlins.robotworld.mechanoid.commands");
		}
		catch(MissingResourceException x) {
			// Couldn't locate commands, poor robot will have no additional commands
			log.warn("Missing resource. Robot will have no additional commands");
		}
		
		// Create our robot with a map and a list of commands it understands
		Robot robbyRobot = new RobbyRobot(theKnownWorld, commands);
		
		// Use a factory to return an appropriate controller for this robot.
		// If we specify a file name, return a file controller, otherwise use
		// the default keyboard console controller.
		IController controller = ControllerFactory.getController(args);
		
		// Issue commands to the robot from the controller until we say 'QUIT'
		String command;
		while(!((command = controller.readCommand()).equals(IController.QUIT_COMMAND))) {
			robbyRobot.recieveCommand(command);
		}
				
		// Close the controller
		controller.destroy();
				
	}
	
	
	// We're done.

}
