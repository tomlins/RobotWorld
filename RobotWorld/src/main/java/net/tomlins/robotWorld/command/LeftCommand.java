package net.tomlins.robotWorld.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tomlins.robotWorld.geo.Facing;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.Position;

/**
 * Describes how to move a robot left and sets its
 * new position on the map
 * @author jason
 *
 */
public class LeftCommand implements ICommand {
	
	private final Logger log = LoggerFactory.getLogger(LeftCommand.class);

	@Override
	public boolean execute(Map map) {
		
		Position currentPosition = map.getCurrentPosition();
		Facing facing = currentPosition.getFacing();
		Facing nowFacing = null;
		
		switch(facing) {
			case NORTH : nowFacing = Facing.WEST; break;
			case SOUTH : nowFacing = Facing.EAST; break;
			case EAST : nowFacing = Facing.NORTH; break;
			case WEST : nowFacing = Facing.SOUTH; break;
		}
		
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();
		Position newPosition = new Position(xPos, yPos, nowFacing);
		
		log.info("Robot now facing " + nowFacing + " co-ords : " + xPos + ":" + yPos);
		
		return map.setCurrentPosition(newPosition) == null ? false : true;
	}

}
