package net.tomlins.robotWorld.controller;

import java.util.Scanner;

/**
 * A keyboard controller for sending commands to a robot
 * @author jason
 *
 */
public class ConsoleController implements IController {
	
	private Scanner scanner;
	
	/**
	 * Creates a new keyboard/console controller
	 */
	public ConsoleController() {
		scanner = new Scanner(System.in);
	}

	public String readCommand() {
		String command = scanner.nextLine();
		return command;
	}
		
	public void destroy() {
		scanner.close();
		System.out.println("Goodbye");
	}

}
