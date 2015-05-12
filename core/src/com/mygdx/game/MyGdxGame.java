package com.mygdx.game;

import objectAcessor.MissileAcessor;
import objectAcessor.PointAcessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
public class MyGdxGame implements ApplicationListener{
	SpriteBatch batch;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	Texture img;
	Grid grid;
	MyGestureListener listener;
	static TweenManager tweenManager;
	Point point;
	StateManager stateManager;
	@Override
	public void create () {
		listener=new MyGestureListener();
		Gdx.input.setInputProcessor(new GestureDetector(listener));
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(1080,1920);
		camera.setToOrtho(false, 1080, 1920);
		Tween.registerAccessor(Point.class, new PointAcessor());
		Tween.registerAccessor(Missile.class,new MissileAcessor());
		tweenManager = new TweenManager();
		stateManager = new StateManager();
		stateManager.pushState(new GameState(tweenManager,batch,shapeRenderer,camera,listener));
		
	}

	@Override
	public void render () {
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
}
