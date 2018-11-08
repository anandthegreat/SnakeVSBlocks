package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blocks extends Rectangle {
    private int blockValue;                     		//number written on the block

    public Blocks(int x, int y, int w, int h, int blockValue, Color color) {

        super(w, h, color);
        this.blockValue = blockValue;
        setTranslateX(x);
        setTranslateY(y);
        this.setArcHeight(25);
        this.setArcWidth(25);

    }
    public void setblockValue(int blockValue) {
        this.blockValue=blockValue;
    }
    void moveLeft() {
        setTranslateX(getTranslateX() - 5);
    }
    void moveRight() { setTranslateX(getTranslateX() + 5); }
    void moveUp() {
        setTranslateY(getTranslateY() - 5);
    }
    void moveDown() {
        setTranslateY(getTranslateY() + 5);
    }

}