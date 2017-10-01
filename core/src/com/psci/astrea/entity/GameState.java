package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.screen.helper.TimeHelper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class GameState {

    private static GameState instance;

    private static final float ROUND_DURATION = 60;

    private static final float PRE_ROUND_WAIT_DURATION = 5;

    private BitmapFont font = new BitmapFont(); //or use alex answer to use custom font

    private float roundTime;

    private List<Player> players;
    private Sun sun;
    private List<Alien> aliens;

    private boolean roundHasStarted;

    public static GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }

    /**
     * Game state cannot be instantiated outside of the class. To get a reference to this object, call the static method getInstance().
     */
    private GameState() {
        instance = this;
    }

    public void initialize() {
        newRoundInitialization();
        roundTime = PRE_ROUND_WAIT_DURATION;
    }

    public void newRoundInitialization() {
        players = new ArrayList<Player>();
        players.add(Player.createPlayer("rocket"));
        aliens = new ArrayList<Alien>();
        aliens.add(Alien.createAlien("alien"));
        aliens.add(Alien.createAlien("alien"));
        aliens.add(Alien.createAlien("alien"));
        sun = Sun.create("sun");
    }

    public void update(float delta) {
        updateRoundTimer(delta);

        if (roundHasStarted) {
            for (Player player : players)
                player.update(delta);

            for (Alien alien : aliens) {
                alien.update(delta);
            }
            if (sun != null) {
                sun.update(delta);
            }

        }

    }

    private void updateRoundTimer(float delta) {
        if (roundTime > 0) {
            roundTime -= delta;
        } else {
            roundHasStarted = true;
            roundTime = ROUND_DURATION;
        }
    }

    public void render(SpriteBatch spriteBatch) {
        if (roundHasStarted) {
            displayAliens(spriteBatch);
            displaySun(spriteBatch);
            displayPlayers(spriteBatch);
        } else {
            spriteBatch.begin();
            font.draw(spriteBatch, TimeHelper.formatSeconds(roundTime), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
            spriteBatch.end();
        }
    }


    private void displayPlayers(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Player player : players) {
            player.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private void displaySun(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        if (sun != null) {
            sun.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private void displayAliens(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Alien alien : aliens) {
            alien.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Alien> getAliens() {
        return aliens;
    }
}
