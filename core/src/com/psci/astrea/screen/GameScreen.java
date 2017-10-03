package com.psci.astrea.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.GameState;
import com.psci.astrea.entity.Player;
import com.psci.astrea.input.GameInputProcessor;

import java.util.Random;

import static com.psci.astrea.entity.Entity.SCREEN_HEIGHT;
import static com.psci.astrea.entity.Entity.SCREEN_WIDTH;

public class GameScreen extends MyScreen {

    Astrea astrea;

//    MySprite star;
//    MySprite player;
//    MySprite alien;
//    MySprite asteroid;
    private BitmapFont font = new BitmapFont();
    public static SpriteBatch spriteBatch;


    private GameState gameState;
    public GameScreen(Astrea astrea) {
        Gdx.graphics.setTitle("Astrea");
//        Gdx.input.setCursorCatched(true);

        this.astrea = astrea;
        //SpriteManager spriteManager = SpriteManager.getInstance();
        gameState = GameState.getInstance();

        spriteBatch = new SpriteBatch();

        gameState.initialize();

        inputProcessor = new GameInputProcessor(astrea);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        gameState.render(spriteBatch);

        update(delta);

    }

    private void update(float delta) {
        FPS();
        gameState.update(delta);
    }
    private void FPS(){
        spriteBatch.begin();
        font.draw(spriteBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
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
