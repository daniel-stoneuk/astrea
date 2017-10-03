
package com.psci.astrea.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.player.Rocket;
import com.psci.astrea.entity.Bullet;
import com.psci.astrea.screen.GameScreen;

public abstract class Player extends Entity {

    public static final float MAX_SPEED = 7f;
    public static final float SPEED_DECREASE = 0.25f;
    private static final float ROTATION_FACTOR = 3f;

    protected float angle;
    private int health;
    public float speed;
    private float cooldown;

    public Player(MySprite sprite, Vector2 position, int health, float speed, float angle) {
        super(sprite);
        this.health = health;
        this.speed = speed;
        this.angle = angle;
        this.position = position;
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

    public void rotateLeft() {
        angle = getAngle(angle - ROTATION_FACTOR);
    }

    public void rotateRight() {
        angle = getAngle(angle + ROTATION_FACTOR);
    }

    public void moveForwards() {
        speed = speed + 1f;
        if (speed > MAX_SPEED) speed = MAX_SPEED;
    }

    public void moveBackwards() {
        speed = speed - 1f;
        if (speed < -MAX_SPEED) speed = -MAX_SPEED;
    }

    public static Player createPlayer(String type) {
        Player player = null;

        SpriteManager handler = SpriteManager.getInstance();
        MySprite playerSprite = handler.getSprite(type);
        Vector2 position = new Vector2(200, 200);

        int health = 100;
        float speed = 0f;
        float angle = 0f;

        if (type.equals("rocket")) {
            player = new Rocket(playerSprite, position, health, speed, angle);
        }
        return player;
    }

    public void update(float delta) {
        super.update(delta);
        cooldown = cooldown - delta;
        if (speed <= -SPEED_DECREASE) {
            speed += SPEED_DECREASE;
        } else if (speed >= SPEED_DECREASE) {
            speed -= SPEED_DECREASE;
        } else {
            speed = 0f;
        }
        boolean collided = false;
        GameState state = GameState.getInstance();
        for (Alien alien : state.getAliens()) {
            collided = checkCollisionWithAlien(alien);
            if (collided) {
                alien.collidedWithPlayer();
            }
        }

        collided = checkCollisionWithSun(state.getSun());
        if (collided) {
            this.speed = -speed - 1f;
        }

        if (!collided) {
            control();
        }
        movePlayer();

    }

    private boolean checkCollisionWithSun(Sun target) {
        Circle circleSun = new Circle(target.getPosition().x + target.getWidth() / 2, target.getPosition().y + target.getHeight() / 2, target.getWidth() / 2);
        Circle circle = new Circle(getPosition().x + getSprite().getWidth() / 2, getPosition().y + getSprite().getHeight() / 2, getSprite().getWidth() / 2);
        return circle.overlaps(circleSun);
    }

    private boolean checkCollisionWithAlien(Alien target) {
        Rectangle minRect = sprite.getBoundingRectangle();
        return minRect.overlaps(target.getSprite().getBoundingRectangle());
    }


    private void movePlayer() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
        // Check if rocket is out of frame
        if (position.x >= Gdx.graphics.getWidth()) {
            position.x = 0;
        } else if (position.y >= Gdx.graphics.getHeight()) {
            position.y = 0;
        } else if (position.y <= 0) {
            position.y = Gdx.graphics.getHeight();
        } else if (position.x <= 0) {
            position.x = Gdx.graphics.getWidth();
        }
    }

    private void control() {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveForwards();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            moveBackwards();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            rotateLeft();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            rotateRight();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (cooldown <= 0)
                shoot();
        }
    }

    public void shoot() {
        cooldown = 0.2f;
        GameState state = GameState.getInstance();
        state.shootBullet(this);
    }


    public float getAngle() {
        return this.angle;
    }
}

