package sample;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Assignment4 extends Application {
	public Game game;

	@Override
	public void start(Stage screen) {
			BorderPane root = new BorderPane();
			game = new Game(root, 1280, 960);
			screen.setScene(game);
			game.start();
			

			screen.setTitle("HUBBMRacer");
			screen.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
