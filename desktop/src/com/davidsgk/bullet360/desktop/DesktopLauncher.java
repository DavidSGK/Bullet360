package com.davidsgk.bullet360.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.davidsgk.bullet360.Bullet360;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Bullet360.WIDTH;
        config.height = Bullet360.HEIGHT;
		new LwjglApplication(new Bullet360(), config);
	}
}
