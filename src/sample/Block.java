package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Class for the blocks
 */
public class Block extends Rectangle {
    /**
     * Value written on the block
     */
    private int blockValue;                     		//number written on the block
    /**
     * Variable to check if the block is Alive(not destroyed)
     */
    private boolean alive;
    /**
     * X coordinate of the block
     */
    private double x;
    /**
     * Y coordinate of the block
     */
    private double y;
    /**
     * Constructor to initialize the values
     * @param x X coordinate of the block
     * @param y Y coordinate of the block
     * @param w Width of the block
     * @param h Height of the block
     * @param blockValue Value of the block
     * @param color Color of the Block
     */
    public Block(int x, int y, int w, int h, int blockValue, Color color) {

        super(w, h, color);
        super.setArcHeight(25);
        super.setArcWidth(25);
        this.blockValue = blockValue;
        setTranslateX(x);
        setTranslateY(y);
        setAlive(true);
        this.x=x;
        this.y=y;

    }

    /**Getter Function for Block value
     * @return Block Value
     */
    public int getblockValue(){
    	return this.blockValue;
    }
    /**
     * Setter Function for block Value
     * @param blockValue
     */
    public void setblockValue(int blockValue) {
        this.blockValue=blockValue;
    }
    /**
     * This methods moves the block down
     * @param speed speed by which the block should move
     */
    void moveDown(double speed) {
    	setManualY(getTranslateY()+1+speed);
    	setTranslateY(getTranslateY() + 1 + speed);
    }
    /**
     * To check if the block is not destroyed
     * @return True if the block is alive
     */
	public boolean getAlive() {
		return alive;
	}
    /**
     * Sets the {@link #alive} to true or false
     * @param alive Boolean values which needs to be assigned to {@link #alive}
     */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
    /**
     * Function to get the Y coordinate of the block
     * @return The Y coordinate of the block
     */
	public double getManualY() {
			return y;
	}
    /**
     * Sets the Y coordinate of the block
     * @param relativeY Value which needs to be assigned to {@link #y}
     */
	public void setManualY(double relativeY) {
		this.y=relativeY;
	}

}