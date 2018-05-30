package GameStates;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.nathan.managers.GSM;
import com.nathan.managers.GameKeys;

import entities.Background;
import entities.Player;
import entities.Tail;
import entities.Wall;

public class MenuState extends GameState{
	
	private Player player;
	private boolean bt;
	private Background bg;
	
	FileHandle file = Gdx.files.internal("data.txt");
	
	private Texture S;
	
	private int high;
	
	private Texture GO;
	private Texture YS;
	private Texture HS;
	private Texture N;
	private Texture P;
	
	private Texture tex;

	
	private Sound select = Gdx.audio.newSound(Gdx.files.internal("select.wav"));
	private Sound point = Gdx.audio.newSound(Gdx.files.internal("point.wav"));
	
	private int score;
	
	private Color wallColor;
	private Color title;
	
	private boolean start;
	private boolean playing;
	private boolean dead;
	private boolean paused;
	
	private ShapeRenderer sr;
	private SpriteBatch batch;
	private BitmapFont font;
	
	private ArrayList<Tail> tail;
	private ArrayList<Wall> walls;
	
	String f;

	public MenuState(GSM gsm) {
		super(gsm);
		
	}

	
	public void init() {
		
		
		high = getScore();
		
		player = new Player();
		sr = new ShapeRenderer();
		tail = new ArrayList<Tail>();
		walls = new ArrayList<Wall>();
		start = false;
		playing = true;
		dead = false;
		paused = false;
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		bg = new Background();
		bt = false;
		walls.add(new Wall(Gdx.graphics.getWidth()));
		walls.add(new Wall(Gdx.graphics.getWidth() + Gdx.graphics.getWidth()/2));
		tex = new Texture(Gdx.files.internal("score.png"));
		score = 0;
		
		wallColor = Color.WHITE;
		title = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
		
		HS = new Texture(Gdx.files.internal("highscor.png"));
		GO = new Texture(Gdx.files.internal("gameover.png"));
		YS = new Texture(Gdx.files.internal("yourscore.png"));
		N = new Texture(Gdx.files.internal("numbers.png"));
		P = new Texture(Gdx.files.internal("play.png"));
		S = new Texture(Gdx.files.internal("setting.png"));
	}

	
	public void update(float dt) {
		
		if(paused){
			
		}else{
		
		handleInput();
		Collision();
		
		if(playing){
			
			
			bg.update();
			player.update();
			tail.add(new Tail(player.getX(),player.getY(),wallColor));
			
			for(int i = 0;i < tail.size();i++){
				tail.get(i).update();
				if(tail.get(i).getX() < 0){
					tail.remove(i);
					i--;
				}
				
			}
			
			for(int i = 0;i < walls.size();i++){
				walls.get(i).update();
				if(walls.get(i).getX() <0){
					walls.add(new Wall(Gdx.graphics.getWidth()) );
					walls.remove(i);
					i--;
				}
			}
			for(int i = 0;i< walls.size();i++){
				if(walls.get(i).getX() < player.getX() && walls.get(i).getPassed() == false){
					score++;
					point.play(.6f);
					walls.get(i).setPassed(true);
					wallColor = walls.get(i).getColor();
				}
			}

		}
		}
				
	}

	
	public void draw() {
		
		
		sr.begin(ShapeType.Filled);
		sr.setProjectionMatrix(cam.combined);
		sr.setColor(Color.BLACK);
		sr.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		sr.end();
		
		if(start){
			
			
			
		}
		if(playing || paused){
		
		
		
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BLACK);
		sr.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		sr.end();
		
		
		
		//if(bt){
			//bg.draw(sr);
		//}
		player.draw(sr,wallColor);
		for(int i = 0;i < tail.size();i++){
			tail.get(i).draw(sr);
		}
		
		for(int i = 0;i < walls.size();i++){
			walls.get(i).draw(sr);
		}
		
		batch.begin();
		batch.setProjectionMatrix(cam.combined);
		font.setScale(2, 2);
		font.setColor(Color.WHITE);
		//font.draw(batch, "Score: " + score, 10,Gdx.graphics.getHeight()-25);
		
		//draw score
		batch.draw(tex,10,Gdx.graphics.getHeight()-tex.getHeight() - 10);
		
				String s = String.valueOf(score);
				
				if(score > 99){
					
					batch.draw(getNumber(Character.getNumericValue(s.charAt(0))),tex.getWidth() + 20,Gdx.graphics.getHeight()-tex.getHeight()-8);
					batch.draw(getNumber(Character.getNumericValue(s.charAt(1))),tex.getWidth() + 60,Gdx.graphics.getHeight()-tex.getHeight()-8);
					batch.draw(getNumber(Character.getNumericValue(s.charAt(2))),tex.getWidth() + 100,Gdx.graphics.getHeight()-tex.getHeight()-8);
					
				}else if(score > 9){
					
					batch.draw(getNumber(Character.getNumericValue(s.charAt(0))),tex.getWidth() + 20,Gdx.graphics.getHeight()-tex.getHeight()-8);
					batch.draw(getNumber(Character.getNumericValue(s.charAt(1))),tex.getWidth() + 60,Gdx.graphics.getHeight()-tex.getHeight()-8);
					
				}else{
					batch.draw(getNumber(score),tex.getWidth() + 20,Gdx.graphics.getHeight()-tex.getHeight()-8);
				}
		
		batch.end();
		
		}
		
