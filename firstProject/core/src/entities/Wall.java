package entities;


import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Wall {
	
	private int x;
	private double y1;
	private int y2;
	private int width;
	private int height;
	
	private static int HEIGHT = Gdx.graphics.getHeight();
	private static int GAP;
	
	private boolean passed;
	
	private Color c;
	private Random ran;
	
	public Wall(int x){
		
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		
		if(prefs.getString("difficulty").equals("easy")){
			GAP = 400;
		}else if(prefs.getString("difficulty").equals("normal")){
			GAP = 300;
		}else if(prefs.getString("difficulty").equals("hard")){
			GAP = 200;
		}else{
			GAP = 300;
		}
		
		

		
		
		
		height = HEIGHT;
		int min = 20 + GAP;
		int max = HEIGHT - 20;
		int difference = max - min;
		
		ran = new Random();
		pickColor();
		this.x = x;
		double i = Math.random();
		y1 =ran.nextInt(max - min) + min;
		System.out.println(y1);
		y2 = (int)y1 - (height + GAP);
		//height = 400;
		width = 20;
		passed = false;
	}
	
	public void draw(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		
		sr.setColor(c);
		sr.rect((float)x, (float)y1, width, height);
		sr.rect((float)x,(float)y2,width,height);
		
		sr.end();
		
	}
	
	public void update(){
		x -= 8;
		
	}
	
	public void pickColor(){
		c = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
		//c = new Color(Color.RED);
	}
	
	public void setPassed(boolean b){
		passed = b;
	}
	
	public boolean getPassed(){
		return passed;
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(x,(int)y1,width,height);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle(x,y2,width,height);
	}
	
	
	public Color getColor(){
		return c;
	}
	public int getX(){
		return x;
	}
	public double getY1(){
		return y1;
	}
	public int getY2(){
		return y2;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
