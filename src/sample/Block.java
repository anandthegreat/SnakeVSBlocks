package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    private int blockValue;                     		//number written on the block

    public Block(int x, int y, int w, int h, int blockValue, Color color) {

        super(w, h, color);
        super.setArcHeight(25);
        super.setArcWidth(25);
        this.blockValue = blockValue;
        setTranslateX(x);
        setTranslateY(y);
//        this.setArcHeight(25);
//        this.setArcWidth(25);

    }
    public void setblockValue(int blockValue) {
        this.blockValue=blockValue;
    }
    void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }
    void moveRight() { 
    	setTranslateX(getTranslateX() + 5); 
    }
    void moveUp() {
        setTranslateY(getTranslateY() - 5);
    }
    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }


}