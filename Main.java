package Game;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group group = new Group();                                                      //Changed root to group
        
        // set icon
        primaryStage.getIcons().add(new Image("file:Snake-icon.png"));;
        
        Image image = new Image("file:snake-vs-block.png");
        ImageView imageview=new ImageView(image);
        imageview.setFitHeight(820);
        imageview.setFitWidth(620);

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);
        text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.BROWN);

        Button btn=new Button("Press ENTER");
        btn.setLayoutX(85);
        btn.setLayoutY(450);
        btn.setMinSize(200, 50);
        btn.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");
        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Menu(primaryStage, imageview);
            }
        };

        // when button is pressed
        btn.setOnAction(event);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), btn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.6);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

        group.getChildren().addAll(imageview,text,btn);

        Scene scene = new Scene(group, 600,800);
        
        primaryStage.setTitle("SnakeVsBlock");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }


    private void Menu(Stage primaryStage, ImageView imageview) {

        Group subroot = new Group();

//        Image image = new Image("file:snake-vs-block.png");
//        ImageView imageview=new ImageView(image);
//        imageview.setFitHeight(820);
//        imageview.setFitWidth(620);

        Text text = new Text();
        text.setText("Snake \n   vs \nBlock");
        text.setX(65);
        text.setY(250);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 75));
        text.setFill(Color.BROWN);

        Button btn1=new Button("Play");

        btn1.setLayoutX(95);
        btn1.setLayoutY(450);
        btn1.setMinSize(160, 40);
        btn1.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn2=new Button("LeaderBoard");

        btn2.setLayoutX(95);
        btn2.setLayoutY(510);
        btn2.setMinSize(160, 40);
        btn2.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn3=new Button("Themes");
        btn3.setLayoutX(95);
        btn3.setLayoutY(570);
        btn3.setMinSize(160, 40);
        btn3.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn4=new Button("How to Play");
        btn4.setLayoutX(95);
        btn4.setLayoutY(630);
        btn4.setMinSize(160, 40);
        btn4.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");
        
        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Instructions(primaryStage, imageview);
            }
        };
        btn4.setOnAction(event4);

        Button btn5=new Button("Exit");
        btn5.setLayoutX(95);
        btn5.setLayoutY(690);
        btn5.setMinSize(160, 40);
        btn5.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");
        
        EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Platform.exit();
            }
        };
        btn5.setOnAction(event5);
       

        subroot.getChildren().addAll(imageview,text,btn1,btn2,btn3,btn4,btn5);
        Scene scene=new Scene(subroot,600,800);
//    	primaryStage.setTitle("SnakeVsBlock");

        primaryStage.setScene(scene);

//        primaryStage.setResizable(false);
//        primaryStage.show();

    }



	protected void Instructions(Stage primaryStage, ImageView imageview) {
		Group instructions=new Group();
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
				+ "		Press left to turn left. \n "
				+ "		Press right to turn right. \n \n "
				+ "				Tokens : \n "
				+ "There are 4 token which could help you in scoring and \n "
				+ "tackling blocks. These are listed below: \n"
				+ "1.  \n"
				+ "2.  \n"
				+ "3.  \n"
				+ "4.  \n");
		
		text.setX(70);
		text.setY(70);
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,15));
        text.setFill(Color.BROWN);
        
        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(500);
        r.setHeight(700);
        r.setFill(Color.BEIGE);
        r.setOpacity(10);
		
		instructions.getChildren().setAll(imageview,r,text);
		
		Scene scene=new Scene(instructions,600,800);
		primaryStage.setScene(scene);
		
	}


	public static void main(String[] args) {

        launch(args);
        int[] A=new int[2000000000];

    }


}