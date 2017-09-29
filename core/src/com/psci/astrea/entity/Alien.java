package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

public class Alien extends Entity{

    public Alien(MySprite sprite) {
        super(sprite);
    }
    public static Alien create(){
        MySprite alienSprite = SpriteManager.getInstance().getSprite("alien");
        Alien alien = new Alien(alienSprite);
        return alien;
    }
    public void movement(){


    }

    public void rotation(){

    }

    public void collision(){


    }

    public void shoot(){}


    @Override
    public void draw(SpriteBatch spriteBatch) {

    }
}
