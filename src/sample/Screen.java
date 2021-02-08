package sample;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Screen
	{
		double x, y, width, height;
		private static Screen gamePlay = new Screen();
		private BorderPane root;
		public Screen road;
		private boolean isPlay = false;

		
		public Screen(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		private Screen() {

		}

		public static Screen getInstance() {
			return gamePlay;
		}
		

		public Screen(BorderPane root, Screen roadBoundary) {
			this.root = root;
			this.road = roadBoundary;

			init();
		}
		
		private Text score, level;
		public void init() {
			score = show("SCORE", Color.BLACK, 0);
			level = show("LEVEL", Color.BLACK, 120);

		}

		private Text show(String title, Color bg, double offset) {
			Rectangle rectBg = new Rectangle(10, offset, 10, 110);

			Text score = new Text(title);
			score.setFill(Color.BLACK);
			score.setX(rectBg.getX() + 25);
			score.setY(rectBg.getY() + 35);
			root.getChildren().add(score);

			Text level = new Text();
			level.setFill(Color.BLACK);
			level.setX(score.getX() + 25);
			level.setY(score.getY() + 35);
			root.getChildren().add(level);
			return level;
		}

		public void showScore(int point) {
			score.setText(String.valueOf(point));
		}

		public void showLevel(int speed) {
			level.setText(String.valueOf(speed));
		}

		
		public static Screen screen;
		public Player carPlayer;
		public ArrayList<Cars> enemies = new ArrayList<Cars>();
		public ArrayList<Dash> dash = new ArrayList<Dash>();
		
		public void addEntity(Cars enemy) {
			enemies.add(enemy);
		}
		public void addEntity(Dash dash) {
			this.dash.add(dash);
		}

		public void setMainEntity(Player carPlayer) {
			this.carPlayer = carPlayer;
			
		}

		public void start() {
			isPlay = true;

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).game();
			}
		}

		private long previous = 0;

		public void changingBoard(long now) {
			if (previous == 0 || isPlay == false) {
				previous = now;
				return;
			}

			double dt = (now - previous) / Math.pow(10, 9); 
			
			for (int i = 0; i < dash.size(); i++) {
				Dash roadDash = dash.get(i);

				roadDash.moveDash(dt);

			}
			
			for (int i = 0; i < enemies.size(); i++) {
				Cars enemy = enemies.get(i);

			
					if (carPlayer.createImage().getBoundsInParent().intersects(enemy.createImage().getBoundsInParent())) {
							Player.carPlayer.gameOver();
						}
			

				enemy.colorChanging(dt);

			}
			
			carPlayer.changingBoard(dt);

			previous = now;
		}

		public void stop() {
			isPlay = false;
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).resume();
			}
			enemies.clear();

		}


}
