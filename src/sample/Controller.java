package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    Random rand=new Random();
    Main obj= new Main();
    double speed;                               //for increasing speed
    Integer score;		                        // player's score
    private List<Block> blocks;
    private List<Text>  blockText;
    Text Score;                             	// Score Board
    List<Circle> snake=new ArrayList<Circle>();
    int blockTimer;                          // timer for creating new blocks on the screen
    int scoreTracker;                        // to keep track of score to increase length of snake
    boolean paused;

    public Controller(){
        this.speed=0;
        this.score=0;
        this.scoreTracker=0;
        this.blocks=new ArrayList<Block>();
        this.blockText=new ArrayList<Text>();
        this.Score=new Text("Score : "+score.toString());
        this.paused=false;
    }


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



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void gameplay(Pane play) {
        Score.setText("Score : "+score.toString());
        for(int i=0;i<blocks.size();i++) {
            blocks.get(i).moveDown(speed);
            moveText(blockText.get(i),speed);

        }
        List<Block> newBlocks=null;            //so that it is automatically destroyed after each execution
        List<Text>  newBlocksValue=null;

        if(blocks.isEmpty() || blocks.get(blocks.size()-1).getTranslateY()>250) { //distance between two rows of blocks is 250
            blockAndText obj= createBlocks();
            newBlocks= obj.blockShape;
            newBlocksValue =obj.blockText;
            for(int i=0;i<newBlocks.size();i++){
                blocks.add(newBlocks.get(i));
                blockText.add(newBlocksValue.get(i));
                play.getChildren().add(newBlocks.get(i));
                play.getChildren().add(newBlocksValue.get(i));
            }

            speed+=0.15;             // we have to sync speed with rate of fall of blocks
            System.out.println(speed);
        }
        checkCollision(play);
        checkBoundary(play);

    }


    protected void checkCollision(Pane play) {
        for(int i=0;i<blocks.size();i++) {
            if(blocks.get(i).getBoundsInParent().intersects(snake.get(0).getBoundsInParent()) & blocks.get(i).getAlive()==true){    //Collision Check

                score+=blocks.get(i).getblockValue();
                if(snake.size()<18) {
                    if (score - scoreTracker > 20)             // This is of course not the condition to increase snake's length
                    {                            // These things will be used when coins are added

                        for (int j = 0; j < snake.size(); j++) {
                            snake.get(j).setCenterY(snake.get(j).getCenterY() - 20);
                        }

                        snake.add(new Circle(snake.get(snake.size() - 1).getCenterX(), snake.get(snake.size() - 1).getCenterY() + 20, 10));
                        snake.get(snake.size() - 1).setFill(Color.YELLOW);
                        play.getChildren().add(snake.get(snake.size() - 1));
                        scoreTracker += 20;
                    }
                }

                blocks.get(i).setAlive(false);
                blocks.get(i).setVisible(false);
                play.getChildren().remove(blocks.get(i));           //may be wrong or cause of an error
                blocks.remove(i);


                blockText.get(i).setVisible(false);
                play.getChildren().remove(blockText.get(i));           //may be wrong or cause of an error
                blockText.remove(i);

//    	    		System.gc();
            }
        }

    }

    protected void checkBoundary(Pane play) {
        for(int i=0;i<blocks.size();i++) {
//    		System.out.println(blocks.get(i).getManualY());
            if(blocks.get(i).getManualY()>800) {						// checking if block passed the snake

                blocks.get(i).setVisible(false);
                blocks.get(i).setAlive(false);
                play.getChildren().remove(blocks.get(i));           //may be wrong or cause of an error
                blocks.remove(i);
                blockText.get(i).setVisible(false);
                play.getChildren().remove(blockText.get(i));           //may be wrong or cause of an error
                blockText.remove(i);

            }
        }
    }


    protected void Play(Stage primaryStage, ImageView imageview) {

        Pane play=new Pane();
        speed=0; blockTimer=0; scoreTracker=0;
        snake.clear();
        blocks.clear();
        blockText.clear();
        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);

        snake.add(new Circle(270,610,1));
        for(int i=1;i<5;i++){
            snake.add(new Circle(270,600+i*20,10));
            snake.get(i).setFill(Color.YELLOW);
        }

        AnimationTimer A = new AnimationTimer() {
            @Override
            public void handle(long now) {
//            	Score.setText("Score : "+score.toString());
                gameplay(play);
            }
        };
        A.start();


        Button btn6=new Button("X");                    //Quit Button
        btn6.setLayoutX(230);
        btn6.setLayoutY(20);
        btn6.setMinSize(30, 30);
        btn6.setStyle("-fx-font: 24 arial; -fx-base: #FE2E2E;");
        btn6.setOnAction(e-> obj.Menu(primaryStage,imageview));

        Button btn7=new Button("| |");                  //Pause Button
        btn7.setLayoutX(170);
        btn7.setLayoutY(20);
        btn7.setMinSize(30, 30);
        btn7.setStyle("-fx-font: 24 arial; -fx-base: #FE2E2E;");
        btn7.setOnAction(e-> {
            if(this.paused==false)
            {   this.paused=true;
                btn7.setText("|>");
                A.stop();
            }
            else if(this.paused==true){
                this.paused=false;
                btn7.setText("| |");
                A.start();
            }
        });

//        HBox topPanel = new HBox(5);                //Horizontal strip in the upper side for pause,stop and score
//        topPanel.setPadding(new Insets(10));
//        topPanel.setAlignment(Pos.BASELINE_RIGHT);

        //topPanel.getChildren().addAll(btn6,btn7);
        play.getChildren().setAll(blackBackground,btn6,btn7);
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
                    if(snake.get(0).getCenterX()>10){
                        for(int i=0;i<snake.size();i++){
                            snake.get(i).setCenterX(snake.get(i).getCenterX()-10);
                        }
                    }
                    break;
                case D :
                    if(snake.get(0).getCenterX()<520){
                        for(int i=0;i<snake.size();i++) {
                            snake.get(i).setCenterX(snake.get(i).getCenterX()+10);
                        }
                    }
                    break;
                case LEFT :
                    if(snake.get(0).getCenterX()>10){
                        for(int i=0;i<snake.size();i++){
                            snake.get(i).setCenterX(snake.get(i).getCenterX()-10);
                        }
                    }
                    break;
                case RIGHT :
                    if(snake.get(0).getCenterX()<520){
                        for(int i=0;i<snake.size();i++) {
                            snake.get(i).setCenterX(snake.get(i).getCenterX()+10);
                        }
                    }
                    break;
                default:
                    break;

            }});


        primaryStage.setScene(scene);

    }

}
