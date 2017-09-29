package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

public class Asteroids extends Entity{

    public Asteroids(MySprite sprite) {
        super(sprite);
    }
    public static Asteroids create(){
        MySprite asteroidSprite = SpriteManager.getInstance().getSprite("asteroid");
        Asteroids asteroid = new Asteroids(asteroidSprite);
        return asteroid;
    }

    public void movement(){


    }

    public void rotation(){

    }

    public void collision(){


    }

    public void gravity(){}

    public void image(){}
    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

}
