package com.psci.astrea.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.psci.astrea.astrea.Astrea;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width =900;
		config.height = 640;
		config.resizable = false;
		new LwjglApplication(new Astrea(), config);
	}
}
