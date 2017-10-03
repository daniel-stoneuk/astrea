package com.psci.astrea.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psci.astrea.astrea.Astrea;

public class DesktopLauncher {

	public static int width = 1280;
	public static int height = width/16*9;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = width;
		config.height = height;
		config.resizable = false;
		config.fullscreen = true;

		new LwjglApplication(new Astrea(), config);
	}
}
