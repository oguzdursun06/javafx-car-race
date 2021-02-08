package sample;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class Cars{
	private Image enemyimg=new Image("sample/red.png");
	private Image enemypassed=new Image("sample/yellow.png");
	private Image temp =new Image("sample/red.png");
	private Image collision =new Image("sample/white.png");
	public ImageView img;
	public Screen coordinate;
	static double velocity = 0,balance = 525;
	
	
	public Cars(BorderPane root, Screen carEnemycoordinate) {
		this.coordinate = carEnemycoordinate;
		img = new ImageView();
		img.setImage(enemyimg);
		root.getChildren().add(createImage());
        
        }
	public ImageView createImage(){
		img.setX(coordinate.x);
		img.setY(coordinate.y);
		return img;
	}

	
	public void road(Screen roadcoordinate) {
		createImage().setClip(new Rectangle(400, 0, 460 , 960));
	}

	public void game() {
		velocity = balance;
		restart();
		
	}
	
	public void resume() {
		velocity = 0;
		
		Player.createimg.setImage(collision);
		createImage().setImage(collision);
		
	}

	public void colorChanging(double acc) {
		
		Rectangle rect = (Rectangle)createImage().getClip();
		double d = velocity * acc;
		double y = coordinate.y;
		if(y + d < rect.getHeight()) {
			coordinate.y = (y + d);
			img.setY(y+d);
		}
		
		if(y+d>580)
			createImage().setImage(enemypassed);
			
		if(y+d>960) {
			createImage().setImage(temp);	
			Player.score += 1*Player.level;
			if (Player.score > (Player.level*Player.level)) {
				Player.level++;
				Cars.velocity += 75;
				Dash.velocity += 75;
				GameLoad.velocityStep += 75;
			}
			restart();
		}
	}
			
	private void restart() {
		Rectangle rect = (Rectangle)createImage().getClip();
		coordinate.x = (rect.getX() + (rect.getWidth() - coordinate.width) * new Random().nextDouble());
		img.setX(rect.getX() + (rect.getWidth() - coordinate.width) * new Random().nextDouble());
		coordinate.y = (- coordinate.height);
		img.setY(- coordinate.height);
	}






}

