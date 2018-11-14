package sample;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Snake {
    private int numBalls;
    private Text T;
    List<Circle> body;

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
    		double x=this.body.get(0).getCenterX()-5;
        	double y=this.body.get(0).getCenterY()+2;
        	
        	T.setX(x);
        	T.setY(y);
        	T.setText(String.valueOf(numBalls));
    	}
    	
    	
    }
    public Text getScore() {
    	return T;
    }
    public void moveLeft() {
    	for(int i=0;i<this.body.size();i++){
            this.body.get(i).setCenterX(this.body.get(i).getCenterX()-10);
        }
    	setScoreText();
    }
    public void moveRight() {
    	for(int i=0;i<this.body.size();i++){
            this.body.get(i).setCenterX(this.body.get(i).getCenterX()+10);
        }
    	setScoreText();
    }
    
}

