package com.mygdx.game;

import objectAcessor.MissileAcessor;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;

import com.mygdx.utils.MissileType;
import com.mygdx.utils.SpawnPosition;
import com.mygdx.utils.utils;

public class MissileTweenFactory {
	static float time = 0.4f;
	static public Timeline createTimelineForMissile(Missile missile,MissileType type,SpawnPosition position)
	{
		float mulx=1;
		float muly=1;
		switch(position)
		{
		case up:
			mulx=0;
			muly=-1;
			break;
		case down:
			mulx=0;
			muly=1;
			break;
		case left:
			mulx=1;
			muly=0;
			break;
		case right:
			mulx=-1;
			muly=0;
			break;
		}
		switch(type)
		{
		case straight:
			return Timeline.createSequence().push(Tween.to(missile, MissileAcessor.XY, time).targetRelative(utils.gridSize*mulx, utils.gridSize*muly)
					.setCallback(missile.missileTweenEnded));
		case leftRight:
			if(position==SpawnPosition.up||position==SpawnPosition.down)
			{
				return Timeline.createSequence()
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(0, utils.gridSize))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(utils.gridSize,0))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(0, utils.gridSize))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(-utils.gridSize,0)
								.setCallback(missile.missileTweenEnded));
			}
			else 
			{
				return Timeline.createSequence()
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(utils.gridSize*mulx,0))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(0,utils.gridSize))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(utils.gridSize*mulx,0))
						.push(Tween.to(missile, MissileAcessor.XY,0.6f)
								.targetRelative(0,-utils.gridSize)
								.setCallback(missile.missileTweenEnded));
			}
		}
		return null;
	}
}
