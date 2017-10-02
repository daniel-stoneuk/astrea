package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class Asteroid extends Entity {

    public static final float SPEED = 8;

    public float angle;
    private float speed;

    public Asteroid(MySprite sprite, Vector2 position, float angle, float speed) {
        super(sprite);
        this.position = position;
        this.angle = angle;
        this.speed = speed;
    }

    public static Asteroid createAsteroid(String type) {
        Asteroid asteroid = null;

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
                y = 0;

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
        Vector2 thePosition = new Vector2(x, y);
        Vector2 position = new Vector2(x, y);
        Vector2 center = new Vector2(SCREEN_WIDTH / 2,SCREEN_HEIGHT / 2);

        Vector2 v1 = position.cpy().sub(center);
        Vector2 v2 = position.cpy().sub(center);
        float angle1 = v1.angle();
        float angle2 = v2.angle();

        float angleBetween = Math.abs(angle1 - angle2);
//If the angle is more then 180 then comparing the other way around would be shorter.
        if (angleBetween > 180)
            angleBetween = 360 - angleBetween;


        float dx = x - SCREEN_WIDTH / 2;
        float dy = y - SCREEN_HEIGHT / 2;
        System.out.println(position.x + " " + position.y);
        System.out.println(position.angle(center));
        float angle = (float) Math.toDegrees(Math.atan2(dy,dx));
        System.out.println(angle);

        position.sub(center);

        asteroid = new Asteroid(asteroidSprite,thePosition, (float) angleBetween, SPEED);

        return asteroid;
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.setRotation(360f - getAngle((angle)));
        sprite.draw(spriteBatch);
    }

    private float getAngle(float a) {
        while (a < 0) {
            a += 360f;
        }
        while (a > 360) {
            a -= 360f;
        }
        return a;
    }


    public void update(float delta) {
        super.update(delta);
        moveAsteroid();
    }

    private void moveAsteroid() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
    }


}
