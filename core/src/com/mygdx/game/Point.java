package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Point extends Entity {

	
	Point()
	{
		super();
		shapes.circles.add(new Circle(x,y,45));
	}
	@Override
	public CollisionShapes getCollisionShapes() {
		
		shapes.circles.firstElement().x=x;
		shapes.circles.firstElement().y=y;
		return shapes;
	}
	
	public boolean isCollidable()
	{
		return true;
	}
	@Override
	public void render(ShapeRenderer renderer)
	{
		renderer.circle(x, y, 45);
	}
}
