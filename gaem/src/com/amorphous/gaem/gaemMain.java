package com.amorphous.gaem;

import Screens.GameScreen;
import Screens.MainMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.FPSLogger;


public class gaemMain extends Game{
	public static final String version = "STARSHMUP V0.6.2 Hella Progress Edition";
	public static final String LOG = "SpaceGame";
	public static final String TITLE = "";
	public static final boolean DEBUG = false;
	public Preferences saves;
	public static float VOLUME = 1f;
	public int score = 0;
	FPSLogger logger;
	public AudioManager audio;

	@Override
	public void create() {
		audio = new AudioManager();
		saves = Gdx.app.getPreferences("GaemSave");
		saves.putInteger("level", 1);
		saves.flush();
		setScreen(new MainMenu(this));
		logger = new FPSLogger();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		super.render();
		logger.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
