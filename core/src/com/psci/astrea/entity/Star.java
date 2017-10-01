package com.psci.astrea.entity;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.Sun.newsun;

public class Star extends Entity {

//    public RayHandler raay = new RayHandler(Astrea);


    public static Star create(){
        MySprite starSprite = SpriteManager.getInstance().getSprite("Star");
        Vector2 position = new Vector2(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);

        Star star = new newsun(starSprite, position);

        return star;
    }
    public void update(float delta) {
        super.update(delta);
    }

    public Star(MySprite sprite, Vector2 position) {
        super(sprite);
        this.position = position;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }
}
