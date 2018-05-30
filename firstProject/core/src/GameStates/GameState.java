package GameStates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nathan.firstproject.Game;
import com.nathan.managers.GSM;

public abstract class GameState {
	
	protected GSM gsm;
	protected OrthographicCamera cam;
	protected Game game;
	
	protected GameState(GSM gsm){
		this.gsm = gsm;
		game = gsm.getGame();
		cam = game.getCam();
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	public abstract void handleInput();
	public abstract void dispose();

}
