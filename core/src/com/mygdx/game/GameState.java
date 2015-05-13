package com.mygdx.game;

import java.util.Stack;
import java.util.Vector;

import objectAcessor.PointAcessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utils.SpawnPosition;

public class GameState extends State {

	
	Point point;
	Grid grid;
	boolean pointTweening=false;
	private MyGestureListener listener;
	boolean collided=false;
	float time=0;
	float x;
	private Vector<Entity> entitys;
	GameState(TweenManager tweenManager, SpriteBatch batch,
			ShapeRenderer shapeRenderer, OrthographicCamera camera, MyGestureListener listener) {
		super(tweenManager, batch, shapeRenderer, camera);
		this.listener = listener;
		entitys = new Vector<Entity>();
	}

	@Override
	public void create() {
		point = new Point();
		grid = new Grid(3,3);
		Vector2 start= grid.getStartCoordinates();
		point.setPosition(start.x, start.y);
		entitys.add(point);
		time = 1.8f;
	}
	
	private TweenCallback pointTweenEnded = new TweenCallback()
	{
		
		@Override
		public void onEvent(int type, BaseTween<?> source)
		{
			if(type == TweenCallback.COMPLETE)
				pointTweening=false;	
		}
	};
	
	@Override
	public void update(float dt) {
		super.update(dt);
		applyUserInput(listener.actionQueue);
		camera.update();
		time+=dt;
		if(time>=1.4f)
		{
			time=0;
			Vector2 v=grid.createNewSpawn(90,90,SpawnPosition.left);
			entitys.add(new Missile(v.x,v.y,90,90,tweenManager,SpawnPosition.left));
		}
		if(!listener.actionQueue.isEmpty()&&!pointTweening)
		{
			Vector2 target=grid.calculateNewPointPosition(listener.actionQueue.pop(), point);
			pointTweening=true;
			Tween.to(point, PointAcessor.XY, 0.2f).target(target.x, target.y).ease(TweenEquations.easeOutCubic).setCallback(pointTweenEnded).start(tweenManager);
		}
		tweenManager.update(dt);
		updateEntitys(dt);
		collided = entitysCollided();
		//camera.rotate(90*dt);
		x+=dt;
	}
	private void updateEntitys(float dt)
	{
		for(Entity e:entitys)
		{
			e.update(dt);
		}
	}
	private boolean entitysCollided()
	{
		for(Entity e1:entitys)
		{
			for(Entity e2:entitys)
			{
				if(e2!=e1&&(e1.isCollidable()&&e2.isCollidable()))
				{
					if(IntersectorShapes.intersects(e1.getCollisionShapes(), e2.getCollisionShapes()))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void applyUserInput(Stack<flingType> actionQueue)
	{
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP))
		{
			actionQueue.push(flingType.up);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.LEFT))
		{
			actionQueue.push(flingType.left);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT))
		{
			actionQueue.push(flingType.right);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.DOWN))
		{
			actionQueue.push(flingType.down);
		}
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		grid.render(shapeRenderer);
		for(Entity e:entitys)
		{
			e.render(shapeRenderer);
		}
		if(collided)
			shapeRenderer.rect(0, 0, 100, 100);
		shapeRenderer.end();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
