package net.tomlins.robotWorld.controller;

/**
 * Simple factory to return a specific type of IController
 * according to passed args. Used to control a Robot.
 * @author jason
 *
 */
public class ControllerFactory {
	
	private ControllerFactory() {}
	
	/**
	 * Return a IController based on given args
	 * @param args
	 * @return implementation of IController
	 */
	public static IController getController(String[] args) {
		
		if(args.length > 0) {
			
			if(args[0].endsWith(".cmd")) {
				// The robot will be controlled from a file
				// containing instructions
				return new FileController(args[0]);
			}
			
			 /**
			  * We could test for others as our
			  * implementation grows e.g.
			  * 
			  * if(args[0].startsWith("http")) {
			  * 	return new HttpController(args[0]);
			  * }
			  */
			
		}
		
		// Default to console controller
		return new ConsoleController();
	}

}
