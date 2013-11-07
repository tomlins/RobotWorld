package net.tomlins.robotWorld.geo;

import java.awt.Dimension;


/**
 * Concrete implementation of a Map. A TableMap has
 * a predefined area of 5 x 5 where bottom left is 0,0 and
 * top right is 4,4
 * @author jason
 *
 */
public class TableMap extends Map {
	
	public static final int maxX = 4;
	public static final int maxY = 4;
	
	/**
	 * Creates a new TableMap with default values
	 */
	public TableMap() {
		this.area = new Dimension(maxX, maxY);
	}

	public TableMap(int maxX, int maxY) {
		this.area = new Dimension(maxX, maxY);
	}
}
