package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * <h1>Controller Class for controlling the game</h1>
 * <li>Generates blocks, tokens and walls
 * <li>Initializes Snake
 * <li>Has methods required to support a fully functional game
 *
 */
public class Controller implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8891686975133082925L;
    /**
     * Random class object for generating random values
     */
	private static Random rand;
    /**
     * Main class object to return to the Main screen
     * @see Main
     */
//    private Main obj;
    /**
     * For controlling the speed of falling blocks
     */
    private double speed;                               //for increasing speed
    /**
     * Integer to store Player's score
     */
    private Integer score;                              // player's score
    /**
     * List which contains randomly generated blocks
     * @see Block
     */
    private List<Block> blocks;
    /**
     * List which contains random Block values
     */
    private List<Text>  blockText;
    /**
     * Text object to display the scoreboard
     */
    private Text Score;                                 // Score Board
    /**
     * Snake object
     * @see Snake
     */
    private Snake snake;
    /**
     * To check if the game is paused
     */
    private boolean paused;
    /**
     * Token object to generate tokens
     * @see Token
     */
    private Token T[];
    /**
     * Wall object to generate walls
     * @see Wall
     */
    private Wall W;
    
    private Coin C[];
    /**
     * A timer which calls {@link #gameplay}
     * <p>in each frame to check collision, update score, check boundary, and generate tokens
     */
    private AnimationTimer A;
    /**
     * A variable to check if animation timer {@link #A} is running
     */
    private boolean runningA;
    /**
     * Constructor to initialize the data members
     */
    
    private LeaderBoard leaderboard;
    List <Line> burstEffect;
    public Controller(LeaderBoard L){
        this.speed=0;
        this.score=0;
        this.blocks=new ArrayList<Block>();
        this.blockText=new ArrayList<Text>();
        this.Score=new Text("Score:"+score.toString());
        this.paused=false;
        burstEffect=new ArrayList<Line>();
//        obj = new Main();
        rand=new Random();
        T=new Token[2];
        snake=new Snake();
        C=new Coin[2];
        leaderboard=L;
    }
    /**
     * A function which returns a random color object
     * <p>which is required for blocks
     * @return Color object
     */
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
            return Color.CORAL;
        else
            return Color.YELLOW;
    }
    /**
     * A function which returns a random color for wall
     * @return Color A color object
     */

    protected Color colorPicker_Wall() {
        int toss=rand.nextInt(4);
        if(toss==0) {
            return Color.GREENYELLOW;
        }
        else if(toss==1) {
            return Color.WHITE;
        }
        else if(toss==2) {
            return Color.WHEAT;
        }
        else {
            return Color.YELLOW;
        }   
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is required to move a text object
     * @param text This is the text which we need to move
     * @param speed Denotes the speed of the moving text
     */
    void moveText(Text text, double speed) {
        text.setTranslateY(text.getTranslateY() + 1 + speed);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Helper class for {@link #createBlocks} function
     * <p>to return Blocks and Blocks Value at the same time
     */
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
    /**
     * This function creates Blocks and their values
     * @return blockAndText object
     */
    protected blockAndText createBlocks(){                      //Random Blocks Creator
        List<Block> Blockslist=new ArrayList<Block>();
        List<Text>  BlockText =new ArrayList<Text>();
        for(int i=0;i<5;i++){
            int toss= rand.nextInt(3);
            if(toss==0 || toss ==1){
                int valueofNewBlock;
                
                if(i==1) {                                                  // for having at least one block with less value
                    System.out.println(snake.getNumBalls());
                	valueofNewBlock= rand.nextInt(snake.getNumBalls());
                }
                else {
                    valueofNewBlock= rand.nextInt(20)+1;
                }
                if(valueofNewBlock!=0) {
                	Blockslist.add(new Block( (i+1)*5+i*100,-100,96,90,valueofNewBlock,colorPicker()));
                    BlockText.add(new Text(String.valueOf(valueofNewBlock)));
                    BlockText.get(BlockText.size()-1).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
                    BlockText.get(BlockText.size()-1).setX((i+1)*5+i*100+40);
                    BlockText.get(BlockText.size()-1).setY(-50);
                }
            }
        }

        return new blockAndText(Blockslist,BlockText);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This method generates tokens randomly
     * @param play Pane which contains UI buttons and game elements
     * @see Token
     */
    protected void createToken(Pane play) {
        int p=rand.nextInt(20);
        for(int i=0;i<2;i++) {
        	if(p<2 && T[i]==null) {
                int code=rand.nextInt(8);
               // code=8;
                if(code<=4) {
                    Token temp=new Ball("ball","file:ball.png");
                    if(temp.isOverlapping(blocks,T,W)==false) {
                    	T[i]=temp;
                    }
                	
                }
                else if(code==5) {
                    Token temp=new Destroy_Blocks("destroy","file:destroy.png");
                    if(temp.isOverlapping(blocks,T,W)==false) {
                    	T[i]=temp;
                    }
                }
                else if(code==6) {
                    Token temp=new Shield("shield","file:shield.png");
                    if(temp.isOverlapping(blocks,T,W)==false) {
                    	T[i]=temp;
                    }
                }
                else {
                    Token temp=new Magnet("magnet","file:magnet.png");
                    if(temp.isOverlapping(blocks,T,W)==false) {
                    	T[i]=temp;
                    }
                }
                if(T[i]!=null) {
	                play.getChildren().add(T[i].getPhoto());
	                if(T[i] instanceof Ball) {
	                    play.getChildren().add(((Ball) T[i]).getballText());
	                }
                } 
            }
        }
        
    }
    /**
     * This method creates walls randomly
     * @param play Pane where the walls are added
     * @see Wall
     */
    protected void createWall(Pane play) {
        int p=rand.nextInt(10);
        if(p<2 && W==null) {
            Wall temp=new Wall(colorPicker_Wall());
            if(temp.isOverlapping(blocks,T)==false) {
            	W=temp;
            	play.getChildren().add(W.getLine());
            }
        }
    }
    
    protected void createCoin(Pane play) {
    	
    	for(int i=0;i<2;i++) {
    		int p=rand.nextInt(20);
    		if (p<4 && C[i]==null){
    			Coin temp=new Coin("file:coin.png");
    			if(temp.isOverlapping(blocks, T, W,C)==false) {
    				C[i]=temp;
    				play.getChildren().add(C[i].getPhoto());
    				play.getChildren().add(C[i].getCoinText());
    			}
    		}
    	}
    }

    /**
     * This method is called in every frame.It controls the whole game.
     * <li>Updates the score
     * <li>Generates and moves the blocks down
     * <li>Operates the timer for {@link Shield}
     * @param play Pane in which blocks,snake,tokens,walls and UI Elements are present
     * @param r UI Panel which contains buttons
     * @param Quit Quit Button
     * @param Pause Pause Button
     * @param Restart Restart Button
     */
    protected void gameplay(Pane play,Rectangle r,ChoiceBox<String> dropdown,Button Click, Stage primaryStage, ImageView imageview) {
        Score.setText("Score:"+score.toString());
        int shieldFlag=0;
        int magnetFlag=0;
//        System.out.println("ShieldFlag : "+shieldFlag);
//        System.out.println(blocks.size()+" "+blocksText.size());

        if(blocks.isEmpty()==false && blocks.get(0).getTranslateY()>500 && burstEffect.isEmpty()==false){
            play.getChildren().removeAll(burstEffect);
            burstEffect.clear();

        }
        for(int i=0;i<blocks.size();i++) {
            blocks.get(i).moveDown(speed);
            moveText(blockText.get(i),speed);
        }
        
        for(int i=0;i<2;i++) 
        {
            if(T[i]!=null && T[i] instanceof Shield) 					// if the token is shield
            {
                if(((Shield)T[i]).isAlive==true){
                    if(System.currentTimeMillis()-((Shield) T[i]).getStart()<5000) {
                        ((Shield) T[i]).getTimer().setText("Shield:"+String.valueOf(5-(System.currentTimeMillis()-((Shield) T[i]).getStart())/1000));
                        shieldFlag=1;
                    }
                    else {
                        shieldFlag=0;
                        play.getChildren().remove(((Shield)T[i]).getTimer());
//                      System.out.println("Over");
                        ((Shield)T[i]).getTimer().setVisible(false);
                        T[i]=null;
                    }                  
                }
                else {
                    T[i].moveDown(speed);
                }
            }
            
            if(T[i]!=null && T[i] instanceof Magnet) {							// if the token is magnet
            	if(((Magnet)T[i]).isAlive==true){
//            		System.out.println("Magnet Working");
//            		System.out.println("Elapsed : " + (System.currentTimeMillis()-((Magnet) T).getStart()));
    	        	if(System.currentTimeMillis()-((Magnet) T[i]).getStart()<8000) {
//    	        		System.out.println("Under 8");
    	                ((Magnet) T[i]).getTimer().setText("Magnet:"+String.valueOf(8-(System.currentTimeMillis()-((Magnet) T[i]).getStart())/1000));
    	                magnetFlag=1;
    	                ((Magnet)T[i]).getRange().setCenterX(snake.getBody().get(0).getCenterX());
    	                ((Magnet)T[i]).getRange().setCenterY(snake.getBody().get(0).getCenterY());
    	            }
    	            else {
    	                magnetFlag=0;
    	                play.getChildren().remove(((Magnet)T[i]).getTimer());
    	                play.getChildren().remove(((Magnet)T[i]).getRange());
    	                System.out.println("magnet duration over");
    	                ((Magnet)T[i]).getTimer().setVisible(false);
    	                ((Magnet)T[i]).getRange().setVisible(false);
    	                T[i]=null;
    	            }
    	        }
                else {
            		T[i].moveDown(speed);
            	}
            }
//            System.out.println("ShieldFlag : "+shieldFlag);
            if(T[i]!=null && (T[i] instanceof Shield) == false && (T[i] instanceof Magnet) == false) { // if the token is ball or bomb                                      // if token is on screen
                T[i].moveDown(speed);
                if( T[i] instanceof Ball) {
                    ((Ball) T[i]).moveBallText(speed);
                }
            }
        }
        
        if(W!=null) {                                           // if wall is on screen
            W.moveDown(speed);
        }
        for(int i=0;i<2;i++) {
        	if(C[i]!=null) {
        		C[i].moveCoinText(speed);
        		C[i].moveDown(speed);
        	}
        }
        
        List<Block> newBlocks=null;            					//so that it is automatically destroyed after each execution
        List<Text>  newBlocksValue=null;

        if(blocks.isEmpty() || blocks.get(blocks.size()-1).getTranslateY()>500) { //distance between two rows of blocks is 500
            blockAndText obj= createBlocks();
            newBlocks= obj.blockShape;
            newBlocksValue =obj.blockText;
            for(int i=0;i<newBlocks.size();i++){
                blocks.add(newBlocks.get(i));
                blockText.add(newBlocksValue.get(i));
                play.getChildren().add(newBlocks.get(i));
                play.getChildren().add(newBlocksValue.get(i));

            }

            speed+=0.10;
//            System.out.println("Speed : "+speed);
        }
        
        checkCollision(play,shieldFlag, magnetFlag, primaryStage, imageview);
        checkBoundary(play);
        createToken(play);
        createWall(play);
        createCoin(play);
//        checkSnakeBalls(play, primaryStage, imageview);
        r.toFront();
//        Quit.toFront();             //toFront means always in front of all other nodes.
        Click.toFront();
        dropdown.toFront();
//        Restart.toFront();
        Score.toFront();
    }
    
    /**
     * This method keeps a track of the length of snake
     * <p>If it becomes less than 1 , game ends
     * @param play Pane in which the game elements are present.
     */
    protected void checkSnakeBalls(Pane play, Stage primaryStage, ImageView imageview) {
        if(snake.getNumBalls()<1) {
            A.stop();
            runningA=false;
            gameOver(play, primaryStage, imageview);
        }      
    }
     protected void pauseAnimationTimer(Pane play,int snakeNumBalls,int BlockValue){
        A.stop();
        long startWaiting=System.currentTimeMillis();
        int count=100;
        while(BlockValue>0 || snakeNumBalls>0){
            if(System.currentTimeMillis()-startWaiting>count)
            {
                
                count+=100;
                BlockValue--;
                snakeNumBalls--;
            }

        }
        A.start();
    }

    protected void decreaseSnakeLength(int shieldFlag,int blockVal){
        //Decreasing snake's length
        if(shieldFlag==0) {
            for (int k = 0; k <blockVal; k++) {
                if (snake.getBody().size() > 1) {
                    snake.getBody().get(snake.getBody().size() - 1).setVisible(false);
                    snake.getBody().remove(snake.getBody().size() - 1);
                }
                else break;
            }
        }

    }
    /**
     * This method generates a message when the game ends.
     * <p>It shows the score made by the user
     * @param play Pane in which game elements are present.
     */
    protected void gameOver(Pane play, Stage primaryStage, ImageView imageview) {
    	leaderboard.addScore(new Score(score));
//    	System.out.println(dateFormat.format(date));
    	Text t= new Text("Game Over \n \nYour Score \n  "+score.toString());
        t.setX(200);
        t.setY(240);
        t.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        Rectangle r=new Rectangle(200,200,Color.WHITE);
        r.setX(180);
        r.setY(200);
        r.setOpacity(0.4);
        play.getChildren().addAll(r,t);
//        Scene scene=new Scene(play,530,800);
//        primaryStage.setScene(scene);
    }

    /**
     * This method checks the collision of different objects with Snake
     * <p>Checks collision with Tokens,Walls and Blocks
     * @param play Pane in which game elements are present.
     * @param shieldFlag A variable which tells whether the Shield is alive or not.
     */
    protected void checkCollision(Pane play, int shieldFlag, int magnetFlag, Stage primaryStage, ImageView imageview) {
        for(int i=0;i<blocks.size();i++) 
        {  	
        	//Collision Check
            if(blocks.get(i).getBoundsInParent().intersects(snake.getBody().get(0).getBoundsInParent()) & blocks.get(i).getAlive()==true)
            {     if(blocks.get(i).getblockValue()>5){
                pauseAnimationTimer(play,snake.getNumBalls(),blocks.get(i).getblockValue());
            }
                double xcoor=blocks.get(i).getTranslateX();
                double ycoor=blocks.get(i).getTranslateY();
                burstEffect.add(new Line(xcoor+90,ycoor+20,xcoor+110,ycoor));
                burstEffect.add(new Line(xcoor+20,ycoor+20,xcoor,ycoor));
                burstEffect.add(new Line(xcoor+50,ycoor+20,xcoor+50,ycoor));
                burstEffect.add(new Line(xcoor+20,ycoor+50,xcoor,ycoor+50));
                burstEffect.add(new Line(xcoor+90,ycoor+50,xcoor+110,ycoor+50));
                for(int b=0;b<burstEffect.size();b++){
                    burstEffect.get(b).setStroke(Color.WHITE);
                    burstEffect.get(b).setStrokeWidth(2);
                }
                play.getChildren().addAll(burstEffect);
                if(shieldFlag==1) {
                    score+=blocks.get(i).getblockValue();
                }
                else if(snake.getNumBalls()>=blocks.get(i).getblockValue()) {
                    score+=blocks.get(i).getblockValue();
                    snake.setNumBalls(-blocks.get(i).getblockValue());
                    snake.setScoreText();
                }
                else {
                    snake.setNumBalls(-blocks.get(i).getblockValue());
                    checkSnakeBalls(play, primaryStage, imageview);
                }

                decreaseSnakeLength(shieldFlag, blocks.get(i).getblockValue());

            

                blocks.get(i).setAlive(false);
                blocks.get(i).setVisible(false);
                play.getChildren().remove(blocks.get(i));
                blocks.remove(i);

                blockText.get(i).setVisible(false);
                play.getChildren().remove(blockText.get(i));
                blockText.remove(i);

//                  System.gc();

            }
        }

           for(int j=0;j<2;j++) {
        	   if(T[j]!=null && ((T[j] instanceof Shield)==true) && ((Shield)T[j]).isAlive==true){          
        		   //left blank intentionally
                   
               }
               else if(T[j]!=null && ((T[j] instanceof Magnet)==true) && ((Magnet)T[j]).isAlive==true) {
            	   for(int i=0;i<2;i++) {
            		   if((C[i]!=null && ((Magnet) T[j]).getRange().getBoundsInParent().intersects(C[i].getPhoto().getBoundsInParent()))){
            			   score+=C[i].getValue();
                           System.out.println("Magnet Effect");
                          
                           
                           C[i].getPhoto().setVisible(false);
                           C[i].getCoinText().setVisible(false);
                           play.getChildren().remove(C[i].getPhoto());
                           play.getChildren().remove(C[i].getCoinText());
                           C[i]=null; 
            		   }
               		}
               }
               else if(T[j]!=null && T[j].getPhoto().getBoundsInParent().intersects(snake.getBody().get(0).getBoundsInParent())){
                   if(T[j] instanceof Ball) 
                   {
                       ((Ball) T[j]).increaseBalls(snake);
                       ((Ball) T[j]).getballText().setVisible(false);

                       for(int k=0;k<((Ball) T[j]).getValue();k++)
                       {
                           snake.getBody().add(new Circle(snake.getBody().get(snake.getBody().size() - 1).getCenterX(), snake.getBody().get(snake.getBody().size() - 1).getCenterY() + 20, 10));
                           snake.getBody().get(snake.getBody().size() - 1).setFill(Color.YELLOW);
                           play.getChildren().add(snake.getBody().get(snake.getBody().size() - 1));
                       }
                   }
                   else if(T[j] instanceof Destroy_Blocks) {
                       score=score+((Destroy_Blocks) T[j]).destroyBlocks(play,blocks,blockText);
                   }
                   else if(T[j] instanceof Shield) {
                       ((Shield) T[j]).protectSnake();
                       play.getChildren().add(((Shield) T[j]).getTimer());
                       if(shieldFlag==1) {
                    	   if(j==0) {
                    		   System.out.println("Old shield Removed");
                    		   play.getChildren().remove(((Shield)T[j+1]).getTimer());
                               ((Shield)T[j+1]).getTimer().setVisible(false);
                    		   T[j+1]=null;
                    	   }
                    	   else {
                    		   System.out.println("Old shield Removed");
                    		   play.getChildren().remove(((Shield)T[j-1]).getTimer());
                               ((Shield)T[j-1]).getTimer().setVisible(false);
                    		   T[j-1]=null;
                    	   }
                       }

                   }
                   else {
//                	   System.out.println("Collided with Magnet");
                       ((Magnet) T[j]).attractCoins(snake);
                       play.getChildren().add(((Magnet) T[j]).getTimer());
                       play.getChildren().add(((Magnet) T[j]).getRange());
                       if(magnetFlag==1) {
                    	   if(j==0) {
                    		   System.out.println("Old magnet removed");
                    		   play.getChildren().remove(((Magnet)T[j+1]).getTimer());
                    		   play.getChildren().remove(((Magnet)T[j+1]).getRange());
           	                   ((Magnet)T[j+1]).getTimer().setVisible(false);
        	                   ((Magnet)T[j+1]).getRange().setVisible(false);
        	                   T[j+1]=null;
                    	   }
                    	   else {
                    		   System.out.println("Old magnet removed");
                    		   play.getChildren().remove(((Magnet)T[j-1]).getTimer());
                    		   play.getChildren().remove(((Magnet)T[j-1]).getRange());
           	                   ((Magnet)T[j-1]).getTimer().setVisible(false);
        	                   ((Magnet)T[j-1]).getRange().setVisible(false);       
        	                   T[j-1]=null;
                    	   }
                       }
                   }
                   T[j].getPhoto().setVisible(false);
                   play.getChildren().remove(T[j].getPhoto());
                   if( (T[j] instanceof Shield == false) && (T[j] instanceof Magnet == false)) {
                       T[j]=null;
                   }         
               }
//               if( magnetFlag==1 && T[j] instanceof Magnet ) {
//            	   
//            
//            	
//               }
           }
            
            if(W!=null && W.getLine().getBoundsInParent().intersects(snake.getBody().get(0).getBoundsInParent())){   //walls working
//              W.getLine().setVisible(false);  
//              play.getChildren().remove(W);
//              W=null;
//              System.out.println(W.getLine().getEndX()+" , "+snake.getBody().get(0).getCenterX() );
                if(W.getLine().getEndX()>snake.getBody().get(0).getCenterX()) {
                    snake.moveLeft();
                }
                else {
                    snake.moveRight();
                }                
            }
            for(int i=0;i<2;i++) {
            	if(C[i]!=null && C[i].getPhoto().getBoundsInParent().intersects(snake.getBody().get(0).getBoundsInParent())) {
            		score+=C[i].getValue();
                    System.out.println("Collided with coin");
                   
                    
                    C[i].getPhoto().setVisible(false);
                    C[i].getCoinText().setVisible(false);
                    play.getChildren().remove(C[i].getPhoto());
                    play.getChildren().remove(C[i].getCoinText());
                    C[i]=null; 
            	}
            }
        }
    /**
     * This method checks the boundary to remove the falling objects from the pane
     * @param play Pane from which the objects (which crossed the boundary) needs to be removed.
     */

    protected void checkBoundary(Pane play) {
        for(int i=0;i<blocks.size();i++) {
//          System.out.println(blocks.get(i).getManualY());
            if(blocks.get(i).getManualY()>820) {                        // checking if block passed the snake
                blocks.get(i).setVisible(false);
                blocks.get(i).setAlive(false);
                play.getChildren().remove(blocks.get(i));
                blocks.remove(i);
                blockText.get(i).setVisible(false);
                play.getChildren().remove(blockText.get(i));
                blockText.remove(i);
            }
        }
        
        for(int i=0;i<2;i++) {
        	if(T[i]!=null && T[i].getManualY()>900) {
//              System.out.println("Removed Token");
                T[i].getPhoto().setVisible(false);
                if(T[i] instanceof Ball){
                    ((Ball) T[i]).getballText().setVisible(false);
                }
                play.getChildren().remove(T[i].getPhoto());
                T[i]=null;
            }
        }
        
        
        if(W!=null && W.getManualY()>900) {
//          System.out.println("Removed Wall");
            W.getLine().setVisible(false);
            play.getChildren().remove(W.getLine());
            W=null;
        }
    }

    /**
     * This method initializes the game and controls movement of snake
     * <ol>Initializes the snake
     * <ol>Creates UI elements like buttons
     * <ol>Creates an AnimationTimer which calls {@link #gameplay(Pane, Rectangle, Button, Button, Button)}
     * @param primaryStage
     * @param imageview
     */
    protected void Play(Main M,Stage primaryStage, ImageView imageview) {
    	System.out.println("Playing");
    	
    	//////////////////////
    	this.speed=0;
        this.score=0;
        this.blocks=new ArrayList<Block>();
        this.blockText=new ArrayList<Text>();
        this.Score=new Text("Score:"+score.toString());
        this.paused=false;
        T=new Token[2];
        snake=new Snake();
        C=new Coin[2];
        ///////////////
        Pane play=new Pane();
        speed=0;
        snake=new Snake();
        snake.getBody().clear();
        blocks.clear();
        blockText.clear();
        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);

        snake.getBody().add(new Circle(270,600,12));
        snake.getBody().get(0).setFill(Color.WHITE);
        snake.setScoreText();
        for(int i=1;i<4;i++){
            snake.getBody().add(new Circle(270,600+i*20,10));
            snake.getBody().get(i).setFill(Color.YELLOW);
        }

        Rectangle rectanglePanel = new Rectangle();          // panel for score, play,pause,resume
        rectanglePanel.setX(10);
        rectanglePanel.setY(10);
        rectanglePanel.setWidth(290);
        rectanglePanel.setHeight(50);
        rectanglePanel.setFill(Color.WHITE);
        rectanglePanel.setOpacity(0.80);
              
        
        Button dropbtn=new Button("Click");
        dropbtn.setLayoutX(240);
        dropbtn.setLayoutY(15);
        dropbtn.setMinSize(30, 30);
        dropbtn.setStyle("-fx-font: 14 arial; -fx-base: #FE2E2E;");
        
        ChoiceBox<String> dropdown= new ChoiceBox<>(); 
        dropdown.setLayoutX(140);
        dropdown.setLayoutY(15);
        dropdown.getItems().addAll("Pause","Restart","Quit");
        dropdown.setValue("Pause");
        
        dropbtn.setOnAction(e->{
        	String choice=dropdown.getValue();
        	if(choice.equals("Pause")) {
        		 if(this.paused==false)
                 {   this.paused=true;
                     A.stop();
                 }
                 else if(this.paused==true){
                     this.paused=false;
                     A.start();
                 }
        	}
        	else if(choice.equals("Restart")){
        		M.restart(primaryStage,imageview,this);
        	}
        	else {
        		 M.Menu(primaryStage,imageview);
        	}
        });

        A = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameplay(play,rectanglePanel,dropdown,dropbtn,primaryStage, imageview);
            }
        };
        A.start();
        runningA=true;

        play.getChildren().setAll(blackBackground,rectanglePanel,dropbtn,dropdown);
        play.getChildren().addAll(snake.getBody());
        play.getChildren().add(snake.getScore());


        Score.setX(15);
        Score.setY(44);
        Score.setFill(Color.RED);
        Score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        play.getChildren().addAll(blocks);
        play.getChildren().addAll(Score);
        

        Scene scene=new Scene(play,530,800);

        scene.setOnKeyPressed(e-> {

            switch(e.getCode()){

                case A :
                    if(snake.getBody().get(0).getCenterX()>10 && runningA==true){
                        snake.moveLeft();
                    }
                    break;
                case D :
                    if(snake.getBody().get(0).getCenterX()<520 && runningA==true){
                        snake.moveRight();
                    }
                    break;
                case LEFT :
                    if(snake.getBody().get(0).getCenterX()>10 && runningA==true){
                        snake.moveLeft();
                    }
                    break;
                case RIGHT :
                    if(snake.getBody().get(0).getCenterX()<520 && runningA==true){
                        snake.moveRight();
                    }
                    break;
                default:
                    break;

            }});

        primaryStage.setScene(scene);

    }
}