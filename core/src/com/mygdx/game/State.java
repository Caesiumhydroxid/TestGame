package com.mygdx.game;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class State implements ApplicationListener {
	protected TweenManager tweenManager;
	protected SpriteBatch batch;
	protected ShapeRenderer shapeRenderer;
	protected OrthographicCamera camera;
	State(TweenManager tweenManager,SpriteBatch batch, ShapeRenderer shapeRenderer,OrthographicCamera camera)
	{
		this.tweenManager=tweenManager;
		this.batch = batch;
		this.shapeRenderer = shapeRenderer;
		this.camera = camera;
	}
	
	public void update(float dt)
	{
		
	}
	
}
