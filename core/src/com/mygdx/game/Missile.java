package com.mygdx.game;

import objectAcessor.MissileAcessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utils.utils;

public class Missile extends Entity {

	private float width,height;
	boolean currentlyTweening=false;
	Timeline tweeningPattern;
	TweenManager tweenManager;
	public Missile() {
		super();
	}
	
	public Missile(float x, float y,float width,float height,Vector2 velocity, TweenManager tweenManager) {
		super(x, y);
		this.width = width;
		this.height = height;
		setVelocity(velocity);
		shapes.rectangles.add(new Rectangle(x,y,width,height));
		this.tweenManager = tweenManager;
	}
	private TweenCallback missileTweenEnded = new TweenCallback()
	{
		
		@Override
		public void onEvent(int type, BaseTween<?> source)
		{
			if(type == TweenCallback.COMPLETE)
			{
				currentlyTweening=false;	
			}
		}
	};
	@Override
	public CollisionShapes getCollisionShapes() {
		shapes.rectangles.firstElement().x=x;
		shapes.rectangles.firstElement().y=y;
		shapes.rectangles.firstElement().width=width;
		shapes.rectangles.firstElement().height=height;
		return shapes;
	}
	@Override
	public void update(float dt)
	{
		if(!currentlyTweening)
		{
			currentlyTweening=true;
			Timeline.createSequence().push(Tween.to(this, MissileAcessor.XY,0.6f).targetRelative(0, utils.gridSize))
			.push(Tween.to(this, MissileAcessor.XY,0.6f).targetRelative(utils.gridSize,0))
			.push(Tween.to(this, MissileAcessor.XY,0.6f).targetRelative(0, utils.gridSize))
			.push(Tween.to(this, MissileAcessor.XY,0.6f).targetRelative(-utils.gridSize,0).setCallback(missileTweenEnded))
			.start(tweenManager);
		}
	}
	@Override
	public boolean isCollidable() {
		return true;
	}

	@Override
	void render(ShapeRenderer renderer) {
		renderer.setColor(0, 150, 150, 255);
		renderer.rect(x, y, width, height);
	}
	
}
