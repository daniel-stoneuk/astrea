package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.alien.AlienImpl;
import com.psci.astrea.entity.player.Rocket;

import java.util.Random;

import static com.psci.astrea.entity.Asteroid.SPEED;


public class Alien extends Entity {
    public float angle;
    private float speed;
    protected Alien(MySprite sprite, Vector2 position, float angle, float speed) {
        super(sprite);
        this.position = position;
        this.angle = angle;
        this.speed = speed;
    }


    public void draw(SpriteBatch spriteBatch) {
        sprite.setRotation(360f - getAngle((angle)));
        sprite.draw(spriteBatch);
    }

    public void collidedWithPlayer() {
        Random random = new Random();
        setPosition(new Vector2(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
    }

    public static Alien createAlien(String type) {
        Alien alien = null;
        float x = Player.getPosition().x;
        float y = Player.getPosition().y;
        Random random = new Random();
        SpriteManager handler = SpriteManager.getInstance();
        MySprite alienSprite = handler.getSprite(type);
        Vector2 position = new Vector2(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT));
        Vector2 thePosition = new Vector2(x, y);
        Vector2 center = new Vector2(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);




        float dx = center.x - x;
        float dy = center.y - y;
        float angle = (float) Math.toDegrees(Math.atan2(Math.abs(dy), Math.abs(dx)));
        if (dx > 0 && dy < 0) {
            angle += 90;
        } else if (dx > 0 && dy > 0) {
            angle = 90 - angle;
        } else if (dx < 0 && dy < 0) {
            angle = 270 - angle;
        } else if (dx < 0 && dy > 0) {
            angle += 270;
        }
        angle = getAngle(angle);


        alien = new Alien(alienSprite, thePosition, angle, SPEED);
        if (type.equals("alien")) {
            alien = new AlienImpl(alienSprite, position, angle, SPEED);
        }
        return alien;
    }


    private static float getAngle(float a) {
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
    }
}

