package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class Player{
	public ImageView img;
	private Image carImage = new Image("sample/green.png");
	public static Player carPlayer;

	private Screen road;
	static ImageView createimg;
	private Screen coordinate;
	private double velocity = 0;
	

	public Player(BorderPane root, Screen coordinate) {
		this.coordinate = coordinate;
		img = new ImageView();
		img.setImage(carImage);
		createimg = createImage();
		root.getChildren().add(createImage());

	}
	
	public ImageView createImage() {
		img.setX(coordinate.x);
		img.setY(coordinate.y);
		return img;
	}

	public void setRoad(Screen road) {
		this.road = road;
	}
	
	public void gameOver() {
		GameLoad.gameLoad.gameOver();

	}

	public void keyPressed(KeyCode press) {
		if (press == KeyCode.LEFT) {

			velocity = -450;

		} else if (press == KeyCode.RIGHT) {
			velocity = 450;

		} else {

		}
	}

	public void keyReleased(KeyCode release) {
		if (release == KeyCode.LEFT || release == KeyCode.RIGHT) {
			velocity = 0;
			
		}
	}
	
	public Screen getCoordinate() {
		return coordinate;
	}
	
	public static int score = 0;
	public static int level = 1;
	
	public void changingBoard(double acc) {
	
		double change = velocity * acc;
		double x = getCoordinate().x;
		if ((x + change > road.x)
				&& (x + change + getCoordinate().width < road.x + road.width)) {
			coordinate.x = (x + change);
			img.setX(x + change);
		}

		Screen.screen.showScore(score);
		Screen.screen.showLevel(level);

		GameLoad.gameLoad.update(acc);

	}

	

	

}
