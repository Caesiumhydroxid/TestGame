package com.mygdx.game;

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

public class GameState extends State {

	
	Point point;
	Grid grid;
	boolean pointTweening=false;
	private MyGestureListener listener;
	boolean collided=false;
	float time=0;
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
		grid = new Grid(6,2);
		Vector2 start= grid.getStartCoordinates();
		point.setPosition(start.x, start.y);
		entitys.add(point);
		entitys.add(new Missile(425,0,90,90,new Vector2(0,240)));
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
		camera.update();
		time+=dt;
		if(time>=5)
		{
			time=0;
			Vector2 v=grid.createNewSpawn(90);
			entitys.add(new Missile(v.x,v.y,90,90,new Vector2(00,200)));
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP))
		{
			listener.actionQueue.push(flingType.up);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.LEFT))
		{
			listener.actionQueue.push(flingType.left);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT))
		{
			listener.actionQueue.push(flingType.right);
		}
		if(Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.DOWN))
		{
			listener.actionQueue.push(flingType.down);
		}
		if(!listener.actionQueue.isEmpty()&&!pointTweening)
		{
			Vector2 target=grid.calculateNewPointPosition(listener.actionQueue.pop(), point);
			pointTweening=true;
			Tween.to(point, PointAcessor.XY, 0.10f).target(target.x, target.y).ease(TweenEquations.easeOutCubic).setCallback(pointTweenEnded).start(tweenManager);
		}
		tweenManager.update(dt);
		for(Entity e:entitys)
		{
			e.update(dt);
		}
		collided = false;
		for(Entity e1:entitys)
		{
			for(Entity e2:entitys)
			{
				if(e2!=e1&&(e1.isCollidable()&&e2.isCollidable()))
				{
					if(IntersectorShapes.intersects(e1.getCollisionShapes(), e2.getCollisionShapes()))
					{
						collided=true;
					}
				}
			}
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
