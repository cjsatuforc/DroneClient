package edu.mit.anekin.DroneClient;

public class Action {
	
	private final ActionType actionType;
	private final int value;
	
	public Action(ActionType actionType, int value){
		this.actionType = actionType;
		this.value = value;
	}
	
	public ActionType getActionType(){
		return actionType;
	}
	
	public int getValue(){
		return value;
	}

}
