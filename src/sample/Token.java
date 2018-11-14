package sample;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Token {
    private String name;
    protected ImageView photo;
    private double y;
    Token(String n,String p){
        setName(n);
        setPhoto(p);
    }
    
    public void setPhoto(String location) {
    	Image image = new Image(location);
    	photo=new ImageView(image);
    	Random rand=new Random();
    	photo.setX(rand.nextInt(500));
    	photo.setY(-100);
    	photo.setFitWidth(50);
    	photo.setFitHeight(50);
    }
    public ImageView getPhoto() {
    	return photo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    void moveDown(double speed) {
    	setManualY(photo.getTranslateY()+1+speed);
    	photo.setTranslateY(photo.getTranslateY() + 1 + speed);
    }
    
    public double getManualY() {
		return y;
    }
    public void setManualY(double relativeY) {
    	this.y=relativeY;
    }
}


class Magnet extends Token{

    private int timelimit;
    private int range;
    Magnet(String n, String loc) {
        super(n,loc);
        setTimelimit(5);
        setRange(100);
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void attractCoins() {

    }

}

class Shield extends Token{

    private int timelimit;

    Shield(String n,String loc ) {
        super(n,loc);
        setTimelimit(5);
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public void protectSnake() {

    }

}

class Destroy_Blocks extends Token{

    Destroy_Blocks(String n, String loc) {
        super(n, loc);
    }

    public void destroyBlocks() {

    }

}

class Ball extends Token{
    private int value;
    private Text ballT;
    Ball(String n, String loc) {
        super(n,loc);
        Random R=new Random();
        value=R.nextInt(20);
        ballT=new Text(String.valueOf(value));
        ballT.setX(photo.getX()+18);
        ballT.setY(photo.getY()+20);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    void moveBallText(double speed) {
        ballT.setTranslateY(ballT.getTranslateY() + 1 + speed);
    }
    public Text getballT() {
    	return ballT;
    }
    public void increaseBalls(Snake snake) {
    	snake.setNumBalls(value);
    }

}