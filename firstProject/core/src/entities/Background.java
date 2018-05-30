package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Background {
	
	private int[] x;
	private int[] y;
	private int[] r;
	private int[] s;
	private Color[] colors;
	
	public Background(){
		x = new int[20];
		y = new int[20];
		r = new int[20];
		s = new int[20];
		colors = new Color[20];
		setColors();
		setCircles();
	}
	
	public void update(){
		
		for(int i=0;i<r.length;i++){
			if(r[i] < 40){
				r[i] += s[i];
			}else{
				r[i] = 0;
				s[i] = (int)(Math.random()*3 +1);
				x[i] = (int)(Math.random()*Gdx.graphics.getWidth());
				y[i] = (int)(Math.random()*Gdx.graphics.getHeight());
				colors[i] = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),.8f);
			}
		}
		
		
	}
	
	public void draw(ShapeRenderer sr){
		
		sr.begin(ShapeType.Filled);
		for(int i=0;i<20;i++){
			sr.setColor(colors[i]);
			sr.circle(x[i], y[i], r[i]);
		}
		sr.end();
		
	}
	
	public void setColors(){
		for(int i = 0; i < colors.length;i++){
			colors[i] = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),.8f);
		}
	}
	
	public void setCircles(){
		
		for(int i = 0;i<r.length;i++){
			r[i] = 0;
			x[i] = (int)(Math.random()*Gdx.graphics.getWidth());
			y[i] = (int)(Math.random()*Gdx.graphics.getHeight());
			s[i] = (int)(Math.random()*3 +1);
		}
		
	}

}
