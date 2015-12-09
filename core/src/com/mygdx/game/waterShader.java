package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class waterShader extends ApplicationAdapter {
	
	private static final String TAG = waterShader.class.getName();
	
	private WorldRenderer worldRenderer;
	private WorldController worldController;
	
	private boolean paused;
	
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		
		Assets.instance.init();
		
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		
		paused = false;
	}
	
	@Override
	public void render () {
		if(!paused){
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		worldRenderer.render();
	}
	
	@Override
	public void resize(int width, int height){
		worldRenderer.resize(width, height);
	}
	
	@Override
	public void pause(){
		paused = true;
	}
	
	@Override
	public void resume(){
		paused = false;
	}
	
	@Override
	public void dispose(){
		worldRenderer.dispose();
	}
}
