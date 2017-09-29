package com.psci.astrea.input;

import com.badlogic.gdx.Input;
import com.psci.astrea.astrea.Astrea;
import com.psci.astrea.entity.GameState;
import com.psci.astrea.entity.Player;
import com.psci.astrea.screen.GameUserInterface;

import java.util.List;

public class GameInputProcessor extends MyInputProcessor {

    private GameUserInterface gameInterface;

    public GameInputProcessor(Astrea astrea) {
        super(astrea);
        gameInterface = new GameUserInterface();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE: {
                break;
            }
            case Input.Keys.P: {
                break;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
