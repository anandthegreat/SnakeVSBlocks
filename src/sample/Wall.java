package sample;

import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/**
 * Class for Walls
 */
public class Wall {
	/**
	 * Line object which represents a wall
	 */
    private Line L;
	/**
	 * Y coordinate of the Line
	 */
    private double y;
	/**
	 * Constructor to initialize the wall
	 * @param c Color of the wall
	 */
    Wall(Color c){
        setLine(c);
    }
	/**
	 * Creates a new line and sets its color and width
	 * @param c Color of the wall
	 */
    public void setLine(Color c) {
    	Random rand=new Random();
    	int startX=rand.nextInt(4)+1;
    	int length=rand.nextInt(2);
    	L=new Line(100*startX+9,-130,100*startX+9,100*length);
    	L.setStroke(c);
    	L.setStrokeWidth(3);
    }
	/**
	 * Getter function for {@link #L}
	 * @return Line object {@link #L}
	 */
    public Line getLine() {
    	return L;
    }

	/**
	 * Function to check if blocks and tokens are overlapping
	 * @param blocks List of blocks
	 * @param T list of tokens
	 * @return True or false depending on overlap
	 */
    public boolean isOverlapping(List<Block> blocks, Token T[]) {
    	for(int i=0;i<blocks.size();i++) {
    		if(blocks.get(i).getBoundsInParent().intersects(this.getLine().getBoundsInParent())){
    			return true;
    		}
    	}
    	for(int i=0;i<2;i++) {
    		if(T[i]!=null && T[i].getPhoto().getBoundsInParent().intersects(this.getLine().getBoundsInParent())) {
    			return true;
    		}
    	}
    	
    	return false;
    }
	/**
	 * This function moves the wall down
	 * @param speed Speed by which the blocks needs to be moved Down
	 */
    public void moveDown(double speed) {
    	setManualY(L.getTranslateY()+1+speed);
    	L.setTranslateY(L.getTranslateY() + 1 + speed);
    }
	/**
	 * Getter function for {@link #y} coordinate of the block
	 * @return {@link #y} coordinate
	 */
    public double getManualY() {
		return y;
    }
	/**
	 * Setter function for {@link #y} coordinate
	 * @param relativeY Value which needs to be assigned to {@link #y}
	 */
    public void setManualY(double relativeY) {
    	this.y=relativeY;
    }
    	
}
