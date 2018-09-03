package eightqueens;
import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class Main extends Application{

	//the queens array
	private boolean[][] exist=new boolean[8][8];
	private Grid[][] grids=new Grid[8][8];
	private Grid errorgrid=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	/**
	 * initialize the stage and scene.
	 */
	@Override
	public void start(Stage stage) {
		Scene scene=new Scene(parent());
		stage.setScene(scene);
		stage.setTitle("Eight Queens Problem");
		stage.setResizable(false);
		stage.show();
	}
	/**
	 * create the root pane using grid pane.
	 * @return root pane.
	 */
	public Parent parent() {
		GridPane root=new GridPane();
		root.setStyle("-fx-padding:0 30px 20px 0");
		createBoard(root);
		
		return root;
	}
	/**
	 * Create the chess board.
	 * @param root root pane.
	 */
	private void createBoard(GridPane root) {
		Label label=new Label();
		label.setStyle("-fx-font-size:30px;");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setPrefHeight(50);
		label.setPrefWidth(50);
		root.add(label, 0, 0);
		for(int i=1;i<=8;i++) {
			Label l=new Label(Integer.toString(i));
			l.setStyle("-fx-font-size:30px;-fx-alignment:center");
			l.setTextAlignment(TextAlignment.CENTER);
			l.setPrefHeight(50);
			l.setPrefWidth(100);
			root.add(l, i, 0);
		}
		for(int i=1;i<=8;i++) {
			Label l=new Label(Integer.toString(i));
			l.setStyle("-fx-font-size:30px;-fx-alignment:center");
			l.setTextAlignment(TextAlignment.CENTER);
			l.setPrefHeight(100);
			l.setPrefWidth(50);
			root.add(l, 0, i);
			for(int j=1;j<=8;j++) {
				Grid grid=new Grid(i-1,j-1);
				//set the color of each grid depends on the sum of col and row.
				if((i+j)%2==1)
					grid.setGrid(Color.WHITE);
				else{
					grid.setGrid(Color.BLACK);
				}
				addGridListeners(grid);
				grid.setPrefWidth(100);
				grid.setPrefHeight(100);
				exist[i-1][j-1]=false;
				grids[i-1][j-1]=grid;
				root.add(grid, j, i);
			}
		}
	}
	/**
	 * add click listener to grid.
	 * @param grid
	 */
	private void addGridListeners(Grid grid) {
		grid.setOnMousePressed(event->{
			if(event.getButton()==MouseButton.PRIMARY) {
				if(!grid.hasQueen()) {
					if(isOk(grid.getI(),grid.getJ())) {
						Color c=grid.getBackgroundColor()==Color.WHITE?Color.BLACK:Color.WHITE;
						Queen q=new Queen(c);
						grid.add(q);
						exist[grid.getI()][grid.getJ()]=true;
					}
					else
						errorgrid.warn();
				}
			}
			else {
				if(grid.hasQueen()) {
					grid.removeAll();
					exist[grid.getI()][grid.getJ()]=false;					
				}
			}
		});
		grid.setOnMouseReleased(e->{
			if(errorgrid!=null){
				errorgrid.reset();
				errorgrid=null;
			}
		});
	
	}
	/**
	 * check if ok to put a queen in that grid
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean isOk(int i,int j) {
		//check row and col
		for(int k=0;k<8;k++) {
			if(exist[i][k]&&j!=k) {
				errorgrid=grids[i][k];
				return false;
			}
			if(exist[k][j]&&i!=k) {
				errorgrid=grids[k][j];
				return false;
			}
		}
		//check left upper diagonal
		for(int k=1;i-k>=0&&j-k>=0;k++) {
			if(exist[i-k][j-k]) {
				errorgrid=grids[i-k][j-k];
				return false;
			}
		}
		//check right bottom diagonal
		for(int k=1;i+k<8&&j+k<8;k++) {
			if(exist[i+k][j+k]) {
				errorgrid=grids[i+k][j+k];
				return false;
			}
		}
		//check left bottom
		for(int k=1;i-k>=0&&j+k<8;k++) {
			if(exist[i-k][j+k]) {
				errorgrid=grids[i-k][j+k];
				return false;
			}
		}
		//check right upper
		for(int k=1;i+k<8&&j-k>=0;k++) {
			if(exist[i+k][j-k]) {
				errorgrid=grids[i+k][j-k];
				return false;
			}
		}
		return true;
	}
}
