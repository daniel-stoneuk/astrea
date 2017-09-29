package com.psci.astrea.screen.helper;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class MathHelper {
	public static Vector2 PointToVector2(Point p){
		return new Vector2(p.x, p.y);
	}
	
	public static Vector2 getCenterOfSprite(Sprite sprite){
		float x = sprite.getX() - sprite.getWidth() / 2f;
		float y = sprite.getY() - sprite.getHeight() / 2f;
		return new Vector2(x,y);
	}
}
