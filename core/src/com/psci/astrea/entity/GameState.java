package com.psci.astrea.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.psci.astrea.entity.alien.AlienImpl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState {

    private static GameState instance;

    private static final float ROUND_DURATION = 60;

    private static final float PRE_ROUND_WAIT_DURATION = 5;

    private int playerLife = 10;
    private int spawnedEnemies;

    private float roundTime, spawnDelay;

    private List<Player> players;
    private List<Alien> aliens;
//    private List<Projectile> projectiles;

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

    public void newRoundInitialization(){
        playerLife = 0;
        players = new ArrayList<Player>();
        players.add(Player.createPlayer("rocket",900,640));
        aliens = new ArrayList<Alien>();
        aliens.add(Alien.createAlien("alien", 900, 640));
        aliens.add(Alien.createAlien("alien", 900, 640));
        aliens.add(Alien.createAlien("alien", 900, 640));
    }

    public void update(float delta) {
        updateRoundTimer(delta);

        if (roundHasStarted) {
            for (Player player : players)
                player.update(delta);

            for (Alien alien : aliens) {
                alien.update(delta);
            }

        }

    }

    private void updateRoundTimer(float delta) {
        if (roundTime > 0) {
            roundTime -= delta;
        }
        else {
            roundHasStarted = true;
            roundTime = ROUND_DURATION;
        }
    }

    public void render(SpriteBatch spriteBatch) {
        displayPlayers(spriteBatch);
        displayAliens(spriteBatch);
    }


    private void displayPlayers(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for(Player player : players) {
            player.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private void displayAliens(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for(Alien alien : aliens) {
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
