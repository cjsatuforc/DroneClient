package edu.mit.anekin.DroneClient.CommandPassing;

import edu.mit.anekin.DroneClient.Action;
import edu.mit.anekin.DroneClient.DroneClientMain;

public class CommandHandler implements Runnable{

	@Override
	public void run(){
		try {
			startCommandHandler();
		} catch (InterruptedException e) {}
	}
	
	public void startCommandHandler() throws InterruptedException{
		while(true){
			Action action = DroneClientMain.getState().takeNextAction();
			handleAction(action);
			Thread.sleep(1000);
		}
	}
	
	public void handleAction(Action action){
		//TODO action thing gets command run python yay.
		System.out.println(action.getActionType().getName() + " : " + action.getValue());
		DroneClientMain.runCommand("python george_helper.py " + action.getActionType().getName().toLowerCase() + " " + action.getValue());
	}
	
}
