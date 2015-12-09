package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class WorldRenderer implements Disposable{
	
	WorldController worldController;
	private SpriteBatch batch;
	
	private FrameBuffer fbo;
	private TextureRegion waterSurface;
	
	private int level = 400;
	private int yWaterOffset;
	
	ShaderProgram waterShader;
	Texture waterMap;
	
	public WorldRenderer(WorldController worldController){
		this.worldController = worldController;
		init();
	}
	
	public void init(){
		batch = new SpriteBatch();
		
		// initialize the FBO
		fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		waterShader = new ShaderProgram(
				  Gdx.files.internal("shader/water.vert.glsl"),
				  Gdx.files.internal("shader/water.frag.glsl")
				);
		
		waterMap = new Texture(Gdx.files.internal("shader/waterDispMap.jpg"));
		waterMap.bind(1);
		Assets.instance.bgTexture.bind(0);
	}
	
	public void render(){
		renderWater();
		
		batch.begin();
		//worldController.render(batch);
		batch.draw(Assets.instance.bgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.setShader(waterShader);
		batch.draw(waterSurface, 0, -level);
		batch.setShader(null);
		batch.end();
		
	}
	
	public void renderWater(){
		fbo.begin();
		{
			batch.begin();
			batch.draw(Assets.instance.bgTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			batch.end();
		}
		fbo.end();
		
		yWaterOffset = Gdx.graphics.getHeight() - level;

		waterSurface = new TextureRegion(
		  fbo.getColorBufferTexture(), 0, (int) yWaterOffset, fbo.getColorBufferTexture().getWidth(), Gdx.graphics.getHeight()
		);
		
		
//		waterShader.begin();
//		{
//		  waterShader.setUniformi("u_texture1", 1); // enable texture buffer 1
//		  waterShader.setUniformf("u_time", 10); // the time to make the normal map slide
//		  waterShader.setUniformf("u_y_offset", yWaterOffset / Gdx.graphics.getHeight());
//		  //waterShader.setUniform4fv("u_color", waterColor, 0, 4); // water color if needed
//		 // waterShader.setUniformf("u_sparkle_intensity", sparkleIntensity); // the treshold when the wave will generate some sparkles
//		}
//		waterShader.end();
		
		
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	public void resize(int width, int height){
		
	}
	

}
