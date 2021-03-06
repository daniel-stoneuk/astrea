
package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Rectangle;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.player.Rocket;

public abstract class Player extends Entity {

    public static final float MAX_SPEED = 8f;
    public static final float SPEED_DECREASE = 0.2f;
    public static final float SPEED_INCREASE = 1f;

    public static final float ROTATION_MAX_SPEED = 4f;
    public static final float ROTATION_SPEED_DECREASE = 0.15f;
    public static final float ROTATION_SPEED_INCREASE = 0.35f;

    protected float angle;
    private int health;
    public float speed;
    public float rotationSpeed;
    private float cooldown;

    public Player(MySprite sprite, Vector2 position, int health, float speed, float angle) {
        super(sprite);
        this.health = health;
        this.speed = speed;
        this.rotationSpeed = speed;
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
        rotationSpeed = rotationSpeed - ROTATION_SPEED_INCREASE;
        if (rotationSpeed < -ROTATION_MAX_SPEED) rotationSpeed = -ROTATION_MAX_SPEED;
    }

    public void rotateRight() {
        rotationSpeed = rotationSpeed + ROTATION_SPEED_INCREASE;
        if (rotationSpeed > ROTATION_MAX_SPEED) rotationSpeed = ROTATION_MAX_SPEED;
    }

    public void moveForwards() {
        speed = speed + SPEED_INCREASE;
        if (speed > MAX_SPEED) speed = MAX_SPEED;
    }

    public void moveBackwards() {
        speed = speed - SPEED_INCREASE;
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
        speed = decreaseSpeed(speed, SPEED_DECREASE);
        rotationSpeed = decreaseRotationSpeed(rotationSpeed, ROTATION_SPEED_DECREASE);

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
        angle = getAngle(angle + rotationSpeed);
    }

    private float decreaseSpeed(float speed, float speedDecrease) {
        if (speed <= -speedDecrease) {
            speed += speedDecrease;
        } else if (speed >= speedDecrease) {
            speed -= speedDecrease;
        } else {
            speed = 0f;
        }
        return speed;
    }

    private float decreaseRotationSpeed(float speed, float speedDecrease) {
        if (speed <= -speedDecrease) {
            speed += speedDecrease;
        } else if (speed >= speedDecrease) {
            speed -= speedDecrease;
        } else {
            speed = 0f;
        }
        return speed;
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
//            moveBackwards();
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
        if (speed != 0) {
            cooldown = 0.2f;
        } else {
            cooldown = .4f;
        }
        GameState state = GameState.getInstance();
        state.shootBullet(this);
    }


    public float getAngle() {
        return this.angle;
    }
}

