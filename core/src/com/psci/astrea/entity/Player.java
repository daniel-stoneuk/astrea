package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.screen.GameScreen;

import static com.psci.astrea.screen.GameScreen.playerSpriteRotate;

public class Player extends Entity {

    public Player(MySprite sprite) {
        super(sprite);
    }

    public static Player create() {
        MySprite playerSprite = SpriteManager.getInstance().getSprite("player");
        Player player = new Player(playerSprite);
        return player;
    }

    public void movement() {


    }

    public void rotation() {

    }

    public void collision() {


    }

    public void shoot() {
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {

    }

    public static void spriteControl(float spriteXposition, float spriteYposition, float playerSpriteRotate) {

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            GameScreen.playerSpriteYposition++;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            GameScreen.playerSpriteYposition--;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            GameScreen.playerSpriteXposition--;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            GameScreen.playerSpriteXposition++;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            GameScreen.playerSpriteRotate++;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            GameScreen.playerSpriteRotate--;

        }

    }
}
