 package com.amorphous.gaem;

import Screens.MainMenu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "StarShmup Alpha"+gaemMain.version;
		cfg.useGL20 = true;
		//res, obviously  
		cfg.width = 1000; //1000
		cfg.height = (int) 900; //900
		cfg.vSyncEnabled = false;
		cfg.resizable = false;
		cfg.foregroundFPS = 60;
		cfg.fullscreen = false;
		
		new LwjglApplication(new gaemMain(), cfg);
	}
}
