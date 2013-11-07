package net.tomlins.robotWorld.controller;

/**
 * A device for reading commands
 * @author jason
 *
 */
public interface IController {
	
	public static final String QUIT_COMMAND = "QUIT";
	
	/**
	 * Read in a command string
	 * @return the command
	 */
	public String readCommand();
	
	/**
	 * Close any resources and destroy the
	 * controller
	 */
	public void destroy();
	
}
