package com.psci.astrea.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psci.astrea.astrea.Astrea;

public class DesktopLauncher {

	public static int width = 900;
	public static int height = 640;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = width;
		config.height = height;
		config.resizable = false;
		new LwjglApplication(new Astrea(), config);
	}
}
