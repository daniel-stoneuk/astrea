package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.alien.AlienImpl;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class Asteroids extends Entity {

    public Asteroids(MySprite sprite) {
        super(sprite);
    }



    public static Asteroids createAsteroid(String type) {
        Asteroids asteroid = null;

        int x = 0;
        int y = 0;
        Random rd = new Random();
        switch (rd.nextInt(3)) {

            case 0:
                y = rd.nextInt(1000);
                x = 0;

                break;
            case 1:
                x = rd.nextInt(700);
                x = 0;

                break;
            case 2:
                y = rd.nextInt(1000);
                x = 1000;

                break;
            case 3:
                x = rd.nextInt(700);
                y = 700;
                break;

        }


        SpriteManager handler = SpriteManager.getInstance();
        MySprite asteroidSprite = handler.getSprite(type);
        Vector2 position = new Vector2(x, y);

        asteroid = new Asteroids(asteroidSprite);

        return asteroid;
    }

    public void update(float delta) {


    }


    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

}
