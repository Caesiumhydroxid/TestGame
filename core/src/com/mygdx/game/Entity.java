package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	float x=0;
	float y=0;
	private Vector2 velocity;
	protected CollisionShapes shapes;
	Entity()
	{
		velocity = new Vector2();
		shapes = new CollisionShapes();
	}
	Entity(float x, float y)
	{
		this();
		this.x=x;
		this.y=y;
	}
	
	public void setVelocity(Vector2 velocity)
	{
		this.velocity=velocity;
	}
	public Vector2 getVelocity()
	{
		return velocity;
	}
	public void update(float dt)
	{
		translate(velocity.x*dt,velocity.y*dt);
	}
	public CollisionShapes getCollisionShapes()
	{
		return null;
	}
	public boolean isCollidable()
	{
		return false;
	}
	void render(ShapeRenderer renderer)
	{
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void setPosition(float x,float y){
		setX(x);
		setY(y);
	}
	public void translate(float dx,float dy)
	{
		 x=x+dx;
		 y=y+dy;
	}
}
