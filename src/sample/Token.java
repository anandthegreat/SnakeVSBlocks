package sample;

import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    private long start;
    boolean isAlive;
    private Text Timer;
    
    Shield(String n,String loc ) {
        super(n,loc);
        setTimelimit(5);
        isAlive=false;
    }
    
    public Text getTimer() {
    	return Timer;
    }

    public int getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }

    public void protectSnake() {
    	setStart(System.currentTimeMillis());
//    	System.out.println("start  : "+getStart());
    	isAlive=true;
    	Timer=new Text("Shield:"+String.valueOf(0));
    	Timer.setX(400);
    	Timer.setY(80);
    	Timer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    	Timer.setFill(Color.AQUA);
    }

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	} 

}

class Destroy_Blocks extends Token{

    Destroy_Blocks(String n, String loc) {
        super(n, loc);
    }

    public int destroyBlocks(Pane play, List<Block> blocks, List<Text> blockText) {
    	int tempScore=0;
//    	System.out.println("Blocks to be destroyed : "+ blocks.size());
    	int tempSize=blocks.size();
    	for(int i=0;i<tempSize;i++) {
    		tempScore+=blocks.get(0).getblockValue();
    		blocks.get(0).setAlive(false);
            blocks.get(0).setVisible(false);
            play.getChildren().remove(blocks.get(0));
            blocks.remove(0);
            

            blockText.get(0).setVisible(false);
            play.getChildren().remove(blockText.get(0));
            blockText.remove(0);
    	}
//    	System.out.println("Blocks after destroyed : "+ blocks.size());
    	return tempScore;
    }

}

class Ball extends Token{
    private int value;
    private Text ballText;
    Ball(String n, String loc) {
        super(n,loc);
        Random R=new Random();
        value=R.nextInt(20)+1;
        ballText=new Text(String.valueOf(value));
        ballText.setX(photo.getX()+20);
        ballText.setY(photo.getY()+30);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    void moveBallText(double speed) {
        ballText.setTranslateY(ballText.getTranslateY() + 1 + speed);
    }
    public Text getballText() {
    	return ballText;
    }
    public void increaseBalls(Snake snake) {
    	snake.setNumBalls(value);
    }

}