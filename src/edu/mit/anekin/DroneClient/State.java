package edu.mit.anekin.DroneClient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class State {
	
	private Behavior behavior;
	
	private BlockingQueue<Action> overrideActions;
	private BlockingQueue<Action> actions;
	
	public State(){
		behavior = Behavior.IDLE;
		overrideActions = new ArrayBlockingQueue<Action>(100);
		actions = new ArrayBlockingQueue<Action>(100);
		for(Action action : Behavior.getActions(behavior)){
			try {
				actions.put(action);
			} catch (InterruptedException e) {}
		}
	}
	
	public synchronized Behavior getBehavior(){
		return behavior;
	}
	
	/**
	 * updates the set behavior and resets all actions to be of new behavior
	 */
	public synchronized void updateBehavior(Behavior behavior){
		this.behavior = behavior;
		overrideActions.clear();
		actions.clear();
		for(Action action : Behavior.getActions(behavior)){
			try {
				actions.put(action);
			} catch (InterruptedException e) {}
		}
	}
	
	public synchronized void addOverrideAction(Action action){
		try {
			overrideActions.put(action);
		} catch (InterruptedException e) {}
	}
	
	public synchronized Action peekNextAction(){
		if(overrideActions.size() > 0) return overrideActions.peek();
		else if(actions.size() > 0) return actions.peek();
		return null;
	}
	
	public synchronized Action takeNextAction() throws InterruptedException{
		if(overrideActions.size() > 0) return overrideActions.take();
		else if(actions.size() > 1) return actions.take();
		else if(actions.size() == 1){
			Action actionToTake = actions.take();
			for(Action action : Behavior.getActions(behavior)){
				try {
					actions.put(action);
				} catch (InterruptedException e) {}
			}
			return actionToTake;
		}
		return new Action(ActionType.NO_CHANGE, 10);
	}

}
