package edu.mit.anekin.DroneClient.CommandPassing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import edu.mit.anekin.DroneClient.Action;
import edu.mit.anekin.DroneClient.DroneClientMain;

public class CommandServer implements Runnable{

	@Override
	public void run() {
		try {
			startServer();
		} catch (IOException e) {}
	}
	
	public void startServer() throws IOException{
		ServerSocket server = new ServerSocket(DroneClientMain.COMMAND_RECEIVER_PORT);
		while(true){
			Socket socket = server.accept();
			handleConnection(socket);
		}
	}
	
	public void handleConnection(Socket socket){
		Thread connection = new Thread(new Runnable(){
			public void run(){
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String rawCommand = reader.readLine();
					Command command = new Command(rawCommand);
					reader.close();
					socket.close();
					if(command.getType() == CommandType.BEHAVIOR){
						DroneClientMain.getState().updateBehavior(command.getBehavior());;
					}else{
						DroneClientMain.getState().addOverrideAction(new Action(command.getActionType(), command.getValue()));
					}
				} catch (IOException e) {}
			}
		});
		connection.start();
	}

}
