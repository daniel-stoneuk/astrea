package com.psci.astrea.input;

import com.badlogic.gdx.InputProcessor;
import com.psci.astrea.astrea.Astrea;

public abstract class MyInputProcessor implements InputProcessor {

    protected Astrea astrea;

    public MyInputProcessor(Astrea astrea) {
        this.astrea = astrea;

    }
}
