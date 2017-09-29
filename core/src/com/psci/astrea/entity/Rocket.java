package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.screen.GameScreen;

public class Rocket extends Player {


    protected Rocket(MySprite sprite, Vector2 position, int health, float speed, float angle) {
        super(sprite, position, health, speed, angle);
    }

}
