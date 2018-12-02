package sample;

import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * Class for tokens-ball,shield,magnet and bomb
 */
public class Token {
    /**
     * Name of the token
     */
    private String name;
    /**
     * Image used to show the token
     */
    protected ImageView photo;
    /**
     * Y coordinate of the token
     */
    private double y;
    /**
     * Constructor to initialize the token
     * @param n Name of the token
     * @param p Address of the photo used to show the token
     */
    Token(String n,String p){
        setName(n);
        setPhoto(p);
    }
    /**
     * This function sets the photo of the token
     * @param location Address of the image
     */
    public void setPhoto(String location) {
    	Image image = new Image(location);
    	photo=new ImageView(image);
    	Random rand=new Random();
    	photo.setX(rand.nextInt(500));
    	
    	photo.setY(-100);
    	photo.setFitWidth(50);
    	photo.setFitHeight(50);
    }
    /**
     * This method returns the photo of the token
     * @return Photo of the token
     */
    public ImageView getPhoto() {
    	return photo;
    }
    /**
     * Getter function to get the Token name
     * @return Token name
     */
    public String getName() {
        return name;
    }
    /**
     * Setter function to set the Token name
     * @param name Name which needs to be assigned to the token
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isOverlapping(List<Block> blocks, Token T[], Wall W) {
    	for(int i=0;i<blocks.size();i++) {
    		if(blocks.get(i).getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())){
    			return true;
    		}
    	}
    	for(int i=0;i<2;i++) {
    		if(T[i]!=null && T[i].getPhoto().getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())) {
    			return true;
    		}
    	}
    	if(W!=null && W.getLine().getBoundsInParent().intersects(this.getPhoto().getBoundsInParent())) {
    		return true;
    	}
    	return false;
    }
    /**
     * This function moves down the Token
     * @param speed The speed by which the token needs to move
     */
    void moveDown(double speed) {
    	setManualY(photo.getTranslateY()+1+speed);
    	photo.setTranslateY(photo.getTranslateY() + 1 + speed);
    }
    /**
     * Getter function for getting the Y coordinate of the token
     * @return Y coordinate of the token
     */
    public double getManualY() {
		return y;
    }
    /**
     * Setter function for the Y coordinate of the Token
     * @param relativeY The value which needs to be assigned to the Y coordinate
     */
    public void setManualY(double relativeY) {
    	this.y=relativeY;
    }
}

/**
 * Magnet, Subclass of {@link Token}
 */
class Magnet extends Token{
    /**
     * Time for which the magnet needs to be there
     */
    private int timelimit;
    /**
     * The range in which it should attract the coins
     */
    private Circle range;
    /**
     * To check if the magnet is alive or not
     */
    boolean isAlive;
    /**
     * Start time of the magnet
     */
    private long start;
    /**
     * Text object which stores displays timer for magnet
     */
    private Text Timer;
    /**
     * Constructor to initialize the Magnet
     * @param n Name of the Magnet
     * @param loc Address of the image of the magnet
     */
    Magnet(String n, String loc) {
        super(n,loc);
        setTimelimit(8);
        range=null;
        isAlive=false;
        
    }
    /**
     * Getter function for {@link #timelimit}
     * @return {@link #timelimit}
     */
    public int getTimelimit() {
        return timelimit;
    }
    /**
     * Setter function for {@link #timelimit}
     * @param timelimit the time which needs to be assigned to the timelimit
     */
    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }
    /**
     * Getter function for {@link #Timer}
     * @return {@link #Timer}
     */
    public Text getTimer() {
    	return Timer;
    }
    /**
     * Getter function for {@link #range}
     * @return {@link #range}
     */
    public Circle getRange() {
        return range;
    }
    /**
     * Setter function for {@link #range}
     * @param snake the Snake object for its current position
     */
    public void setRange(Snake snake) {
        range=new Circle();
        range.setCenterX(snake.getBody().get(0).getCenterX());
        range.setCenterY(snake.getBody().get(0).getCenterY());
        range.setRadius(150);
        range.setFill(Color.RED);
        range.setOpacity(0.1);
    }
    /**
     * Function to attract coins within a range
     */
    public void attractCoins(Snake snake) {
    	setRange(snake);
    	setStart(System.currentTimeMillis());
    	isAlive=true;
    	Timer=new Text("Magnet:"+String.valueOf(0));
    	Timer.setX(300);
    	Timer.setY(40);
    	Timer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    	Timer.setFill(Color.AQUA);
    }

    /**
     * Setter function for {@link #start}
     * @param start time which needs to be assigned to start
     */
    public void setStart(long start) {
		this.start = start;
	}

    /**
     * Getter function for {@link #start}
     * @return {@link #start}
     */
	public long getStart() {
		return start;
	} 
}
/**
 * Shield, Subclass of {@link Token}
 */
