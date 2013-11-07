package net.tomlins.robotWorld.command;

import net.tomlins.robotWorld.geo.Facing;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.Position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describes how a robot should move forward and
 * set its new position on the map
 * @author jason
 *
 */
public class MoveCommand implements ICommand {

	private final Logger log = LoggerFactory.getLogger(MoveCommand.class);

	@Override
	public boolean execute(Map map) {
		
		Position currentPosition = map.getCurrentPosition();
		int currentX = currentPosition.getX();
		int currentY = currentPosition.getY();
		Facing facing = currentPosition.getFacing();
		
		switch(facing) {
			case NORTH : ++currentY; break;
			case SOUTH : --currentY; break;
			case EAST : ++currentX; break;
			case WEST : --currentX; break;
		}
		
		Position newPosition = new Position(currentX, currentY, facing);
				
		return map.setCurrentPosition(newPosition) == null ? false : true;
	}

}
