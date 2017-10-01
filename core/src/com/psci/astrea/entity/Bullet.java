package com.psci.astrea.entity;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

/**
 * Created by udeshya on 01/10/2017.
 */
public class Bullet{

    public static void spawnBullet(SpriteBatch batch, float x, float y){
        batch.begin();
        SpriteManager handler = SpriteManager.getInstance();
        MySprite bullet = handler.getSprite("bullet");
        batch.draw(bullet, x, y);
        batch.end();

    }
}
