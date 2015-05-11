package com.mygdx.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public final class IntersectorShapes {
	public static boolean intersects(CollisionShapes s1, CollisionShapes s2)
	{
		boolean collides=false;
		for(Circle c1:s1.circles)
		{
			for(Circle c2:s2.circles)
			{
				if(Intersector.overlaps(c1, c2))
					collides =true;
			}
			for(Rectangle r1:s2.rectangles)
			{
				if(Intersector.overlaps(c1,r1))
					collides = true;
			}
		}
		for(Rectangle r1:s1.rectangles)
		{
			for(Circle c1:s2.circles)
			{
				if(Intersector.overlaps(c1, r1))
					collides =true;
			}
			for(Rectangle r2:s2.rectangles)
			{
				if(Intersector.overlaps(r1,r2))
					collides = true;
			}
		}
		return collides;
	}
}
