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
    private int size;

    private Sun(MySprite sprite, Vector2 position, int size) {
        super(sprite);
        this.position = position;
        this.size = size;
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
            sun = new Sun(starSprite, position, 1);
        }


        return sun;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }
}
