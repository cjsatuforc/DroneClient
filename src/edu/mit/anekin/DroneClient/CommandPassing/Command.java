package edu.mit.anekin.DroneClient.CommandPassing;

import edu.mit.anekin.DroneClient.ActionType;
import edu.mit.anekin.DroneClient.Behavior;

public class Command {
	
	private final CommandType type;
	private final Behavior behavior;
	private final ActionType action;
	private final int value;
	
	public Command(CommandType type, Behavior behavior, ActionType action, int value){
		this.type = type;
		this.behavior = behavior;
		this.action = action;
		this.value = value;
	}
	
	public Command(String rawCommand){
		String[] rawCommandSplit = rawCommand.split(":");
		type = CommandType.getCommandTypeFromName(rawCommandSplit[0]);
		if(type == CommandType.BEHAVIOR){
			behavior = Behavior.getBehaviorFromName(rawCommandSplit[1]);
			action = ActionType.NO_CHANGE;
			value = 0;			
		}else{
			action = ActionType.getActionFromName(rawCommandSplit[1]);
			behavior = Behavior.NO_CHANGE;
			value = Integer.parseInt(rawCommandSplit[2]);
		}
	}
	
	public CommandType getType(){
		return type;
	}
	
	public Behavior getBehavior(){
		return behavior;
	}
	
	public ActionType getActionType(){
		return action;
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		if(type == CommandType.BEHAVIOR){
			return type.getName() + ":" + behavior.getName();
		}else{
			return type.getName() + ":" + action.getName() + ":" + value;
		}
	}

}
