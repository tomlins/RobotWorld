package net.tomlins.robotWorld.geo;

/**
 * Describes the direction we are facing
 * @author jason
 *
 */
public enum Facing {
	
	NORTH("NORTH"),
	SOUTH("SOUTH"),
	EAST("EAST"),
	WEST("WEST");
	
	private String currentlyFacing;
	
	private Facing(String facing) {
		currentlyFacing = facing;
	}
	
	public String toString() {
		return currentlyFacing;
	}
}
