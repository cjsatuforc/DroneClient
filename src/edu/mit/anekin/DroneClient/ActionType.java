package edu.mit.anekin.DroneClient;

public enum ActionType {

	FORWARD("forward"),
	BACKWARD("backward"),
	LEFT("left"),
	RIGHT("right"),
	UP("up"),
	DOWN("down"),
	ROTATE_LEFT("rotate_left"),
	ROTATE_RIGHT("rotate_right"),
	SWITCH_LED("switch_led"),
	NO_CHANGE("no_change");
	
	private final String name;
	
	private ActionType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static ActionType getActionFromName(String name){
		switch(name.toLowerCase()){
			case "forward":
				return FORWARD;
			case "backward":
				return BACKWARD;
			case "left":
				return LEFT;
			case "right":
				return RIGHT;
			case "up":
				return UP;
			case "down":
				return DOWN;
			case "rotate_left":
				return ROTATE_LEFT;
			case "rotate_right":
				return ROTATE_RIGHT;
			case "switch_led":
				return SWITCH_LED;
			default:
				return NO_CHANGE;
		}
	}
	
}
