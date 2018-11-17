package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Snake {
    private int numBalls;
    protected Text T;
    List<Circle> body;
    public static double snakePos=270;

    Snake(){
        
       
        T=new Text(String.valueOf(numBalls));
        
        body=new ArrayList<Circle>();
        setNumBalls(4);
        
    }

    public int getNumBalls() {
        return numBalls;
    }
    public void setNumBalls(int numBalls) {
        this.numBalls += numBalls;
        setScoreText();
    }
    
    public void setScoreText() {
    	if(numBalls>0 && this.body.size()>0) {
    		double x=snakePos-5;
        	double y=this.body.get(0).getCenterY()+2;
        	
        	T.setX(x);
        	T.setY(y);
        	T.setText(String.valueOf(numBalls));
    	}
    	
    	
    }
    public Text getScore() {
    	return T;
    }

    class smoothSnake{
        public void swiftSnake(TranslateTransition translate,int i,Snake snake,int direction){
            if(i==0){
                if(direction==0)
                {translate.setByX(-20);
                    Snake.snakePos=250+snake.body.get(0).getTranslateX();
                }
                else {
                    Snake.snakePos=290+snake.body.get(0).getTranslateX();
                    translate.setByX(+20);
                }
                translate.setDuration(Duration.millis(50));
                translate.play();
                snake.setScoreText();
            }
            else {
                //System.out.println(snake.body.get(0).getTranslateX());
                if(direction==0)
                translate.setToX(snake.body.get(0).getTranslateX()-20);
                else
                    translate.setToX(snake.body.get(0).getTranslateX()+20);
                translate.setDuration(Duration.millis(i*60));
                translate.play();
            }

        }

    }

    public void moveLeft() {
        //this.body.get(0).setCenterX(this.body.get(0).getCenterX()-20);
    	for(int i=0;i<this.body.size();i++){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(this.body.get(i));
            smoothSnake obj=new smoothSnake();
            obj.swiftSnake(translate,i,this,0);        //0 for left, 1 for right
        }
    	setScoreText();

    }
    public void moveRight() {
       // this.body.get(0).setCenterX(this.body.get(0).getCenterX()+20);

            for(int i=0;i<this.body.size();i++){
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(this.body.get(i));
                smoothSnake obj=new smoothSnake();
                obj.swiftSnake(translate,i,this,1);
            }

    	setScoreText();
    }
    
}

