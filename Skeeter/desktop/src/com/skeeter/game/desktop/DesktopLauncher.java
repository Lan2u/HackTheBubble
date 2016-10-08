package com.skeeter.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.skeeter.game.Skeeter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800; // Application width
        config.height = 450; // Application height
        config.resizable = false;
        // 800 by 450 pixels
		new LwjglApplication(new Skeeter(), config);
	}
}
