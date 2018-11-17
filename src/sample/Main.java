package sample;

import javafx.animation.*;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Pane;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Main extends Application {
    Random rand=new Random();

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception{
    	
//    	long start = System.currentTimeMillis();
//    	long finish = System.currentTimeMillis();
//    	System.out.println(start+" > " +finish);
//    	long timeElapsed = finish - start;
//    	System.out.println("Time Elapsed : "+timeElapsed);
        
    	Pane group = new Pane();
        primaryStage.getIcons().add(new Image("file:Snake-icon.png"));;

        Image image = new Image("file:snake-vs-block.png");
        ImageView imageview=new ImageView(image);
        imageview.setFitHeight(820);    imageview.setFitWidth(620);

        Glow glow = new Glow();
        glow.setLevel(0.5);
        imageview.setEffect(glow);

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);      text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.SEAGREEN);

        Bloom bloom=new Bloom();
        bloom.setThreshold(0.2);

        text.setEffect(bloom);

        Button btn=new Button("Press ENTER");
        btn.setLayoutX(85);     btn.setLayoutY(490);
        btn.setMinSize(200, 50);
        btn.setStyle("-fx-font: 25 arial; -fx-base: #008080;");

        ///////////////////////////////////////////////////////////////////////
        
        Circle snake_animation[]=new Circle[7];
        TranslateTransition translateTransition[]=new TranslateTransition[7];
        int tempY=40;
        for(int i=0;i<7;i++) {
            snake_animation[i]=new Circle();
            snake_animation[i].setCenterX(40);
            snake_animation[i].setCenterY(tempY);
            snake_animation[i].setRadius(10);
            snake_animation[i].setFill(Color.GAINSBORO);
            snake_animation[i].setStrokeWidth(20);

            translateTransition[i] = new TranslateTransition();
            translateTransition[i].setDuration(Duration.millis(1000));
            translateTransition[i].setNode(snake_animation[i]);
            translateTransition[i].setByY(60);
            translateTransition[i].play();

            tempY=tempY-20;
        }
        snake_animation[0].setRadius(12);
        
        /////////////////////////////////////////////////////////////////////

        btn.setOnAction(e-> Menu(primaryStage,imageview));

        // Blinking Effect in Button
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), btn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.6);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

        group.getChildren().addAll(imageview,text,btn);
        group.getChildren().addAll(snake_animation);

        Scene scene = new Scene(group, 600,800);

        primaryStage.setTitle("SnakeVsBlock");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    ///////////////////////////////////////////////////////////////////////////////////

    public void Menu(Stage primaryStage, ImageView imageview) {
        Pane subroot = new Pane();

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);      text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.SEAGREEN);

        Bloom bloom=new Bloom();
        bloom.setThreshold(0.2);
        text.setEffect(bloom);

        Button btn1=new Button("Play");
        btn1.setLayoutX(95);    btn1.setLayoutY(450);
        btn1.setMinSize(160, 40);
        btn1.setStyle("-fx-font: 20 arial; -fx-base: #008080;");

        ///////////////////////////////////////////////////////////////////////
        Circle snake_animation[]=new Circle[12];
        TranslateTransition translateTransition[]=new TranslateTransition[12];
        int tempY=115;
        for(int i=0;i<12;i++) {
            snake_animation[i]=new Circle();
            snake_animation[i].setCenterX(40);
            snake_animation[i].setCenterY(tempY);
            snake_animation[i].setRadius(10);
            snake_animation[i].setFill(Color.GAINSBORO);
            snake_animation[i].setStrokeWidth(20);

            translateTransition[i] = new TranslateTransition();
            translateTransition[i].setDuration(Duration.millis(1000));
            translateTransition[i].setNode(snake_animation[i]);
            translateTransition[i].setByY(110);
            translateTransition[i].play();

            tempY=tempY-20;
        }
        snake_animation[0].setRadius(12);
        /////////////////////////////////////////////////////////////////////

        Button btn2=new Button("LeaderBoard");
        btn2.setLayoutX(95);
        btn2.setLayoutY(510);
        btn2.setMinSize(160, 40);
        btn2.setStyle("-fx-font: 20 arial; -fx-base: #008080;");

        Button btn3=new Button("Themes");
        btn3.setLayoutX(95);
        btn3.setLayoutY(570);
        btn3.setMinSize(160, 40);
        btn3.setStyle("-fx-font: 20 arial; -fx-base: #008080;");

        Button btn4=new Button("How to Play");
        btn4.setLayoutX(95);
        btn4.setLayoutY(630);
        btn4.setMinSize(160, 40);
        btn4.setStyle("-fx-font: 20 arial; -fx-base: #008080;");

        Button btn5=new Button("Exit");
        btn5.setLayoutX(95);
        btn5.setLayoutY(690);
        btn5.setMinSize(160, 40);
        btn5.setStyle("-fx-font: 20 arial; -fx-base: #008080;");

        btn1.setOnAction(e-> {
            Controller obj=new Controller();
            obj.Play(primaryStage,imageview);
        });
        btn2.setOnAction(e-> {
            LeaderBoard obj=new LeaderBoard();
            obj.leaderboard(primaryStage,imageview);
        });
        btn3.setOnAction(e-> {
            Themes obj=new Themes();
            obj.themes(primaryStage,imageview);
        });
        btn4.setOnAction(e-> {
            Instructions obj=new Instructions();
            obj.instructions(primaryStage,imageview);
        });
        btn5.setOnAction(e-> Platform.exit());


        subroot.getChildren().addAll(imageview,text,btn1,btn2,btn3,btn4,btn5);
        subroot.getChildren().addAll(snake_animation);

        Scene scene=new Scene(subroot,600,800);

        primaryStage.setScene(scene);

    }

    //////////////////////////////////////////////////////////////////////////////////////

    public void restart(Stage primaryStage,ImageView imageView,Controller currentGame){
        currentGame=null;                   //Send to dustbin
        Controller obj = new Controller();
        obj.Play(primaryStage,imageView);

    }

    //////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        launch(args);
    }
}