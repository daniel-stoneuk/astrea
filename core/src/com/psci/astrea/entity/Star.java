package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.star.Sun;

public abstract class Star extends Entity {

//    public RayHandler raay = new RayHandler(Astrea);

    // Percentage
    protected int size;

    protected Star(MySprite sprite, Vector2 position, int size) {
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


    public static Star create(){
        MySprite starSprite = SpriteManager.getInstance().getSprite("star");
        Vector2 position = new Vector2((SCREEN_WIDTH / 2) - starSprite.getWidth() / 2, (SCREEN_HEIGHT / 2) - starSprite.getHeight() / 2);

        Star star = new Sun(starSprite, position, 1);

        return star;
    }
}
