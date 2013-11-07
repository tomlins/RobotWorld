package net.tomlins.robotWorld.geo;

import java.awt.Dimension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Represents a map for a given area. The map stores the
 * current location of the owner of the map
 * @author jason
 *
 */
public abstract class Map {
	
	private final Logger log = LoggerFactory.getLogger(Map.class);

	/**
	 * The dimensions of the map
	 */
	protected Dimension area;
	
	/**
	 * Current position of the user of this map
	 */
	protected Position currentPosition;
	
	/**
	 * Get area of map
	 * @return area of map
	 */
	public Dimension getArea() {
		return area;
	}

	/**
	 * get current position of user of map
	 * @return current position
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * Set the current position in the map
	 * @param position
	 * @return new position, null if invalid position
	 */
	public Position setCurrentPosition(Position position) {
		if(validPosition(position)) {
			currentPosition = position;
			return currentPosition;
		}
		return null;
	}
	
	/**
	 * Validates the given position
	 * @param position
	 * @return false if invalid, otherwise true
	 */
	public boolean validPosition(Position position) {
		if( (position.getX() < 0 || position.getX() > area.width)
				|| (position.getY() < 0 || position.getY() > area.height)
				|| position.getFacing() == null) {
			log.info("Can't move in that direction");
			return false;
		}
		return true;
	}

}
