package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
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
                x = 0;
                y = rd.nextInt(SCREEN_HEIGHT);
                break;
            case 1:
                x = rd.nextInt(SCREEN_WIDTH);
                y = 0;
                break;
            case 2:
                x = SCREEN_WIDTH;
                y = rd.nextInt(SCREEN_HEIGHT);
                break;
            case 3:
                x = rd.nextInt(SCREEN_WIDTH);
                y = SCREEN_HEIGHT;
                break;

        }


        SpriteManager handler = SpriteManager.getInstance();
        MySprite asteroidSprite = handler.getSprite(type);
        Vector2 thePosition = new Vector2(x, y);
        Vector2 position = new Vector2(x, y);
        Vector2 center = new Vector2(SCREEN_WIDTH / 2,SCREEN_HEIGHT / 2);

        float dx = center.x - x;
        float dy = center.y - y;
        float angle = (float) Math.toDegrees(Math.atan2(Math.abs(dy),Math.abs(dx)));
        if (dx > 0 && dy < 0) {
            angle += 90;
        } else if (dx > 0 && dy > 0) {
            angle = 90 - angle;
        } else if (dx < 0 && dy < 0) {
            angle = 270 - angle;
        } else if (dx < 0 && dy > 0){
            angle += 270;
        }
        angle = getAngle(angle);


        asteroid = new Asteroid(asteroidSprite,thePosition, angle , SPEED);

        return asteroid;
    }

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

    public void update(float delta) {
        super.update(delta);

        boolean collision = checkCollisionWithSun(GameState.getInstance().getSun());

        if (collision) {
            GameState.getInstance().deleteAsteroid(this);
        }

        moveAsteroid();
    }

    private boolean checkCollisionWithSun(Sun target) {
        Circle circleSun = new Circle(target.getPosition().x + target.getWidth() / 2, target.getPosition().y + target.getHeight() / 2, target.getWidth() / 2 - getSprite().getWidth());
        Circle circle = new Circle(getPosition().x + getSprite().getWidth() / 2, getPosition().y + getSprite().getHeight() / 2, getSprite().getWidth() / 2);
        return circle.overlaps(circleSun);
    }

    private void moveAsteroid() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
    }

    public void collidedWithBullet() {
        GameState.getInstance().deleteAsteroid(this);
    }


}
