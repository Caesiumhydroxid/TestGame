package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Missile extends Entity {

	private float width,height;
	
	public Missile() {
		super();
	}

	public Missile(float x, float y,float width,float height,Vector2 velocity) {
		super(x, y);
		this.width = width;
		this.height = height;
		setVelocity(velocity);
		shapes.rectangles.add(new Rectangle(x,y,width,height));
	}

	@Override
	public CollisionShapes getCollisionShapes() {
		shapes.rectangles.firstElement().x=x;
		shapes.rectangles.firstElement().y=y;
		shapes.rectangles.firstElement().width=width;
		shapes.rectangles.firstElement().height=height;
		return shapes;
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
