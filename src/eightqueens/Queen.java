package eightqueens;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Queen{
	//use rectangle to represents the queen
	private Rectangle[] rects=new Rectangle[4];
	public Queen(Color c) {
		Rectangle r1=new Rectangle(10,40,80,40);
		r1.setFill(c);
		for(int i=0;i<3;i++) {
			Rectangle r2=new Rectangle(10+i*32,20,16,20);
			r2.setFill(c);
			rects[i]=r2;
		}
		rects[3]=r1;
	}
	/**
	 * the rectangles of queen
	 * @return
	 */
	public Rectangle[] getElements() {
		return rects;
	}
}
