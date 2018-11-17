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

public class Instructions {
    Main obj= new Main();

    protected void instructions(Stage primaryStage, ImageView imageview) {
        Pane instr=new Pane();
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
                + "2.Destroy Blocks  : Destroy all blocks on screen and and \n 				 increase score by its value. \n"
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

        btn6.setOnAction(e-> obj.Menu(primaryStage,imageview));

        Rectangle r = new Rectangle();
        r.setX(50);
        r.setY(50);
        r.setWidth(500);
        r.setHeight(700);
        r.setFill(Color.BEIGE);
        r.setOpacity(0.95);

        instr.getChildren().setAll(imageview,r,text,btn6);

        Scene scene=new Scene(instr,600,800);

        primaryStage.setScene(scene);
    }
}
