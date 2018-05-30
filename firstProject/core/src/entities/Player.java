package entities;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Player {
	
	private int x;
	private int y;
	private float dy;
	private int dx;
	private int radius;
	
	private boolean falling;
	private int maxSpeed;
	private float gravity;
	private Color color;
	
	public Player(){
		
		x = Gdx.graphics.getWidth()/3;
		y = Gdx.graphics.getHeight()/2;
		radius = 50;
		color = new Color(1,1,1,1);
		gravity = 1.5f;
		maxSpeed = 15;
		dy=0;
		falling = true;
		
	}
	
	public void update(){
		
		if(falling){
			dy-=gravity;
		}else{
			dy+=gravity;
		}
		if(dy > maxSpeed)
			dy = maxSpeed;
		if(dy < -maxSpeed)
			dy = -maxSpeed;
		
		y+=(int)dy;
		
		if(y < 0)
			y = 0;
		if(y > Gdx.graphics.getHeight())
			y = Gdx.graphics.getHeight();
		
	}
	
	public void draw(ShapeRenderer sr,Color wc){
		
		sr.begin(ShapeType.Filled);
		
		//sr.setColor(Color.RED);
		//sr.rect(x-radius, y-radius, radius*2, radius*2);
		
		sr.setColor(wc);
		sr.circle(x,y,radius);
		
		sr.end();
		
		
	}
	
	public void reset(){
		x = Gdx.graphics.getWidth()/3;
		y = Gdx.graphics.getHeight()/2;
		dy=0;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public void setColor(Color c){
		color = c;
	}
	
	public void setFalling(boolean b){
		falling = b;
	}
	
	public boolean getFalling(){
		return falling;
	}
	public Rectangle getBounds(){
		return new Rectangle(x-radius,y-radius,radius*2,radius*2);
	}

}
















