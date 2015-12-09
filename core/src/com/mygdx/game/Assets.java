package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
	
	public static final Assets instance = new Assets();
	
	public Texture bgTexture;
	
	public Assets(){
		init();
	}
	
	public void init(){
		bgTexture = new Texture("bg.jpg");
	}

	@Override
	public void dispose() {}

}
