package net.tomlins.robotWorld.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tomlins.robotWorld.geo.Facing;
import net.tomlins.robotWorld.geo.Map;
import net.tomlins.robotWorld.geo.Position;

/**
 * Describes how a robot can move right and sets its
 * new location in the map
 * @author jason
 *
 */
public class RightCommand implements ICommand {

	private final Logger log = LoggerFactory.getLogger(RightCommand.class);
	
	@Override
	public boolean execute(Map map) {
		
		Position currentPosition = map.getCurrentPosition();
		Facing facing = currentPosition.getFacing();
		Facing nowFacing = null;
		
		switch(facing) {
			case NORTH : nowFacing = Facing.EAST; break;
			case SOUTH : nowFacing = Facing.WEST; break;
			case EAST : nowFacing = Facing.SOUTH; break;
			case WEST : nowFacing = Facing.NORTH; break;
		}
		
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();
		Position newPosition = new Position(xPos, yPos, nowFacing);
		
		log.info("Robot now facing " + nowFacing + " co-ords : " + xPos, ":" + yPos);
		
		return map.setCurrentPosition(newPosition) == null ? false : true;
	}

}
