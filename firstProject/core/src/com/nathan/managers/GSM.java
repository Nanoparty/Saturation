package com.nathan.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.nathan.firstproject.Game;

import GameStates.ChangeState;
import GameStates.GameState;
import GameStates.MenuState;
import GameStates.PlayState;

public class GSM {
	
	private GameState gameState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int CHANGE = 2;
	
	public Game game;
	
	private Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
	
	public GSM(Game game){
		this.game = game;
		setState(PLAY);
		music.setVolume(.5f);
		music.play();
		music.setLooping(true);
		
		
	}
	
	public void setState(int state){
		if(gameState!=null)gameState.dispose();
		if(state == MENU){
			gameState = new MenuState(this);
		}
		if(state == PLAY){
			gameState= new PlayState(this);
		}
		if(state == CHANGE){
			gameState = new ChangeState(this);
		}
	}
	
	public void update(float dt){
		gameState.update(dt);
		
	}
	
	public void draw(){
		gameState.draw();
	}
	public Game getGame(){return game;}

}
