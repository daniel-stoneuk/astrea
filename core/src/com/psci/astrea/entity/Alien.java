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

    public static final float SPEED = 1f;

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
        Random random = new Random();
        SpriteManager handler = SpriteManager.getInstance();
        MySprite alienSprite = handler.getSprite(type);
        Vector2 position = new Vector2(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT));


        alien = new Alien(alienSprite, position, 0, SPEED);
        if (type.equals("alien")) {
            alien = new AlienImpl(alienSprite, position, 0, SPEED);
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

        GameState state = GameState.getInstance();
        if (state.getPlayers().size() == 1) {
            Player player = state.getPlayers().get(0);
            if (player.getPosition().x > position.x)
                position.x += SPEED;
            if (player.getPosition().x < position.x)
                position.x -= SPEED;
            if (player.getPosition().y > position.y)
                position.y += SPEED;
            if (player.getPosition().y < position.y)
                position.y -= SPEED;


            float dx = player.getPosition().x - position.x;
            float dy = player.getPosition().y - position.y;
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
            if (dy == 0)
                if (position.x > player.getPosition().x)
                    angle = 270;
                else
                    angle = 90;
            else if (dx == 0) {
                if (position.y > player.getPosition().y)
                    angle = 180;
                else
                    angle = 0;
            }
            this.angle = getAngle(angle);

        }


    }
}

