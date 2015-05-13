package com.mygdx.game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.utils.MissileType;
import com.mygdx.utils.SpawnPosition;

public class Missile extends Entity {

	private float width,height;
	boolean currentlyTweening=false;
	Timeline tweeningPattern;
	TweenManager tweenManager;
	MissileType type;
	SpawnPosition spawnPosition;
	public Missile() {
		super();
	}
	
	public Missile(float x, float y,float width,float height, TweenManager tweenManager,SpawnPosition spawnPosition) {
		super(x, y);
		this.width = width;
		this.height = height;
		shapes.rectangles.add(new Rectangle(x,y,width,height));
		this.tweenManager = tweenManager;
		this.spawnPosition = spawnPosition;
	}
	public TweenCallback missileTweenEnded = new TweenCallback()
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
			MissileTweenFactory.createTimelineForMissile(this, MissileType.leftRight,spawnPosition).start(tweenManager);
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
