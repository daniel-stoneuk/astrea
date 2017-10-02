package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

import java.util.Random;

/**
 * Sun class will only ever exist as one object type.
 */
public class Sun extends Entity {

    // Percentage
    private int hits;

    private Sun(MySprite sprite, Vector2 position) {
        super(sprite);
        this.position = position;
    }


    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    public static float collidedWithPlayer(float speed) {

        return speed;
    }
    public static Sun create(String type){
        Sun sun = null;
        MySprite starSprite = SpriteManager.getInstance().getSprite(type);
        Vector2 position = new Vector2((SCREEN_WIDTH / 2) - starSprite.getWidth() / 2, (SCREEN_HEIGHT / 2) - starSprite.getHeight() / 2);

        if (type.equals("sun")) {
            sun = new Sun(starSprite, position);
        }


        return sun;
    }

    public void incrementHits() {
        hits += 1;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }
}