class Shield extends Token{
    /**
     *Time for which the shield needs to be there
     */
    private int timelimit;
    /**
     * Start time of the shield
     */
    private long start;
    /**
     * to check if the shield is alive
     */
    boolean isAlive;
    /**
     * Text object which is displayed for shield timer
     */
    private Text Timer;

    /**
     * Constructor which initializes the Shield
     * @param n Name of the shield
     * @param loc Address of the shield image
     */
    Shield(String n,String loc ) {
        super(n,loc);
        setTimelimit(5);
        isAlive=false;
    }
    /**
     * Getter function for the text object {@link #Timer}
     * @return {@link #Timer}
     */
    public Text getTimer() {
    	return Timer;
    }
    /**
     * Getter function for {@link #timelimit}
     * @return {@link #timelimit}
     */
    public int getTimelimit() {
        return timelimit;
    }
    /**
     * Setter function for {@link #timelimit}
     * @param timelimit value which needs to be assigned to the {@link #timelimit}
     */
    public void setTimelimit(int timelimit) {
        this.timelimit = timelimit;
    }
    /**
     * This function protects the snake
     * <p>By letting the snake destroy blocks without decreasing length
     */
    public void protectSnake() {
    	setStart(System.currentTimeMillis());
//    	System.out.println("start  : "+getStart());
    	isAlive=true;
    	Timer=new Text("Shield:"+String.valueOf(0));
    	Timer.setX(400);
    	Timer.setY(40);
    	Timer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
    	Timer.setFill(Color.AQUA);
    }
    /**
     * Getter function for {@link #start}
     * @return
     */
	public long getStart() {
		return start;
	}
    /**
     * Setter function for start
     * @param start {@link #start}
     */
	public void setStart(long start) {
		this.start = start;
	} 

}
/**
 * Destroy_Blocks, subclass of {@link Token}
 */
class Destroy_Blocks extends Token{
    /**
     * Constructor to initialize the Destroy blocks token
     * @param n Name of this token
     * @param loc Address of the image for this token
     */
    Destroy_Blocks(String n, String loc) {
        super(n, loc);
    }
    /**
     * This function destroys all the blocks on the screen
     * @param play Pane in which all the blocks are present
     * @param blocks List of the blocks
     * @param blockText List of the values written on the blocks
     * @return Score gained by destroying those blocks
     */
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
/**
 * Ball, subclass of {@link Token}
 */
class Ball extends Token{
    /**
     * Value of the ball
     */
    private int value;
    /**
     * Text written on the ball, the value
     */
    private Text ballText;
    /**
     * Constructor which initializes the ball
     * @param n Name of the token ball
     * @param loc Address of the image used for ball
     */
    Ball(String n, String loc) {
        super(n,loc);
        Random R=new Random();
        value=R.nextInt(20)+1;
        ballText=new Text(String.valueOf(value));
        ballText.setX(photo.getX()+20);
        ballText.setY(photo.getY()+30);
    }
    /**
     * Getter function for {@link #value}
     * @return {@link #value}
     */
    public int getValue() {
        return value;
    }
    /**
     * Setter function for {@link #value}
     * @param value which needs to be assigned to {@link #value}
     */
    public void setValue(int value) {
        this.value = value;
    }
    /**
     * This function moves the text written on the ball
     * @param speed speed of the movement
     */
    void moveBallText(double speed) {
        ballText.setTranslateY(ballText.getTranslateY() + 1 + speed);
    }
    /**
     * Getter function for {@link #ballText}
     * @return {@link #ballText}
     */
    public Text getballText() {
    	return ballText;
    }
    /**
     * This function sets the value of the snake balls
     * @param snake snake object
     */
    public void increaseBalls(Snake snake) {
    	snake.setNumBalls(value);
    }

}