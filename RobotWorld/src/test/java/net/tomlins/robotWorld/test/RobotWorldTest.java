package net.tomlins.robotWorld.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.TableMap;
import net.tomlins.robotWorld.mechanoid.RobbyRobot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some simple test cases to put our robot through its paces

 * @author jason
 *
 */
public class RobotWorldTest {
	
	static final Logger log = LoggerFactory.getLogger(RobotWorldTest.class);

	
	Map map;
	
	/**
	 * Using robot of type RobbyRobot to expose report() method
	 * for use in testing as opposed to using base class Robot
	 */
	RobbyRobot robot;

	@Before
	public void setUp() throws Exception {
		
		// Create new TableMap
		map = new TableMap();
		
		// Load the robots behaviour
		ResourceBundle commands = null;
		try {
			commands = ResourceBundle.getBundle("net.tomlins.robotworld.mechanoid.commands");
		}
		catch(MissingResourceException x) {
			fail("Could not load the robots behaviour. Cannot execute tests");
		}
		
		// Create RobbyRobot
		robot = new RobbyRobot(map, commands);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPlaceRobotOnMap() {
		
		log.info("testPlaceRobotOnMap");
		
		// bottom left
		assertEquals(true, robot.recieveCommand("PLACE 0,0,NORTH"));
		
		// top left
		assertEquals(true, robot.recieveCommand("PLACE 0,4,NORTH"));
		
		// bottom right
		assertEquals(true, robot.recieveCommand("PLACE 0,4,NORTH"));
		
		// top right
		assertEquals(true, robot.recieveCommand("PLACE 4,4,NORTH"));
	}
	
	@Test
	public void testPlaceRobotOutOfMap() {

		log.info("testPlaceRobotOutOfMap");

		// out of bounds
		assertEquals(false, robot.recieveCommand("PLACE 5,5,NORTH"));		
		assertEquals(false, robot.recieveCommand("PLACE 0,5,NORTH"));		
		assertEquals(false, robot.recieveCommand("PLACE 5,0,NORTH"));		
		assertEquals(false, robot.recieveCommand("PLACE -4,-2,NORTH"));
		
		// facing unknown direction
		assertEquals(false, robot.recieveCommand("PLACE 0,0,NRTH"));		

	}
	
	@Test
	public void testUnKnownCommand() {

		log.info("testUnKnownCommand");
		
		// PLACE spelt incorrectly
		assertEquals(false, robot.recieveCommand("PLCE 0,0,NORTH"));	
		
		// Format incorrect, spaces used
		assertEquals(false, robot.recieveCommand("PLACE 5, 5, NORTH"));	
		
		// MOVE spelt incorrectly
		assertEquals(true, robot.recieveCommand("PLACE 0,0,NORTH"));
		assertEquals(false, robot.recieveCommand("MVE"));		
		
		// Unknown command, JUMP
		assertEquals(true, robot.recieveCommand("PLACE 0,0,NORTH"));
		assertEquals(false, robot.recieveCommand("JUMP"));		
	}
	
	@Test
	public void testControlRobot() {
		
		log.info("testControlRobot");

		// valid moves return true if move successful
		assertEquals(true, robot.recieveCommand("PLACE 0,0,NORTH"));
		assertEquals(true, robot.recieveCommand("MOVE"));
		assertEquals(true, robot.recieveCommand("LEFT"));
		assertEquals("0,1,WEST", robot.report());
		
		// invalid placement, retain last known position
		assertEquals(false, robot.recieveCommand("PLACE 5,0,NORTH"));
		assertEquals("0,1,WEST", robot.report());
		
		// cant move robot of edge of map, retain last position
		assertEquals(false, robot.recieveCommand("MOVE"));
		assertEquals("0,1,WEST", robot.report());
	}

}
