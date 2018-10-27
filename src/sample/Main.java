package sample;

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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group group = new Group();                                                      //Changed root to group
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
                Menu(primaryStage);
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


    private void Menu(Stage primaryStage) {

        Group subroot = new Group();

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

        Button btn1=new Button("Play");

        btn1.setLayoutX(90);
        btn1.setLayoutY(450);
        btn1.setMinSize(160, 40);
        btn1.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn2=new Button("LeaderBoard");

        btn2.setLayoutX(90);
        btn2.setLayoutY(510);
        btn2.setMinSize(160, 40);
        btn2.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn3=new Button("Themes");
        btn3.setLayoutX(90);
        btn3.setLayoutY(570);
        btn3.setMinSize(160, 40);
        btn3.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn4=new Button("How to Play");
        btn4.setLayoutX(90);
        btn4.setLayoutY(630);
        btn4.setMinSize(160, 40);
        btn4.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        Button btn5=new Button("Exit");
        btn5.setLayoutX(90);
        btn5.setLayoutY(690);
        btn5.setMinSize(160, 40);
        btn5.setStyle("-fx-font: 20 arial; -fx-base: #ffa500;");

        subroot.getChildren().addAll(imageview,text,btn1,btn2,btn3,btn4,btn5);
        Scene scene=new Scene(subroot,600,800);
//    	primaryStage.setTitle("SnakeVsBlock");

        primaryStage.setScene(scene);

//        primaryStage.setResizable(false);

//        primaryStage.show();



    }

    public static void main(String[] args) {

        launch(args);

    }


}