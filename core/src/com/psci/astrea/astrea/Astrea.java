package com.psci.astrea.astrea;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.screen.GameScreen;
import com.psci.astrea.screen.MyScreen;

public class Astrea extends Game {

    private GameScreen gameScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen(this);

        switchScreen(gameScreen);
    }

    public void switchScreen(MyScreen screen) {
        setScreen(screen);
        Gdx.input.setInputProcessor(screen.getInputProcessor());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
