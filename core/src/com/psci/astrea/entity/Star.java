package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;

public class Star extends Entity {

    public static Star create(){
        MySprite starSprite = SpriteManager.getInstance().getSprite("star");
        Star star = new Star(starSprite);
        return star;
    }

    public Star(MySprite sprite) {
        super(sprite);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }
}
