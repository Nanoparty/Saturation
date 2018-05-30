package entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Tail {
	
	private int x;
	private int y;
	private int radius;
	private Color color;
	
	public Tail(int x,int y,Color c){
		
		color = c;
		this.x=x;
		this.y=y;
		radius = 15;
	}
	
	public void draw(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		sr.setColor(color);
		sr.circle(x, y, radius);
		sr.end();
	}
	
	public void update(){
		x-=8;
	}
	
	public int getX(){return x;}
	
	public void setColor(Color c){
		color = c;
	}
	

}


