package com.psci.astrea.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.Star;
import com.psci.astrea.input.GameInputProcessor;

public class GameScreen extends MyScreen {

    Astrea astrea;

    MySprite star;

    SpriteBatch spriteBatch;


    public GameScreen(Astrea astrea) {
        this.astrea = astrea;
        SpriteManager spriteManager = SpriteManager.getInstance();

        star = spriteManager.getSprite("star");
        spriteBatch = new SpriteBatch();
        star.setPosition(200,200);

        inputProcessor = new GameInputProcessor(astrea);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));
        spriteBatch.begin();
        star.draw(spriteBatch);
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
