package net.tomlins.robotWorld.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of IController that reads commands from
 * a given file. If the file cannot be found/opened or a
 * IOException occurs a 'QUIT' command will be returned.
 * @author jason
 *
 */
public class FileController implements IController {

	private DataInputStream din;
	private BufferedReader br;
	
	/**
	 * Specifies a file name from which to read instructions
	 * to issue to a robot.
	 *
	 * @param fileName example 'c:\\myfolder\\instructions.cmd'
	 */
	public FileController(String fileName) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(din));
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File, " + fileName + ", not found.");
		}
		
	}

	@Override
	public String readCommand() {
		
		if(br==null) {
			return QUIT_COMMAND;
		}
		
		String command;
		try {
			command = br.readLine();
		}
		catch (IOException e) {
			// issue a QUIT command to exit controlling the robot
			e.printStackTrace();
			return QUIT_COMMAND;
		}
		
		return command;
	}
	
	@Override
	public void destroy() {
		
		if(din==null) {
			return;
		}
		
		try {
			din.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