		if(dead){
			
			
			
			//setting the highscore
			
			if(score > high){
				saveScore(score);
				high = score;
			}
			
			sr.begin(ShapeType.Filled);
			sr.setColor(wallColor);
			sr.rect((Gdx.graphics.getWidth() - GO.getWidth())/2,Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2,GO.getWidth(),GO.getHeight());
			sr.end();
			
			batch.begin();
			
			batch.draw(S,Gdx.graphics.getWidth()-S.getWidth(),Gdx.graphics.getHeight() - S.getHeight());
			
			batch.draw(GO,(Gdx.graphics.getWidth() - GO.getWidth())/2,Gdx.graphics.getHeight()/4 + Gdx.graphics.getHeight()/2);
			batch.draw(P,(Gdx.graphics.getWidth()-P.getWidth())/2,Gdx.graphics.getHeight()/5);
			batch.draw(YS,(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20,Gdx.graphics.getHeight()/2);
			batch.draw(HS,(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20);
			
			//drawing score
			
			String s = String.valueOf(score);
			
			if(score > 99){
				
				batch.draw(getNumber(Character.getNumericValue(s.charAt(0))),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 10,Gdx.graphics.getHeight()/2 );
				batch.draw(getNumber(Character.getNumericValue(s.charAt(1))),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 50,Gdx.graphics.getHeight()/2 );
				batch.draw(getNumber(Character.getNumericValue(s.charAt(2))),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 90,Gdx.graphics.getHeight()/2 );
				
			}else if(score > 9){
				
				batch.draw(getNumber(Character.getNumericValue(s.charAt(0))),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 10,Gdx.graphics.getHeight()/2 );
				batch.draw(getNumber(Character.getNumericValue(s.charAt(1))),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 50,Gdx.graphics.getHeight()/2 );
				
			}else{
				batch.draw(getNumber(score),(Gdx.graphics.getWidth()-YS.getWidth())/2 - 20 + YS.getWidth() + 10,Gdx.graphics.getHeight()/2 );
			}
			
			//drawing highscore
			
			String h = String.valueOf(high);
			
			if(high > 99){
				
				batch.draw(getNumber(Character.getNumericValue(h.charAt(0))),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth() + 10,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20 );
				batch.draw(getNumber(Character.getNumericValue(h.charAt(1))),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth() + 50,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20 );
				batch.draw(getNumber(Character.getNumericValue(h.charAt(2))),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth() + 90,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20 );
				
			}else if(high > 9){
				
				batch.draw(getNumber(Character.getNumericValue(h.charAt(0))),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth()+ 10,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20);
				batch.draw(getNumber(Character.getNumericValue(h.charAt(1))),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth() +50,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20);
				
			}else{
				batch.draw(getNumber(high),(Gdx.graphics.getWidth()-HS.getWidth())/2 - 20 + HS.getWidth() + 10,Gdx.graphics.getHeight()/2 - YS.getHeight() - 20);
			}
			
			
			batch.end();
			
		}
		
	}

	
	public void handleInput() {
		if(start)
			if(GameKeys.isDown(GameKeys.LEFTM)){
				playing = true;
				start = false;
			}
		
		if(playing){
			
			if(GameKeys.isDown(GameKeys.LEFTM)){
				player.setFalling(false);
			}else{
				player.setFalling(true);
			}
			
			if(GameKeys.isPressed(GameKeys.SPACE)){
				playing = false;
				paused = true;
			}
			
		}
		
		if(dead){
			if(GameKeys.isPressed(GameKeys.LEFTM)){
				
				if(GameKeys.getTouchX() > Gdx.graphics.getWidth()-S.getWidth() && GameKeys.getTouchX() < Gdx.graphics.getWidth() && GameKeys.getTouchY() > 0 && GameKeys.getTouchY() < S.getHeight()){
					
					gsm.setState(GSM.CHANGE);
				
			}else{
				System.out.println(bt);
				select.play();
				gsm.setState(GSM.MENU);
			}
				
			}
		}
		
	}

	
	public void dispose() {
		batch.dispose();
		font.dispose();
		sr.dispose();
		for(int i = 0;i< walls.size();i++){
			walls.remove(i);
		}
		for(int i = 0;i< tail.size();i++){
			tail.remove(i);
		}
		
	}
	
	//handle numbers
	
	private TextureRegion getNumber(int i){
		
		int w = 38;
		int h = 44;
		
		TextureRegion num = new TextureRegion(N,i*w,0,w,h);
		
		return num;
	} 
	
	
	
	public void death(){
		for(int i = 0; i < walls.size();i++){
			walls.remove(i);}
		for(int i = 0;i< tail.size();i++){
			tail.remove(i);}
		dead = true;
		playing = false;
	}
	public void resetGame(){
		playing = true;
		dead = false;
		player.reset();
		walls.add(new Wall(Gdx.graphics.getWidth()));
		walls.add(new Wall(Gdx.graphics.getWidth() + Gdx.graphics.getWidth()/2));
	}
	
	public void Collision(){
		
		Rectangle rectP = player.getBounds();
		
		for(int i = 0;i < walls.size();i++){
			
			Rectangle rectT = walls.get(i).getBoundsTop();
			Rectangle rectB = walls.get(i).getBoundsBottom();
			
			if(rectP.overlaps(rectT)){
				death();
			}
			if(rectP.overlaps(rectB)){
				death();
			}
			
			
		}
		
		
	}
	
	private void saveScore(int high){
		
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		if(getDif().equals("easy"))
			prefs.putInteger("scoreE",high);
		else if(getDif().equals("normal"))
			prefs.putInteger("highscore",high);
		else if(getDif().equals("hard"))
			prefs.putInteger("scoreH",high);
		else
			prefs.putInteger("highscore", high);
		prefs.flush();
	}
	
	private int getScore(){
		
		//FileHandle file = Gdx.files.internal("data.txt");
		//String a = file.readString();
		//int high = Integer.parseInt(a);
		//return high;
		
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		//prefs.putInteger("highscore", 10);
		//prefs.putInteger("scoreE", 0);
		//prefs.putInteger("scoreH", 0);
		
		prefs.flush();
		if(getDif().equals("easy"))
				return prefs.getInteger("scoreE");
		if(getDif().equals("normal"))
			return prefs.getInteger("highscore");
		if(getDif().equals("hard"))
			return prefs.getInteger("scoreH");
		return 0;
		
	}
	
	private String getDif(){
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		return prefs.getString("difficulty");
	}
	

}






















