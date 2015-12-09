package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {
	

	public WorldController(){
		init();
	}
	
	public void init(){
		Gdx.input.setInputProcessor(this);
	}
	
	public void update(float deltaTime){
		
	}
	
	public void render(){
		
	}
}
