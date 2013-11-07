package net.tomlins.robotWorld.command;

import net.tomlins.robotWorld.geo.Map;

/**
 * A command a robot can carry out
 * @author jason
 *
 */
public interface ICommand {
	
	/**
	 * executes this command
	 * @param map
	 * @return true if executed successfully and position on map updated.
	 * 
	 */
	public boolean execute(Map map);

}
