package objectAcessor;

import aurelienribon.tweenengine.TweenAccessor;

import com.mygdx.game.Point;

public class PointAcessor implements TweenAccessor<Point> {
	public static final int XY=1;
	@Override
	public int getValues(Point target, int tweenType, float[] returnValues) {
		switch(tweenType)
		{
		case XY:
			returnValues[0] = target.getX();
			returnValues[1]= target.getY();
			return 2;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Point target, int tweenType, float[] newValues) {
		switch (tweenType) {
        case XY: 
            target.setPosition(newValues[0], newValues[1]);
            break;
		}
		
	}
	
}
