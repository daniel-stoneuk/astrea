package com.psci.astrea.entity;

public class GameState {

    public static GameState instance;

    public static final int LEVEL_DURATION = 60;

    private int level;
    private int score = 0;
    private int sunSize = 0;

    public static GameState getInstance() {
        if (instance == null)
            instance = new GameState();
        return instance;
    }


}
