package net.tomlins.robotWorld.geo;


/**
 * Represents the Robots location

 * @author jason
 *
 */
public class Position {
	
	/**
	 * x and y values
	 */
	private int x;
	private int y;
	
	/**
	 * The direction we are facing
	 */
	private Facing facing;
	
	@SuppressWarnings("unused")
	private Position() {}
	
	public Position(int x, int y, Facing facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
	}
	
	/**
	 * get x value
	 * @return x value
	 */
	public int getX() {
		return x;
	}

	/**
	 * get y value
	 * @return y value
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * get direction we are facing
	 * @return facing
	 */
	public Facing getFacing() {
		return facing;
	}
	
	/**
	 * Our position as a string
	 * e.g. 1,2,NORTH
	 * @return position as a String
	 */
	public String toString() {
		try {
			return x + "," + y + "," + facing;
		}
		catch(NullPointerException x) {
			return null;
		}
	}

}
