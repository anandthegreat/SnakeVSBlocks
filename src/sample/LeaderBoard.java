package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LeaderBoard implements Serializable {
    Main obj;
    Text[] highScoreDetail;                 // Score, Date
    Integer[] top10Scores;                  //in ascending order

    public LeaderBoard(){
        obj=new Main();
        highScoreDetail=new Text[11];
        top10Scores=new Integer[11];
        for(int i=0;i<11;i++){
            top10Scores[i]=0;
        }

        for(int i=0;i<11;i++){
            highScoreDetail[i]=new Text(String.valueOf(i+1)+"\t\t\t  No records");
            highScoreDetail[i].setX(70);
            highScoreDetail[i].setY(100+i*40);
            highScoreDetail[i].setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR,28));	   //changed Font to Helvetica
            highScoreDetail[i].setFill(Color.BROWN);
        }

    }

    public void checkHighScore(Integer score){               //Check is the score can be added to the LeaderBoard
                                                            //Incomplete function
        top10Scores[10]=score;
        Arrays.sort(top10Scores);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
//        System.out.println(dateFormat.format(date));
        for(int i=0;i<10;i++)
        {
            if(top10Scores[i]==score){
                highScoreDetail[i].setText(String.valueOf(i+1) + "\t\t"+ String.valueOf(score)+"\t"+ dateFormat.format(date));
            }
        }

    }

    protected void leaderboard(Stage primaryStage, ImageView imageview) {
        Pane leaderpane=new Pane();

        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(650);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> obj.Menu(primaryStage,imageview));

        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(500);
        r.setHeight(700);
        r.setFill(Color.BEIGE);
        r.setOpacity(0.95);

        leaderpane.getChildren().setAll(imageview,r,btn6);
        leaderpane.getChildren().addAll(highScoreDetail);

        Scene scene=new Scene(leaderpane,600,800);


        primaryStage.setScene(scene);


    }
}
