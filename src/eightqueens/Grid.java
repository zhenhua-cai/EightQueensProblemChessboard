package eightqueens;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Grid extends Button{
	private Color color;
	private Pane pane=new Pane();
	private Queen queen;//the queen in this grid
	private boolean hasQueen=false;//if this grid already has queen
	private int i;//row index
	private int j;//col index
	
	public Grid(int i,int j) {
		this.i=i;
		this.j=j;
	}
	/**
	 * set the pane of this grid
	 * @param c
	 */
	public void setGrid(Color c) {
		color=c;
		if(c==Color.WHITE)
			pane.setStyle("-fx-background-color:white;");
		else{
			pane.setStyle("-fx-background-color:black;");
		}
		this.setGraphic(pane);
		this.setStyle("-fx-padding:0");
	}
	/**
	 * the background color of this grid
	 * @return
	 */
	public Color getBackgroundColor() {
		return color;
	}
	/**
	 * get the root pane of this grid
	 * @return
	 */
	public Pane getRoot() {
		return pane;
	}
	/**
	 * add a queen to this grid
	 * @param q
	 */
	public void add(Queen q) {
		for(int i=0;i<4;i++) {
			this.getChildren().add(q.getElements()[i]);
		}
		queen=q;
		hasQueen=true;
	}
	/**
	 * set background color to red to represent warning.
	 */
	public void warn() {
		pane.setStyle("-fx-background-color:red");
	}
	/**
	 * reset to background to orignal color.
	 */
	public void reset() {
		if(color==Color.WHITE)
			pane.setStyle("-fx-background-color:white;");
		else{
			pane.setStyle("-fx-background-color:black;");
		}
	}
	/**
	 * remove the queen from this grid.
	 */
	public void removeAll() {
		for(int i=0;i<4;i++) {
			this.getChildren().removeAll(queen.getElements());
		}
		hasQueen=false;
		queen=null;
	}
	/**
	 * check if this grid has a queen
	 * @return
	 */
	public boolean hasQueen() {
		return hasQueen;
	}
	/**
	 * get the row index
	 * @return
	 */
	public int getI() {
		return i;
	}
	/**
	 * get the col index
	 * @return
	 */
	public int getJ() {
		return j;
	}
}
