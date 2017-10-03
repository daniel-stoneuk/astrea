package com.psci.astrea.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.psci.astrea.screen.helper.TimeHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {

    private static GameState instance;

    private static final float ROUND_DURATION = 60;

    private static final float PRE_ROUND_WAIT_DURATION = 2;

    Random random = new Random();

    private BitmapFont font = new BitmapFont(); //or use alex answer to use custom font
    private float hitCountWidth = 0;

    private float roundTime;


    private List<Player> players;

    private Sun sun;

    private List<Alien> aliens;

    private List<Bullet> bullets;
    private List<Bullet> bulletsDeleteQueue;

    private List<Asteroid> asteroids;
    private List<Asteroid> asteroidsDeleteQueue;


    private boolean roundHasStarted;

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
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

        bullets = new ArrayList<Bullet>();
        bulletsDeleteQueue = new ArrayList<Bullet>();

        sun = Sun.create("sun");

        asteroids = new ArrayList<Asteroid>();
        asteroidsDeleteQueue = new ArrayList<Asteroid>();

        GlyphLayout layout = new GlyphLayout(); //dont do this every frame! Store it as member
        layout.setText(font, "Asteroids Missed: 123");
        hitCountWidth = layout.width;
    }

    float timeUntilAsteroid = 0;

    public void update(float delta) {
//        System.out.println(" ----- GAME STATE ----- ");
//        System.out.println("Bullet delete queue size: " + bulletsDeleteQueue.size());
//        System.out.println("Bullet count: " + bullets.size());
//        System.out.println("Asteroid count: " + asteroids.size());

        updateRoundTimer(delta);

        if (roundHasStarted) {

            checkDeleteQueue();

            timeUntilAsteroid -= delta;
            if (timeUntilAsteroid <= 0) {
                asteroids.add(Asteroid.createAsteroid("asteroid"));
                timeUntilAsteroid = 2;
                System.out.println("Asteroid created");
            }

            for (Player player : players)
                player.update(delta);

            for (Alien alien : aliens) {
                alien.update(delta);
            }
            if (sun != null) {
                sun.update(delta);
            }

            for (Bullet bullet : bullets) {
                bullet.update(delta);
            }

            for (Asteroid asteroids : asteroids)
                asteroids.update(delta);
        }
    }

    private void checkDeleteQueue() {
        bullets.removeAll(bulletsDeleteQueue);
        bulletsDeleteQueue.clear();

        asteroids.removeAll(asteroidsDeleteQueue);
        asteroidsDeleteQueue.clear();
    }

    public void deleteAsteroid(Asteroid asteroid) {
        asteroidsDeleteQueue.add(asteroid);
    }

    public void deleteBullet(Bullet bullet) {
        bulletsDeleteQueue.add(bullet);
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
            displayAsteroids(spriteBatch);
            displaySun(spriteBatch);
            displayBullets(spriteBatch);
            displayPlayers(spriteBatch);
            spriteBatch.begin();
            font.draw(spriteBatch, "Asteroids Missed: " + sun.getHits(), Gdx.graphics.getWidth() - hitCountWidth, Gdx.graphics.getHeight());
            spriteBatch.end();
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

    private void displayBullets(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Bullet bullet : bullets) {
            bullet.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    private void displayAsteroids(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(spriteBatch);
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

    public void shootBullet(Player player) {
        if (random.nextInt(3) == 1 || true) {
            List<Bullet> bullet = Bullet.createBulletSpray(player, "bullet");
            for (Bullet bullet1 : bullet) {
                bullets.add(bullet1);
            }
        } else {
            bullets.add(Bullet.createBullet(player, "bullet"));
        }
    }


    public List<Player> getPlayers() {
        return players;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public List<Alien> getAliens() {
        return aliens;
    }

    public Sun getSun() {
        return sun;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Random getRandom() {
        return random;
    }
}
