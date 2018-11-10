
package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    private int blockValue;                     		//number written on the block
    private boolean alive;
    private double x;
    private double y;
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
    public void setblockValue(int blockValue) {
        this.blockValue=blockValue;
    }
    
    void moveDown(double speed) {
    	setManualY(getTranslateY()+1+speed);
    	setTranslateY(getTranslateY() + 1 + speed);
        
    }
	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public double getManualY() {
			return y;
	}
	public void setManualY(double relativeY) {
		this.y=relativeY;
	}

}