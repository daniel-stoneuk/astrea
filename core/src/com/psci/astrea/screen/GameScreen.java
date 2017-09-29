package com.psci.astrea.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.Player;
import com.psci.astrea.entity.Star;
import com.psci.astrea.input.GameInputProcessor;

public class GameScreen extends MyScreen {

    Astrea astrea;

    MySprite star;
    MySprite player;
    MySprite alien;
    MySprite asteroid;

    SpriteBatch spriteBatch;
    public static float playerSpriteXposition = 450;
    public static float playerSpriteYposition = 320;
    public static float playerSpriteRotate;

    public GameScreen(Astrea astrea) {
        this.astrea = astrea;
        SpriteManager spriteManager = SpriteManager.getInstance();

        star = spriteManager.getSprite("star");
        spriteBatch = new SpriteBatch();
        star.setPosition(200, 200);


        player = spriteManager.getSprite("player");
        spriteBatch = new SpriteBatch();
        inputProcessor = new GameInputProcessor(astrea);

        alien = spriteManager.getSprite("alien");
        spriteBatch = new SpriteBatch();
        alien.setPosition(50, 600);
        inputProcessor = new GameInputProcessor(astrea);

        asteroid = spriteManager.getSprite("asteroid");
        spriteBatch = new SpriteBatch();
        asteroid.setPosition(500, 60);
        inputProcessor = new GameInputProcessor(astrea);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        spriteBatch.begin();
        star.draw(spriteBatch);

        player.draw(spriteBatch);
        Player.spriteControl(playerSpriteXposition, playerSpriteYposition, playerSpriteRotate);

        player.setPosition(playerSpriteXposition, playerSpriteYposition);
        player.setRotation(playerSpriteRotate);
        alien.draw(spriteBatch);
        asteroid.draw(spriteBatch);
        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
