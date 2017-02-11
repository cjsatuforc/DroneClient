package edu.mit.anekin.DroneClient.CommandPassing;

public enum CommandType {
	
	BEHAVIOR("behavior"),
	ACTION("action");
	
	private final String name;
	
	private CommandType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static CommandType getCommandTypeFromName(String name){
		switch(name.toLowerCase()){
			case "behavior":
				return BEHAVIOR;
			case "action":
				return ACTION;
			default:
				return BEHAVIOR;
		}
	}

}
