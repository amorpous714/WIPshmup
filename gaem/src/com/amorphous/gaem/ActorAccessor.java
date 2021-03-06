package com.amorphous.gaem;

import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ActorAccessor implements TweenAccessor<Actor> {

	public static final int ALPHA = 0;
	public static final int Y = 1;
	
	
	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		switch(tweenType){
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		case Y:
			returnValues[0] = target.getY();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues) {
		switch(tweenType){
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
			break;
		case Y:
			target.setY(newValues[0]);
			break;
		default:
			assert false;
		}
	}

}
