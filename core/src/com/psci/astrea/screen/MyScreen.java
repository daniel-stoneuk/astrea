package com.psci.astrea.screen;

import com.badlogic.gdx.Screen;
import com.psci.astrea.input.MyInputProcessor;

public abstract class MyScreen implements Screen {

    protected MyInputProcessor inputProcessor;

    public MyInputProcessor getInputProcessor() {
        return inputProcessor;
    }
}
