package GameStates;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.nathan.managers.GSM;
import com.nathan.managers.GameKeys;

import entities.Background;

public class PlayState extends GameState{
	
	private ShapeRenderer sr;
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture S;
	private Texture title;
	private Texture text;
	private Color c;
	private Sound select = Gdx.audio.newSound(Gdx.files.internal("select.wav"));
	private Background bg;
	
	public PlayState(GSM gsm){
		super(gsm);
	}
	
	public void init(){
		
		//FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("blocked.ttf"));
		batch = new SpriteBatch();
		font = new BitmapFont();
		c = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
		bg = new Background();
		sr = new ShapeRenderer();
		title = new Texture(Gdx.files.internal("title.png"));
		text = new Texture(Gdx.files.internal("play.png"));
		S = new Texture(Gdx.files.internal("setting.png"));
	}
	
	public void update(float dt){
		handleInput();
		//tick++;
		bg.update();
	}
	
	public void draw(){
		
		sr.begin(ShapeType.Filled);
		sr.setColor(c);
		sr.rect((float)((Gdx.graphics.getWidth() - title.getWidth() ) / 2), (float)(Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2),title.getWidth(),title.getHeight());
		sr.end();
		
		batch.begin();
		batch.draw(title, (Gdx.graphics.getWidth() - title.getWidth() ) / 2, Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2);
		batch.draw(text,(Gdx.graphics.getWidth()-text.getWidth())/2,Gdx.graphics.getHeight()/4);
		
		batch.draw(S,Gdx.graphics.getWidth()-S.getWidth(),Gdx.graphics.getHeight() - S.getHeight());
		
		//font.draw(batch,"Touch Screen to Begin", (Gdx.graphics.getWidth()-length)/2,Gdx.graphics.getHeight()/2);
		batch.end();
	
		
		
		
		
	}
	
	public void handleInput(){
		
		
		
		if(GameKeys.isPressed(GameKeys.LEFTM)){
			
			if(GameKeys.getTouchX() > Gdx.graphics.getWidth()-S.getWidth() && GameKeys.getTouchX() < Gdx.graphics.getWidth() && GameKeys.getTouchY() > 0 && GameKeys.getTouchY() < S.getHeight()){
				
					gsm.setState(GSM.CHANGE);
				
			}else{
			
			select.play();
			gsm.setState(GSM.MENU);
		}
		}
		
	}
	
	public void dispose(){
		batch.dispose();
		//select.dispose();
		font.dispose();
		
	}

}















