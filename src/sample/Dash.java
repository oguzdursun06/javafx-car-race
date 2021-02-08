package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Dash{
	private Image carImage = new Image("sample/dash.png");
	public ImageView img;
	public Screen coordinate;
	public static double velocity = 0;
	
	
	
	public Dash(BorderPane root, Screen coordinate) {
		this.coordinate = coordinate;
		img = new ImageView();
		img.setImage(carImage);
		root.getChildren().add(createImage());
	}

	public ImageView createImage() {
		img.setX(coordinate.x);
		img.setY(coordinate.y);
		return img;
	}
	
	public void moveDash(double acc) {
		double d = velocity * acc;
		double y = coordinate.y;
		if(y < coordinate.height) {
			coordinate.y = (y + d);
			img.setY(y + d);
		} else {
			coordinate.y = -coordinate.height;
			img.setY(-coordinate.height);
		}
	}
	
	




	
}
