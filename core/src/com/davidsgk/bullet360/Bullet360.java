package com.davidsgk.bullet360;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.davidsgk.bullet360.screen.GameScreen;
import com.davidsgk.bullet360.screen.ScreenManager;

public class Bullet360 extends ApplicationAdapter {

	public static int WIDTH = 1920, HEIGHT = 1080;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        ScreenManager.setScreen(new GameScreen());
	}

    public void dispose(){
        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().dispose();
        batch.dispose();
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().update();

        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().render(batch);
	}

    public void resize(int width, int height){
        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resize(width, height);
    }

    public void pause(){
        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().pause();
    }

    public void resume(){
        if(ScreenManager.getCurrentScreen() != null)
            ScreenManager.getCurrentScreen().resume();
    }
}
