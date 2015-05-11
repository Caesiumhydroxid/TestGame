package com.mygdx.game;

import java.util.Stack;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class MyGestureListener implements GestureListener {

	public Stack<flingType> actionQueue;
	MyGestureListener()
	{
		actionQueue = new Stack<flingType>();
	}
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Vector2 vec = new Vector2(velocityX,velocityY);
		if(vec.len()>800)
		{
			float angle = vec.angle();
			if(angle<=45||angle>315)
				actionQueue.push(flingType.right);
			else if(angle>45&&angle<=135) 
				actionQueue.push(flingType.down);
			else if(angle>135&&angle<=225)
				actionQueue.push(flingType.left);
			else if(angle>225&&angle<=315) 
				actionQueue.push(flingType.up);
			if(actionQueue.size()==2)
			{
				int i=0;
				i=i+1;
			}
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
