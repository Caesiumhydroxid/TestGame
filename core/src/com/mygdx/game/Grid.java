package com.mygdx.game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utils.SpawnPosition;
import com.mygdx.utils.utils;

public class Grid extends Entity{
	Vector2 size;
	int[][] grid;
	float sectionSize;
	float lineWidth;
	Grid(int x,int y)
	{
		grid=new int[x][y];
		size = new Vector2(x,y);
		sectionSize = 135;
		lineWidth=10;
	}
	public Vector2 getStartCoordinates()
	{
		return new Vector2(utils.WIDTH/2.f+sectionSize*0.5f*((size.x+1)%2),utils.HEIGHT/2.f+sectionSize*0.5f*((size.y+1)%2));
	}
	
	public Vector2 createNewSpawn(float width,float height,SpawnPosition position)
	{
		float overflowX = +sectionSize*0.5f*((size.x+1)%2);
		int lowx = (int)Math.ceil(-size.x/2);
		int highx = (int)Math.round(size.x/2.f)-1;
		int rngx = MathUtils.random(lowx, highx);
		int lowy = (int)Math.round(-size.y/2);
		int highy = (int)Math.round(size.y/2.f)-1;
		int rngy = MathUtils.random(lowy, highy);
		float halfx=utils.WIDTH/2.f;
		float halfy=utils.HEIGHT/2.f;
		float overflowY=sectionSize*0.5f*((size.y+1)%2);
		switch(position)
		{
		case down:
			return new Vector2(halfx-width/2.f+overflowX+rngx*sectionSize,halfy+overflowY-width/2.f-sectionSize*10);
		case up:
			return new Vector2(halfx-width/2.f+overflowX+rngx*sectionSize,halfy+overflowY-width/2.f+sectionSize*10);
		case left:
			return new Vector2(halfx-width/2.f-overflowX-sectionSize*10,halfy+overflowY+rngy*sectionSize-width/2.f);
		case right:
			return new Vector2(halfx+width/2.f+overflowX+sectionSize*10,halfy+overflowY+rngy*sectionSize-width/2.f);
		}
		return null;
	}
	class vec 
	{
		public int x,y;
		vec(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	public Vector2 calculateNewPointPosition(flingType type, Point point)
	{
		vec v = globalToGrid(point.x,point.y);
		if(type==flingType.left)
		{
			if(isPositionOk(v.x-1,v.y))
			{
				return new Vector2(point.x-sectionSize,point.y);
			}
		}
		else if(type==flingType.right)
		{
			if(isPositionOk(v.x+1,v.y))
			{
				return new Vector2(point.x+sectionSize,point.y);
			}
		}
		else if(type==flingType.up)
		{
			if(isPositionOk(v.x,v.y-1))
			{
				return new Vector2(point.x,point.y+sectionSize);
			}
		}
		else if(type==flingType.down)
		{
			if(isPositionOk(v.x,v.y+1))
			{
				return new Vector2(point.x,point.y-sectionSize);
			}
		}
		return new Vector2(point.x,point.y);
	}
	private vec globalToGrid(float x, float y)
	{
		float xMul = ((size.x+1)%2)*0.5f;
		float yMul = ((size.y+1)%2)*0.5f;
		int x1= Math.round(((x-utils.WIDTH/2.f-xMul*sectionSize)/sectionSize));
		int y1 = Math.round((y-utils.HEIGHT/2.f-yMul*sectionSize)/sectionSize);
		return new vec(x1+(int)(size.x/2)+(int)(xMul*2.f)+(int)(size.x%2),-y1+(int)(size.y/2)+(int)(size.y%2));
	}
	public boolean isPositionOk(int x, int y)
	{
		boolean isOk=true;
		if(x<1||x>size.x)
		{
			isOk=false;
		}
		if(y<1||y>size.y)
		{
			isOk=false;
		}
		return isOk;
	}
	@Override
	public void render(ShapeRenderer renderer)
	{
		renderer.setColor(0.5f,0.5f,0.5f,1);
		for(int x=0;x<=size.x;x++)
		{
			float multiplier = (float)Math.ceil(size.x/2.f)-(size.x%2*0.5f);
			float muly = (float)Math.ceil(size.y/2.f)-(size.y%2*0.5f)+0.5f;
			float x1= utils.WIDTH/2.f-sectionSize*multiplier+x*sectionSize;
			renderer.rectLine(x1, utils.HEIGHT/2.f-sectionSize*muly, x1, utils.HEIGHT/2.f+sectionSize*muly, lineWidth);
		}
		for(int y=0;y<=size.y;y++)
		{
			float multiplier = (float)Math.ceil(size.y/2.f)-(size.y%2*0.5f);
			float mulx = (float)Math.ceil(size.x/2.f)-(size.x%2*0.5f)+0.5f;
			float y1= utils.HEIGHT/2.f-sectionSize*multiplier+y*sectionSize;
			renderer.rectLine( utils.WIDTH/2.f-sectionSize*mulx, y1, utils.WIDTH/2.f+sectionSize*mulx,y1, lineWidth);
		}
	}
}