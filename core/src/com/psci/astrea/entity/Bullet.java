package com.psci.astrea.entity;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.player.Rocket;

import java.util.Iterator;

/**
 * Created by Dan Stone on 01/10/2017.
 */
public class Bullet extends Entity {

    public static final float SPEED = 18f;

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

    private float getAngle(float a) {
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

        moveBullet();

        Iterator<Bullet> iter = GameState.getInstance().getBullets().iterator();

        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            if (bullet == this) {
                if (position.x >= Gdx.graphics.getWidth() || position.y >= Gdx.graphics.getHeight() || position.y <= 0 || position.x <= 0) {
                    iter.remove();
                }
            }
        }
    }

    private boolean checkCollisionWithAlien(Alien target) {
        Rectangle minRect = sprite.getBoundingRectangle();
        return minRect.overlaps(target.getSprite().getBoundingRectangle());
    }


    private void moveBullet() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
    }

    public static Bullet createBullet(Player player, String type) {
        Bullet bullet = null;

        SpriteManager handler = SpriteManager.getInstance();
        MySprite bulletSprite = handler.getSprite(type);
        float angle = player.getAngle();
        Vector2 position = new Vector2(
                (player.getPosition().x + player.getSprite().getWidth() / 2) + (float) Math.sin(Math.toRadians(angle)) * 10 - bulletSprite.getWidth() / 2,
                (player.getPosition().y + player.getSprite().getHeight() / 2) + (float) Math.cos(Math.toRadians(angle)) * 10 - bulletSprite.getHeight() / 2);


        if (type.equals("bullet")) {
            bullet = new Bullet(bulletSprite, position, angle, SPEED);
        }
        return bullet;
    }


}
