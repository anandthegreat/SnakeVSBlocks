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

    double speed=0;  //for increasing speed 
    Random rand=new Random();
    Integer score=0;		// player's score
    private List<Block> blocks=new ArrayList<Block>();
    private List<Text>  blockText= new ArrayList<Text>();
    Text Score=new Text("Score : "+score.toString());		// Score Board
    Circle snake[]=new Circle[5];
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane group = new Pane();                                                      //Changed root to group
        primaryStage.getIcons().add(new Image("file:Snake-icon.png"));;

        Image image = new Image("file:snake-vs-block.png");
        ImageView imageview=new ImageView(image);
        imageview.setFitHeight(820);    imageview.setFitWidth(620);
        
        //Instantiating the Glow class
        Glow glow = new Glow();
        //setting level of the glow effect
        glow.setLevel(0.5);
        //Applying bloom effect to text
        imageview.setEffect(glow);

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);      text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.SEAGREEN);

        Bloom bloom=new Bloom();
        bloom.setThreshold(0.2);

        //Applying bloom effect to text
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

        // action event
        btn.setOnAction(e-> Menu(primaryStage,imageview));              //Replaced with lambda expression , a short method.
        
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

    private void Menu(Stage primaryStage, ImageView imageview) {

        Pane subroot = new Pane();

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);      text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.SEAGREEN);

        Bloom bloom=new Bloom();
        bloom.setThreshold(0.2);
        //Applying bloom effect to text
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

        btn1.setOnAction(e-> Play(primaryStage,imageview));
        btn2.setOnAction(e-> LeaderBoard(primaryStage,imageview));
        btn3.setOnAction(e-> Themes(primaryStage,imageview));
        btn4.setOnAction(e-> Instructions(primaryStage,imageview));
        btn5.setOnAction(e-> Platform.exit());


        subroot.getChildren().addAll(imageview,text,btn1,btn2,btn3,btn4,btn5);
        subroot.getChildren().addAll(snake_animation);
        
        Scene scene=new Scene(subroot,600,800);

        primaryStage.setScene(scene);

    }

    /////////////////////////////////////////////////////////////////////////////////////////


    protected Color colorPicker(){                  //Random Color Generator
        int toss= rand.nextInt(6);
        if(toss==0)
            return Color.rgb(128,255,255);       //Sky Blue
        else if(toss==1)
            return Color.rgb(255,153,221);       //Pink
        else if(toss==2)
            return Color.rgb(153,255,153);       //Light Green
        else if(toss==3)
            return Color.rgb(255,209,179);       //Orange
        else if(toss==4)
            return Color.rgb(255,102,102);      //Light Red
        else if(toss==5)
            return Color.WHITE;
        else 
        	return Color.YELLOW;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    void moveText(Text text, double speed) {

        text.setTranslateY(text.getTranslateY() + 1 + speed);

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public class blockAndText {                     //helper class for createBlocks to return shape of blocks
        private List<Block> blockShape;             //as well as block value
        private List<Text>  blockText;

        public blockAndText(List<Block> blockShape, List<Text> blockText) {
            this.blockShape = blockShape;
            this.blockText = blockText;
        }

        public List<Block> getShape() {
            return blockShape;
        }

        public List<Text> getText() {
            return blockText;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected blockAndText createBlocks(){                      //Random Blocks Creator
        List<Block> Blockslist=new ArrayList<Block>();
        List<Text>  BlockText =new ArrayList<Text>();
        for(int i=0;i<5;i++){
            int toss= rand.nextInt(3);
            if(toss==0 || toss ==1){
                int valueofNewBlock= rand.nextInt(20)+1;
                Blockslist.add(new Block( (i+1)*5+i*100,-100,100,90,valueofNewBlock,colorPicker()));
                BlockText.add(new Text(String.valueOf(valueofNewBlock)));
                BlockText.get(BlockText.size()-1).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                BlockText.get(BlockText.size()-1).setX((i+1)*5+i*100+40);
                BlockText.get(BlockText.size()-1).setY(-50);}
        }

        return new blockAndText(Blockslist,BlockText);
    }

    int blockTimer=0;               // timer for creating new blocks on the screen

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void gameplay(Pane play) {									
    		Score.setText("Score : "+score.toString());
    		for(int i=0;i<blocks.size();i++) {
    			blocks.get(i).moveDown(speed);
    			moveText(blockText.get(i),speed);

    		}
             List<Block> newBlocks=null;            //so that it is automatically destroyed after each execution
             List<Text>  newBlocksValue=null;
    		if(blockTimer%300==0) {
    		    blockAndText obj= createBlocks();
    		    newBlocks= obj.blockShape;
    		    newBlocksValue =obj.blockText;
    		    for(int i=0;i<newBlocks.size();i++){
    		        blocks.add(newBlocks.get(i));
    		        blockText.add(newBlocksValue.get(i));
    		         play.getChildren().add(newBlocks.get(i));
    		         play.getChildren().add(newBlocksValue.get(i));
    		    }
    		}
    		checkCollision();
    		checkBoundary();
            blockTimer+=1;
//    		System.out.println(blocks[0].getY()+" ,,,,"+blocks[0].getTranslateY());
//    		speed=speed+0.01;
    }
    
    protected void checkCollision() {
    	for(int i=0;i<blocks.size();i++) {
    		if(blocks.get(i).getBoundsInParent().intersects(snake[0].getBoundsInParent()) & blocks.get(i).getAlive()==true){    //Collision Check
// 					snake[i].setFill(Color.YELLOW);
//    	            play.getChildren().remove(Blockslist.get(i));
    	    		score+=blocks.get(i).getblockValue();
    	    		blocks.get(i).setAlive(false);
    	    		blocks.get(i).setVisible(false);
    	    		blocks.remove(i);

    	    		blockText.get(i).setVisible(false);
    	    		blockText.remove(i);

//    	    		System.gc();
    	       }
    	}
    	
    }
    
    protected void checkBoundary() {									
    	for(int i=0;i<blocks.size();i++) {
//    		System.out.println(blocks.get(i).getManualY());
    		if(blocks.get(i).getManualY()>800) {						// checking if block passed the snake
    			
    			blocks.get(i).setVisible(false);
    			blocks.get(i).setAlive(false);
    			blocks.remove(i);
                blockText.get(i).setVisible(false);
                blockText.remove(i);

    		}
    	}
    }

    protected void Play(Stage primaryStage, ImageView imageview) {

        Pane play=new Pane();

        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);

        snake[0]=new Circle(270,610,1);
        for(int i=1;i<5;i++){
            snake[i]=new Circle(270,600+i*20,10);
            snake[i].setFill(Color.WHITE);
        }
        
//        int position[]= {10,20,30,40,50,60,70,80};
        AnimationTimer A = new AnimationTimer() {
            @Override
            public void handle(long now) {
//            	Score.setText("Score : "+score.toString());
            	gameplay(play);
            }
        };
        A.start();


        Button btn6=new Button("Quit Game");
        btn6.setLayoutX(190);
        btn6.setLayoutY(730);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> Menu(primaryStage,imageview));

        play.getChildren().setAll(blackBackground,btn6);
        play.getChildren().addAll(snake);
        
        Score.setX(36);
        Score.setY(44);
        Score.setFill(Color.WHITE);
        Score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        
        play.getChildren().addAll(blocks);
        play.getChildren().addAll(Score);

        Scene scene=new Scene(play,530,800);

        scene.setOnKeyPressed(e-> {

            switch(e.getCode()){

                case A :
                    if(snake[0].getCenterX()>10){
                    	for(int i=0;i<5;i++){
                        	snake[i].setCenterX(snake[i].getCenterX()-10);
                    	}
                    }
                    break;
                case D :
                    if(snake[0].getCenterX()<520){
                    	for(int i=0;i<5;i++) {
                    		snake[i].setCenterX(snake[i].getCenterX() + 10);
                    	}
                    }
                    break;
                case LEFT :
                    if(snake[0].getCenterX()>10){
                    	for(int i=0;i<5;i++){
                    		snake[i].setCenterX(snake[i].getCenterX()-10);
                    	}
                    }
                    break;
                case RIGHT :
                    if(snake[0].getCenterX()<520){
                    	for(int i=0;i<5;i++) {
                    		snake[i].setCenterX(snake[i].getCenterX() + 10);
                    	}
                    }
                    break;
                default:
                	break;

            }});


        primaryStage.setScene(scene);

    }

    //////////////////////////////////////////////////////////////////////////////////////

    protected void Instructions(Stage primaryStage, ImageView imageview) {
        Pane instructions=new Pane();
        Text text=new Text();
        text.setText("				How To Play \n"
                + "Your have to control snake, collect food, and destroy\n"
                + "blocks to get points. The  speed of snake will increse\n"
                + "gradually.\n \n"
                + "Each food has some points which will be counted \n"
                + "towards snake length. Each Block has some value and \n"
                + "could be destroyed completely only if you have snake \n"
                + "of length more than its value. If you continue hitting \n"
                + "the block after that, you lose. \n \n"
                + "				Controls : \n "
                + "		Press -A- to turn left. \n "
                + "		Press -D- to turn right. \n \n "
                + "				Tokens : \n "
                + "There are 4 token which could help you in scoring and \n "
                + "tackling blocks. These are listed below: \n"
                + "1.Ball : Increases snake's length by its value.  \n"
                + "2.Destroy Blocks  : Destroy all blocks on screen and and \n 				 increse score by its value. \n"
                + "3.Magnet : Attract coins towards the snake. \n"
                + "4.Shield : Protects snake against any block for 5 seconds. \n");

        text.setX(70);
        text.setY(70);
        text.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR,18));	   //changed Font to Helvetica
        text.setFill(Color.BROWN);


        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(650);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> Menu(primaryStage,imageview));

        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(500);
        r.setHeight(700);
        r.setFill(Color.BEIGE);
        r.setOpacity(0.95);

        instructions.getChildren().setAll(imageview,r,text,btn6);

        Scene scene=new Scene(instructions,600,800);

        primaryStage.setScene(scene);


    }

    ////////////////////////////////////////////////////////////////////////////////////////


    protected void LeaderBoard(Stage primaryStage, ImageView imageview) {
        Pane leaderboard=new Pane();

        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);


        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(730);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> Menu(primaryStage,imageview));

        leaderboard.getChildren().setAll(blackBackground,btn6);

        Scene scene=new Scene(leaderboard,600,800);


        primaryStage.setScene(scene);


    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    protected void Themes(Stage primaryStage, ImageView imageview) {
        Pane themes=new Pane();

        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);
        
        Image temp = new Image("file:coming-soon.jpg");									// added coming-soon image
        ImageView comingSoon=new ImageView(temp);
        comingSoon.setFitHeight(820);
        comingSoon.setFitWidth(620);

        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(730);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> Menu(primaryStage,imageview));

        themes.getChildren().setAll(blackBackground,comingSoon,btn6);

        Scene scene=new Scene(themes,600,800);

        primaryStage.setScene(scene);


    }
    //////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        launch(args);
    }
}