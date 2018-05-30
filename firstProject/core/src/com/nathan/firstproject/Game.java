package com.nathan.firstproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nathan.managers.GSM;
import com.nathan.managers.GameInputProcessor;
import com.nathan.managers.GameKeys;

public class Game extends ApplicationAdapter {
	
	public static int WIDTH;
	public static int HEIGHT;
	
	public static OrthographicCamera cam;
	
	private GSM gsm;
	
	@Override
	public void create () {
		
		
		
		WIDTH = 1280;
		HEIGHT = 720;
		
		cam = new OrthographicCamera(WIDTH,HEIGHT);
		cam.translate(WIDTH/2, HEIGHT/2);
		cam.update();
		
		Gdx.input.setInputProcessor(new GameInputProcessor());
		
		gsm = new GSM(this);
		
	}
	
	public void dispose(){}
	
	public void pause(){}
	
	public void resume(){}
	
	public void resize(int width,int height){}

	@Override
	public void render () {
		
		//clear screen black
		Gdx.gl.glClearColor(0,0 ,0 , 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();
		
		
		
		GameKeys.update();
		
		
		
	}
	public OrthographicCamera getCam(){return cam;}
}
