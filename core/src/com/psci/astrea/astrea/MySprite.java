package com.psci.astrea.astrea;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class MySprite extends Sprite {


    public MySprite(Texture texture) {
        super(texture);
    }

    public Point getPosition(){
        return new Point((int)getX(), (int)getY());
    }

    public boolean contains(Vector2 point)
    {
        return getBoundingRectangle().contains(point);
    }

    public boolean contains(int x, int y) {
        return getBoundingRectangle().contains(x, y);
    }

    public MySprite clone(){
        MySprite clone = new MySprite(getTexture());
        clone.set(this);
        return clone;
    }

}
