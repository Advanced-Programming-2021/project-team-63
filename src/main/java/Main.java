package sample;

import View.Graphic.LoginMenu;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    public static Stage primaryStage;
    GridPane grid = new GridPane();
    Button button = new Button("start");
    LoginMenu loginMenu=new LoginMenu();
  //  public Stage primaryStage;




    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        BorderPane root = new BorderPane();
        primaryStage.setTitle("yo-gy-oh");
       // primaryStage.getIcons().add(new Image(getClass().getResource("resources/logo.png").toExternalForm()));
        Scene scene = new Scene(root, 800, 600);


        InputStream stream = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\resources" +
                "\\Database\\Assets\\Textures\\Campaign_11_HelpBG1.dds.png");
        Image backGroundImage = new Image(stream);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,
                false, true, false);
        root.setBackground(new Background(new BackgroundImage(backGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));





        InputStream startGifPath = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\resources\\Database\\" +
                "Assets\\Logos\\_images_text_yugioh.dds2.png");

        Image startGifImage = new Image(startGifPath);
        ImageView StartGifImageView = new ImageView();




        // set background for scene


        primaryStage.setScene(scene);

        //set gif for first scene


        StartGifImageView.setImage(startGifImage);
        StartGifImageView.setFitHeight(150);
        StartGifImageView.setFitWidth(200);
        root.setCenter(StartGifImageView);

        /// set start button


        InputStream startButtonPicPath = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\" +
                "main\\resources\\Database\\Assets\\start.png");

        Image startButtonImage = new Image(startButtonPicPath);
        ImageView startButtonImageView = new ImageView(startButtonImage);
        startButtonImageView.setFitWidth(50);
        startButtonImageView.setFitHeight(50);


        root.setBottom(startButtonImageView);

        BorderPane.setMargin(startButtonImageView, new Insets(20));

        //set action and animation hover for start button

        startButtonImageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startButtonImageView.setEffect(new DropShadow());
            }
        });


        startButtonImageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startButtonImageView.setEffect(null);
            }
        });

        startButtonImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    loginMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        BorderPane.setAlignment(root.getBottom(), Pos.CENTER);
        button.setAlignment(Pos.CENTER);


        //end


        primaryStage.show();

        //set initialize media





    }

}
