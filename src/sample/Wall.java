package sample;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Wall {
//    private String color;
    private Line L;
    private double y;
    Wall(Color c){
//        setColor(c);
        setLine(c);
    }
    
    public void setLine(Color c) {
    	Random rand=new Random();
    	int startX=rand.nextInt(4)+1;
    	int length=rand.nextInt(2);
    	L=new Line(100*startX+9,-130,100*startX+9,100*length);
    	L.setStroke(c);
    	L.setStrokeWidth(3);
    }
    public Line getLine() {
    	return L;
    }
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
    void moveDown(double speed) {
    	setManualY(L.getTranslateY()+1+speed);
    	L.setTranslateY(L.getTranslateY() + 1 + speed);
    }
    
    public double getManualY() {
		return y;
    }
    public void setManualY(double relativeY) {
    	this.y=relativeY;
    }
    	
}
