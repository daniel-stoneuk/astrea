package com.psci.astrea.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.astrea.MySprite;
import com.psci.astrea.astrea.SpriteManager;
import com.psci.astrea.entity.GameState;
import com.psci.astrea.entity.Player;
import com.psci.astrea.entity.Rocket;
import com.psci.astrea.input.GameInputProcessor;

public class GameScreen extends MyScreen {

    Astrea astrea;

    MySprite star;
    MySprite player;
    MySprite alien;
    MySprite asteroid;

    SpriteBatch spriteBatch;
    public Rectangle alienRectangle;
    public Rectangle starRectangle;
    public Rectangle asteroidRectangle;


    private GameState gameState;

    public GameScreen(Astrea astrea) {
        this.astrea = astrea;
        SpriteManager spriteManager = SpriteManager.getInstance();
        gameState = GameState.getInstance();

        spriteBatch = new SpriteBatch();

        gameState.initialize();

        star = spriteManager.getSprite("star");
        star.setPosition(200, 200);

        alien = spriteManager.getSprite("alien");
        spriteBatch = new SpriteBatch();
        alien.setPosition(50, 600);

        asteroid = spriteManager.getSprite("asteroid");
        spriteBatch = new SpriteBatch();
        asteroid.setPosition(500, 60);

        inputProcessor = new GameInputProcessor(astrea);
        alienRectangle=new Rectangle(alien.getX(),alien.getY(),alien.getWidth(),alien.getHeight());
        starRectangle=new Rectangle(star.getX(),star.getY(),star.getWidth(),star.getHeight());
        asteroidRectangle=new Rectangle(asteroid.getX(),asteroid.getY(),asteroid.getWidth(),asteroid.getHeight());


    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        gameState.render(spriteBatch);

        spriteBatch.begin();
        alienRectangle = alien.getBoundingRectangle();
        starRectangle = star.getBoundingRectangle();
        asteroidRectangle = asteroid.getBoundingRectangle();

        boolean alienisOverlaping = Player.playerRectangle.overlaps(alienRectangle);
        if(alienisOverlaping) {
            System.out.println("OVE|RLAPPIN");
        }
        boolean starisOverlaping = Player.playerRectangle.overlaps(starRectangle);
        if(starisOverlaping) {
            System.out.println("OVE|RLAPPIN");
        }
        boolean asteroidisOverlaping = Player.playerRectangle.overlaps(asteroidRectangle);
        if(asteroidisOverlaping) {
            System.out.println("OVE|RLAPPIN");
        }

        star.draw(spriteBatch);

        alien.draw(spriteBatch);
        asteroid.draw(spriteBatch);
        spriteBatch.end();


        update(delta);

    }

    private void update(float delta) {
        gameState.update(delta);
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
