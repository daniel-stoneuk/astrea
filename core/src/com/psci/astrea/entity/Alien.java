package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.alien.AlienImpl;
import com.psci.astrea.entity.player.Rocket;

import java.util.Random;


public abstract class Alien extends Entity{

    protected Alien(MySprite sprite, Vector2 position) {
        super(sprite);
        this.position = position;
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    public void collidedWithPlayer() {
        Random random = new Random();
        setPosition(new Vector2(random.nextInt(900), random.nextInt(640)));
    }

    public static Alien createAlien(String type, int windowWidth, int windowHeight) {
        Alien alien = null;

        Random random = new Random();

        SpriteManager handler = SpriteManager.getInstance();
        MySprite playerSprite = handler.getSprite(type);
        Vector2 position = new Vector2(random.nextInt(windowWidth), random.nextInt(windowHeight));

        if (type.equals("alien")) {
            alien = new AlienImpl(playerSprite, position);
        }
        return alien;
    }

    public void update(float delta) {
        super.update(delta);
    }
}

