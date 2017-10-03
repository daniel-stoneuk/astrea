package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;

public abstract class Entity {
    public static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    protected static Vector2 position;
    protected MySprite sprite;
    protected boolean active;

    public Entity(MySprite sprite){
        this.sprite = sprite;
        this.active = false;
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public void update(float delta){
        sprite.setX(this.position.x);
        sprite.setY(this.position.y);
    }


    public MySprite getSprite() {
        return sprite;
    }

    public void setSprite(MySprite sprite) {
        this.sprite = sprite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPosition(Vector2 position){
        this.position = position;
        sprite.setX(position.x);
        sprite.setY(position.y);
    }


    public static Vector2 getPosition() {
        return position;
    }

}
