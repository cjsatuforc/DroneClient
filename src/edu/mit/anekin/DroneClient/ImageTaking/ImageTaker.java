package edu.mit.anekin.DroneClient.ImageTaking;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;

import edu.mit.anekin.DroneClient.DroneClientMain;

public class ImageTaker implements Runnable{
	
	@Override
	public void run(){
		try {
			startImageTaker();
		} catch (IOException | InterruptedException e) {}
	}
	
	public void startImageTaker() throws IOException, InterruptedException{
		//raspistill -n -t 1 -w 500 -h 500 -o image.jpg
		String commandToTakeImage = "raspistill -n -t 1 -w 500 -h 500 -o image.jpg";
		String hostname = InetAddress.getLocalHost().getHostName();
		Socket initialSocket = new Socket(DroneClientMain.MAIN_SERVER_HOSTNAME, DroneClientMain.MAIN_SERVER_PORT);
		PrintWriter writer = new PrintWriter(initialSocket.getOutputStream());
		writer.println(hostname);
		writer.println(DroneClientMain.COMMAND_RECEIVER_PORT);
		writer.flush();
		while(true){
			DroneClientMain.runCommand(commandToTakeImage);
			BufferedImage image = ImageIO.read(new File("image.jpg"));
			Socket socket = new Socket(DroneClientMain.MAIN_SERVER_HOSTNAME, DroneClientMain.MAIN_SERVER_PORT);
			ImageIO.write(image, "jpg", socket.getOutputStream());
			socket.getOutputStream().flush();
		}
	}

}
