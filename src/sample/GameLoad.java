package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameLoad {

	private Image symbol = new Image("sample/cs.jpg");
	private Image leftImage = new Image("sample/race.jpg");
	private Image rightImage = new Image("sample/race.jpg");
	public static double velocityStep = 525;
	public static GameLoad gameLoad;
	private Text informationText;
	private BorderPane root;
	private AnimationTimer animate;
	public Screen road;
	private ImageView left[], right[];

	public GameLoad(BorderPane root, Screen road) {
		this.root = root;
		this.road = road;
		gameLoad();
	
	}
	
	public void gameLoad() {

		ImageView img1 = new ImageView();
		img1.setImage(symbol);
		img1.setOpacity(0.6);
		img1.setX(980);
		img1.setY(0);
		ImageView img2 = new ImageView();
		img2.setImage(leftImage);
		img2.setX(0);
		img2.setY(-150);
		ImageView img3 = new ImageView();
		img3.setImage(rightImage);
		img3.setOpacity(0.9);
		img3.setX(980);
		img3.setY(-150);
		root.getChildren().addAll(img1, img2, img3);
		
		animate = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Screen.getInstance().changingBoard(now);
			}
			
		};
		
		Rectangle rect = new Rectangle(400, 0, 460, 960);
		rect.setFill(Color.ROYALBLUE);
		rect.setOpacity(0.8);
		root.getChildren().add(rect);

		for (int i = 0; i < 6; i++) {
			Dash dash = new Dash(root, new Screen(400, 0, 460, 960));
			dash.coordinate.x = 630;
			dash.img.setX(630);
			dash.coordinate.y = (-480 * i);
			dash.img.setY(-480 * i);
			Screen.getInstance().addEntity(dash);
		}
		
		left = new ImageView[6];
		right = new ImageView[6];

		for (int i = 0; i < 6; i++) {
			left[i] = createImage(280, -480 * i, 0, "sample/tree.jpg");
			right[i] = createImage(860, -480 * i, 0, "sample/tree.jpg");
		}

	
		Screen car = new Screen(605, 580, 64, 128);
		Player.carPlayer = new Player(root, car);
		Player.carPlayer.setRoad(road);
		Screen.getInstance().setMainEntity(Player.carPlayer);
		
		Screen carEnemyBoundary = new Screen(0, 0, 64, 128);
		Cars carEnemy = new Cars(root, carEnemyBoundary);
		carEnemy.road(road);

		Screen.getInstance().addEntity(carEnemy);
	
		informationText = new Text();
		informationText.setX(400);
		informationText.setY(300);
		root.getChildren().add(informationText);
	
	
	}
		
	
	

	



	private ImageView createImage(double x, double y, double rotation, String img) {
		ImageView tree = new ImageView();
		tree.setImage(new Image(img));
		tree.setX(x);
		tree.setY(y);
		root.getChildren().add(tree);
		return tree;
	}


	public void update(double dt) {
		double displacement = velocityStep * dt;
		for (int i = 0; i < 6; i++) {
			double y = left[i].getY();
			if (y < road.height) {
				left[i].setY(y + displacement);
				right[i].setY(y + displacement);
			} else {
				left[i].setY(-road.height);
				right[i].setY(-road.height);
			}
		}
	}

	public void gameOver() {
		Screen.getInstance().stop();
		Font font = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 50);
		informationText.setText("GAME OVER\nYour score " + Player.score + "\nPress ENTER to restart");
		informationText.setFont(font);
	}

	public void start() {
		
		Text informationText = new Text();
		
		if (Cars.velocity == 0)
		{informationText.setX(400);
		informationText.setY(300);}
		else {
		informationText.setX(-1000);
		informationText.setY(300);}
		root.getChildren().add(informationText);
		Font font = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30);
		informationText.setText("PLEASE ENTER TO START");
		informationText.setFont(font);
	
		
		animate.start();
		
		
	}

	public void resume() {
		
		animate.stop();
		root.getChildren().clear();
		

	}

	

}
