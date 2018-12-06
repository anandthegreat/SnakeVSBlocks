package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Class to store top 10 scores of the player.
 */
public class LeaderBoard implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Score> scores;                 // Score, Date
    transient private Text info;
    
	public ArrayList<Score> getScores(){
		return scores;
	
	}
    public LeaderBoard(ArrayList<Score> scores){
//        obj=M;
        this.scores=scores;
        		//new ArrayList<Score>();
        info=new Text("		LEADERBOARD");
        info.setX(120);
        info.setY(100);
        info.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR,22));
    }
    
    public void addScore(Score score) {
    	System.out.println("adding Score : "+score.getScore());
//    	int size=scores.size();
    	scores.add(score);
    	Collections.sort(scores, new sortbyScore());
    	
    	if(scores.size()>10) {							// Top 10 scores only
    		scores.remove(scores.size()-1);
    	}
    	
    	displayHighScores();
    }
    
    public void displayHighScores(){ 
    	System.out.println("Setting score");
    	System.out.println("Scores Size : "+scores.size());
//    	System.out.println(scores.get(0).getDate());
//    	System.out.println(scores.get(0).getScore());
    	int size=scores.size();
    	String S="		LEADERBOARD\n		Date				     Score \n";
    	for(int i=0;i<size;i++) {
    		S=S+(i+1)+". 	"+scores.get(i).toString()+"\n";
    	}
    	info.setText(S);
    	
    }

    protected void leaderboard(Main main, Stage primaryStage, ImageView imageview) {
        
    	Pane leaderpane=new Pane();

        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(650);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> main.Menu(primaryStage,imageview));

        Rectangle rect = new Rectangle();
        rect.setX(50);
        rect.setY(50);
        rect.setWidth(500);
        rect.setHeight(700);
        rect.setFill(Color.BEIGE);
        rect.setOpacity(0.95);

        leaderpane.getChildren().setAll(imageview,rect,btn6,info);
//        leaderpane.getChildren().setAll(info);
//        leaderpane.getChildren().addAll(highScoreDetail);
        
        displayHighScores();
        Scene scene=new Scene(leaderpane,600,800);
        
        primaryStage.setScene(scene);
    }
}

class Score implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String date;
	Integer score;
	
	Score(Integer score){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date d = new Date();
		date=dateFormat.format(d);
		this.score=score;
	}
	
	public String getDate() {
		return date;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public String toString() {
		return date+" 		"+getScore().toString();
	}

	
}

class sortbyScore implements Comparator<Score>{
	
	@Override 
	public int compare(Score s1, Score s2) {
	
		if(s1.getScore()<s2.getScore())
			return 1;
		else
			return -1;
	}
}
