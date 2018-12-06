package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Snake class for controlling the snake
 */
public class Snake implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
     * The number of balls in the body of snake
     */
    private int numBalls;
    /**
     * Text object which contains snake length
     */
    protected Text T;
    /**
     * List which contains circle objects for the body of snake
     */
    private List<Circle> body;
//  public static double snakePos=270;
    /**
     * Constructor to initialize the snake
     */
    Snake(){
        T=new Text(String.valueOf(numBalls));
        body=new ArrayList<Circle>();
        setNumBalls(4);
        
    }
    /**
     * Getter function for no of balls in snake's body
     * @return number of balls in Snake's body
     */
    public int getNumBalls() {
        return numBalls;
    }
    /**
     * Setter function for setting the no of balls in snake's body
     * @param numBalls the no of balls which needs to be added in snake's body
     */
    public void setNumBalls(int numBalls) {
        this.numBalls += numBalls;
        setScoreText();
    }

    /**
     * This function returns the list of circles in the snake body
     * @return {@link #body}
     */
    public  List<Circle> getBody() {
    	return body;
    }

    /**
     * Sets the text object which stores snake's length
     */
    public void setScoreText() {
    	if(numBalls>0 && this.body.size()>0) {
            double x=this.body.get(0).getCenterX()-5;
        	double y=this.body.get(0).getCenterY()+2;
        	
        	T.setX(x);
        	T.setY(y);
        	T.setText(String.valueOf(numBalls));
    	}
    	
    	
    }
    /**
     * Function to get score
     * @return score
     */
    public Text getScore() {
    	return T;
    }

//    class smoothSnake{
//        public void swiftSnake(TranslateTransition translate,int i,Snake snake,int direction){
//            if(i==0){
//                if(direction==0)
//                {translate.setByX(-20);
//                    Snake.snakePos=250+snake.body.get(0).getTranslateX();
//                }
//                else {
//                    Snake.snakePos=290+snake.body.get(0).getTranslateX();
//                    translate.setByX(+20);
//                }
//                translate.setDuration(Duration.millis(50));
//                translate.play();
//                snake.setScoreText();
//            }
//            else {
//                //System.out.println(snake.body.get(0).getTranslateX());
//                if(direction==0)
//                translate.setToX(snake.body.get(0).getTranslateX()-20);
//                else
//                    translate.setToX(snake.body.get(0).getTranslateX()+20);
//                translate.setDuration(Duration.millis(i*60));
//                translate.play();
//            }
//
//        }
//
//    }
    /**
     * Function to move the snake left
     */
    public void moveLeft() {
    	for(int i=0;i<this.body.size();i++){
//            TranslateTransition translate = new TranslateTransition();
//            translate.setNode(this.body.get(i));
//            smoothSnake obj=new smoothSnake();
//            obj.swiftSnake(translate,i,this,0);        //0 for left, 1 for right
              this.body.get(i).setCenterX(this.body.get(i).getCenterX()-10);
        }
    	setScoreText();
    }
    /**
     * Function to move the snake right
     */
    public void moveRight() {
            for(int i=0;i<this.body.size();i++){
//                TranslateTransition translate = new TranslateTransition();
//                translate.setNode(this.body.get(i));
//                smoothSnake obj=new smoothSnake();
//                obj.swiftSnake(translate,i,this,1);
                 this.body.get(i).setCenterX(this.body.get(i).getCenterX()+10);
                }

    	setScoreText();
    }
    
}