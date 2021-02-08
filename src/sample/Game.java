package sample;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;


public class Game extends Scene {

	public Game(BorderPane root, double width, double height) {
		super(root, width, height);

		Screen road = new Screen(400, 0, 460, 960);
		GameLoad.gameLoad = new GameLoad(root, road);
		Screen.screen = new Screen(root, road);
		setupKeyEventHandler();
	}

	

	private void setupKeyEventHandler() {
		setOnKeyPressed((key) -> {
			KeyCode press = key.getCode();

			if (press == KeyCode.ENTER) {
				restart();

			}

			else if (press == KeyCode.UP) {
				if (Cars.velocity < Cars.balance) {
					Cars.velocity += 75;
					Dash.velocity += 75;
					GameLoad.velocityStep += 75;
				}
			} else if (press == KeyCode.LEFT || press == KeyCode.RIGHT) {
				Player.carPlayer.keyPressed(press);
			} else {

			}
		});
		setOnKeyReleased((key) -> {
			KeyCode release = key.getCode();
			if (release == KeyCode.LEFT || key.getCode() == KeyCode.RIGHT) {
				Player.carPlayer.keyReleased(release);
			}

			else if (release == KeyCode.UP) {
				Player.carPlayer.keyReleased(release);

				Cars.velocity = 100;
				Dash.velocity = 100;
				GameLoad.velocityStep = 100;
			}

			else {

			}
		});
	}

	private void restart() {
		Screen.getInstance().stop();
		GameLoad.gameLoad.resume();
		Player.level = 1;
		Player.score = 0;
		Cars.velocity = 525;
		Dash.velocity = 525;
		GameLoad.velocityStep = 525;
		GameLoad.gameLoad.gameLoad();
		Screen.screen.init();
		Screen.getInstance().start();
		GameLoad.gameLoad.start();
		
	}

	public void start() {
		GameLoad.gameLoad.start();
	}

	public void resume() {
		GameLoad.gameLoad.resume();
	}

}
