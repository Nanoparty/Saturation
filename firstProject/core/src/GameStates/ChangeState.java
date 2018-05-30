package GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nathan.managers.GSM;
import com.nathan.managers.GameKeys;

public class ChangeState extends GameState{
	
	private SpriteBatch sb;
	private Texture E;
	private Texture N;
	private Texture H;

	public ChangeState(GSM gsm) {
		super(gsm);
		
	}

	
	public void init() {
		sb = new SpriteBatch();
		E = new Texture(Gdx.files.internal("easy.png"));
		N = new Texture(Gdx.files.internal("normal.png"));
		H = new Texture(Gdx.files.internal("hard.png"));
		
	}

	
	public void update(float dt) {
		handleInput();
		//setPreferences("normal");
	}

	
	public void draw() {
		
		sb.begin();
		sb.draw(E,(Gdx.graphics.getWidth()- E.getWidth())/2,Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2);
		sb.draw(N,(Gdx.graphics.getWidth()- N.getWidth())/2,Gdx.graphics.getHeight()/2);
		sb.draw(H,(Gdx.graphics.getWidth()- H.getWidth())/2,Gdx.graphics.getHeight()/4);
		sb.end();
		
	}

	
	public void handleInput() {
		
		
		if(GameKeys.isPressed(GameKeys.LEFTM)){
			
			int y = Gdx.graphics.getHeight() - GameKeys.getTouchY();
			
			//easy
			
			if(GameKeys.getTouchX() > (Gdx.graphics.getWidth()- E.getWidth())/2 && GameKeys.getTouchX() < (Gdx.graphics.getWidth()- E.getWidth())/2 + E.getWidth()){
				if(y > Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2 && y < Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2 + E.getHeight()){
					System.out.println("easy");
					setPreferences("easy");
					gsm.setState(GSM.PLAY);
				}
			}
			
			//normal
			
			if(GameKeys.getTouchX() > (Gdx.graphics.getWidth()- N.getWidth())/2 && GameKeys.getTouchX() < (Gdx.graphics.getWidth()- N.getWidth())/2 + N.getWidth()){
				if(y > Gdx.graphics.getHeight()/2 && y < Gdx.graphics.getHeight()/2 + N.getHeight()){
					System.out.println("normal");
					setPreferences("normal");
					gsm.setState(GSM.PLAY);
				}
			}
			
			//hard
			
			if(GameKeys.getTouchX() > (Gdx.graphics.getWidth()- H.getWidth())/2 && GameKeys.getTouchX() < (Gdx.graphics.getWidth()- H.getWidth())/2 + H.getWidth()){
				if(y > Gdx.graphics.getHeight()/4 && y < Gdx.graphics.getHeight()/4 + H.getHeight()){
					System.out.println("hard");
					setPreferences("hard");
					gsm.setState(GSM.PLAY);
				}
			}
			
		}
		
		
	}

	
	public void dispose() {
		
		
	}
	
	private void setPreferences(String s){
		
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		prefs.putString("difficulty",s);
		prefs.flush();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
