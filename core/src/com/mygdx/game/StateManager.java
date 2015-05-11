package com.mygdx.game;

import java.util.Stack;

public class StateManager {
	Stack<State> states;
	StateManager()
	{
		states = new Stack<State>();
	}
	
	public void pushState(State state)
	{
		state.create();
		states.push(state);
	}
	
	public State popState()
	{
		states.firstElement().dispose();
		return states.pop();
	}
	
	public void update(float dt)
	{
		
		states.firstElement().update(dt);
	}
	public void render()
	{
		states.firstElement().render();
	}
}
