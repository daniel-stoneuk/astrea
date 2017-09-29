package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

public abstract class Player extends Entity {

    public static final float MAX_SPEED = 6f;
    public static final float SPEED_DECREASE = 0.25f;

    protected float angle;
    private int health;
    protected float speed;

    protected Player(MySprite sprite, Vector2 position, int health, float speed, float angle) {
        super(sprite);
        this.health = health;
        this.speed = speed;
        this.angle = angle;
        this.position = position;
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.setOriginCenter();
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
        angle = getAngle(angle - 2.5f);
    }

    public void rotateRight() {
        angle = getAngle(angle + 2.5f);
    }

    public void moveForwards() {
        speed = speed + 1f;
        if (speed > MAX_SPEED) speed = MAX_SPEED;
    }

    public void moveBackwards() {
        speed = speed - 1f;
        if (speed < -MAX_SPEED) speed = -MAX_SPEED;
    }

    public static Player createPlayer(String type, int windowWidth, int windowHeight) {
        Player player = null;

        SpriteManager handler = SpriteManager.getInstance();
        MySprite playerSprite = handler.getSprite(type);
        Vector2 position = new Vector2(windowWidth / 2, windowHeight / 2);

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
        if (speed <= -SPEED_DECREASE) {
            speed += SPEED_DECREASE;
        } else if (speed >= SPEED_DECREASE){
            speed -= SPEED_DECREASE;
        } else {
            speed = 0f;
        }
        control();
        movePlayer();
    }

    private void movePlayer() {
        position.x = position.x + (int) (Math.sin(Math.toRadians(angle)) * speed);
        position.y = position.y + (int) (Math.cos(Math.toRadians(angle)) * speed);
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

    }
}

