package edu.mit.anekin.DroneClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import edu.mit.anekin.DroneClient.CommandPassing.CommandHandler;
import edu.mit.anekin.DroneClient.CommandPassing.CommandServer;
import edu.mit.anekin.DroneClient.ImageTaking.ImageTaker;

public class DroneClientMain {
	
	private static State state;
	
	public static int COMMAND_RECEIVER_PORT;
	public static String MAIN_SERVER_HOSTNAME;
	public static int MAIN_SERVER_PORT;
	
	public static void main(String[] args){
		if(hasValidArguments(args)){
			state = new State();
			COMMAND_RECEIVER_PORT = Integer.parseInt(args[0]);
			MAIN_SERVER_HOSTNAME = args[1];
			MAIN_SERVER_PORT = Integer.parseInt(args[2]);
			//start image server
			startImageTakingThread();
			//start message receiver sender
			startCommandServerThread();
			
			startCommandHandlerThread();
		}
	}
	
	public static synchronized State getState(){
		return state;
	}
	
	public static void startImageTakingThread(){
		Thread imageTakingThread = new Thread(new ImageTaker());
		imageTakingThread.start();
	}
	
	public static void startCommandServerThread(){
		Thread commandServerThread = new Thread(new CommandServer());
		commandServerThread.start();
	}
	
	public static void startCommandHandlerThread(){
		Thread commandHandlerThread = new Thread(new CommandHandler());
		commandHandlerThread.start();
	}
	
	public static boolean hasValidArguments(String[] args){
		//java -jar DroneClient.jar [PortForClientServer] [HostnameForMainServer] [PortForMainServer]
		if(args.length == 3){
			int clientConnectionPort = 0;
			try{
				clientConnectionPort = Integer.parseInt(args[0]);
			}catch(NumberFormatException e){
				System.out.println(getErrorMessage("The client connection port must be an integer"));
				return false;
			}
			try{
				ServerSocket server = new ServerSocket(clientConnectionPort);
				server.close();
			}catch(IllegalArgumentException e){
				System.out.println(getErrorMessage("Client connection port is invalid, must be between 0 and 65535"));
				return false;
			}catch(IOException e){
				System.out.println(getErrorMessage("Could not establish client server, client connection port may be taken"));
				return false;
			}
			try{
				int mainServerPort = Integer.parseInt(args[2]);
				if(mainServerPort < 0 || mainServerPort > 65535){
					System.out.println(getErrorMessage("Main server port is invalid, must be between 0 and 65535"));
					return false;
				}
			}catch(NumberFormatException e){
				System.out.println(getErrorMessage("The main server port must be an integer"));
				return false;
			}
		}else{
			System.out.println(getErrorMessage("Make sure you are specifying all arguments."));
			return false;
		}
		return true;
	}
	
	public static String getErrorMessage(String header){
		return header + "\n"
				+ "Correct usage:\n"
				+ "  java -jar DroneClient.jar [ClientConnectionPort] [MainServerHostname] [MainServerPort]\n"
				+ "For example:\n"
				+ "  java -jar DroneClient.jar 40023 18.189.50.164 40021";
	}
	
	public static String runCommand(String command){
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			while((line = reader.readLine()) != null){
				output.append(line + "\n");
			}
		} catch (IOException | InterruptedException e) {
			//System.out.println("Error with the command entered.");
		}
		return output.toString();
	}

}
