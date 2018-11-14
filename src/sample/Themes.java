package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Themes {
    Main obj= new Main();
    protected void themes(Stage primaryStage, ImageView imageview) {
        Pane themepane=new Pane();

        Image image = new Image("file:blackBackground.png");
        ImageView blackBackground=new ImageView(image);
        blackBackground.setFitHeight(820);
        blackBackground.setFitWidth(620);

        Image temp = new Image("file:coming-soon.jpg");
        ImageView comingSoon=new ImageView(temp);
        comingSoon.setFitHeight(820);
        comingSoon.setFitWidth(620);

        Button btn6=new Button("Back to Menu");
        btn6.setLayoutX(220);
        btn6.setLayoutY(730);
        btn6.setMinSize(160, 40);
        btn6.setStyle("-fx-font: 20 arial; -fx-base: #87CEFA;");

        btn6.setOnAction(e-> obj.Menu(primaryStage,imageview));

        themepane.getChildren().setAll(blackBackground,comingSoon,btn6);

        Scene scene=new Scene(themepane,600,800);

        primaryStage.setScene(scene);


    }
    //////////////////////////////////////////////////////////////////////////////////////////
}
