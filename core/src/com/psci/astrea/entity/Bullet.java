package com.psci.astrea.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dan Stone on 01/10/2017.
 */
public class Bullet extends Entity {

    public static final float SPEED = 2f;

    float angle;
    public float speed;

    public Bullet(MySprite sprite, Vector2 position, float angle, float speed) {
        super(sprite);
        this.position = position;
        this.angle = angle;
        this.speed = speed;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setRotation(360f - getAngle((angle)));
        sprite.draw(spriteBatch);
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

    @Override
    public void update(float delta) {
        super.update(delta);

        boolean collided = false;
        GameState state = GameState.getInstance();
        for (Alien alien : state.getAliens()) {
            collided = checkCollisionWithAlien(alien);
            if (collided) {
                alien.collidedWithPlayer();
            }
        }

        collided = false;
        for (Asteroid asteroid : state.getAsteroids()) {
            collided = checkCollisionWithAsteroid(asteroid);
            if (collided) {
                asteroid.collidedWithBullet();
                GameState.getInstance().deleteBullet(this);
            }
        }

        moveBullet();

        if (position.x >= Gdx.graphics.getWidth() || position.y >= Gdx.graphics.getHeight() || position.y <= 0 || position.x <= 0) {
            GameState.getInstance().deleteBullet(this);
        }

    }

    private boolean checkCollisionWithAlien(Alien target) {
        Rectangle minRect = sprite.getBoundingRectangle();
        return minRect.overlaps(target.getSprite().getBoundingRectangle());
    }

    private boolean checkCollisionWithAsteroid(Asteroid target) {
        Rectangle minRect = sprite.getBoundingRectangle();
        return minRect.overlaps(target.getSprite().getBoundingRectangle());
    }

    private void moveBullet() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
    }

    public static List<Bullet> createBulletSpray(Player player, String type) {
        SpriteManager handler = SpriteManager.getInstance();
        MySprite bulletSprite = handler.getSprite(type);
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        float angle = player.getAngle();
        Vector2 position = new Vector2(
                (player.getPosition().x + player.getSprite().getWidth() / 2) + (float) Math.sin(Math.toRadians(angle)) * 10 - bulletSprite.getWidth() / 2,
                (player.getPosition().y + player.getSprite().getHeight() / 2) + (float) Math.cos(Math.toRadians(angle)) * 10 - bulletSprite.getHeight() / 2);

//        bullets.add(createBullet(bulletSprite, getAngle(-90f), position, type));
//        bulletSprite = handler.getSprite(type);
        bullets.add(createBullet(bulletSprite, getAngle(angle), position, type));
        bullets.add(createBullet(bulletSprite, getAngle(10), position, type));
//        bulletSprite = handler.getSprite(type);
//        bullets.add(createBullet(bulletSprite, getAngle(90), position, type));
        return bullets;
    }

    public static Bullet createBullet(Player player, String type) {
        SpriteManager handler = SpriteManager.getInstance();
        MySprite bulletSprite = handler.getSprite(type);
        float angle = player.getAngle();
        Vector2 position = new Vector2(
                (player.getPosition().x + player.getSprite().getWidth() / 2) + (float) Math.sin(Math.toRadians(angle)) * 10 - bulletSprite.getWidth() / 2,
                (player.getPosition().y + player.getSprite().getHeight() / 2) + (float) Math.cos(Math.toRadians(angle)) * 10 - bulletSprite.getHeight() / 2);
        return createBullet(bulletSprite, angle, position, type);
    }

    public static Bullet createBullet(MySprite bulletSprite, float angle, Vector2 position, String type) {
        Bullet bullet = null;
        if (type.equals("bullet")) {
            bullet = new Bullet(bulletSprite, position, angle, SPEED * 5);
        }
        return bullet;
    }


}
