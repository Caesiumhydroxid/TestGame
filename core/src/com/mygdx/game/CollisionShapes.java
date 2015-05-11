package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class CollisionShapes {
	Vector<Circle> circles;
	Vector<Rectangle> rectangles;
	
	CollisionShapes()
	{
		circles = new Vector<Circle>();
		rectangles = new Vector<Rectangle>();
	}
}
