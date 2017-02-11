package edu.mit.anekin.DroneClient;

import java.util.ArrayList;
import java.util.List;

public enum Behavior {

	CIRCLE("circle"),
	IDLE("idle"),
	ROAM("roam"),
	LINE("line"),
	CUSTOM("custom"),
	NO_CHANGE("no_change");
	
	private final String name;
	
	private Behavior(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static Behavior getBehaviorFromName(String name){
		switch(name.toLowerCase()){
			case "circle":
				return CIRCLE;
			case "idle":
				return IDLE;
			case "roam":
				return ROAM;
			case "line":
				return LINE;
			case "custom":
				return CUSTOM;
			default:
				return NO_CHANGE;
		}
	}
	
	public static List<Action> getActions(Behavior behavior){
		List<Action> actions = new ArrayList<>();
		switch(behavior){
			case CIRCLE:
				for(int i=0; i<8; i++){
					actions.add(new Action(ActionType.RIGHT, 10));
					actions.add(new Action(ActionType.ROTATE_LEFT, 10));
				}
				break;
			case IDLE:
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				break;
			case ROAM:
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				break;
			case LINE:
				actions.add(new Action(ActionType.FORWARD, 10));
				break;
			case CUSTOM:
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				break;
			default:
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				actions.add(new Action(ActionType.NO_CHANGE, 10));
				break;
		}
		return actions;
	}
	
}
