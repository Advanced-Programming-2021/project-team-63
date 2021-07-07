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

import javax.print.attribute.standard.Media;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    static Stage primaryStage;
    GridPane grid = new GridPane();
    Button button = new Button("start");
    LoginMenu loginMenu=new LoginMenu();




    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        BorderPane root = new BorderPane();
        primaryStage.setTitle("PAC-MAN");
        primaryStage.getIcons().add(new Image(getClass().getResource("resources/logo.png").toExternalForm()));
        Scene scene = new Scene(root, 948, 533.333);

        Image backGroundImage = new Image(getClass().getResource("resources/pacmanbaackg.jpg").toExternalForm());
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,
                false, true, false);
        InputStream startGifPath = new FileInputStream("D:\\untitled11\\src\\sample\\resources\\pacgif2.gif");

        Image startGifImage = new Image(startGifPath);
        ImageView StartGifImageView = new ImageView();


        // set background for scene


        root.setBackground(new Background(new BackgroundImage(backGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        primaryStage.setScene(scene);

        //set gif for first scene


        StartGifImageView.setImage(startGifImage);
        StartGifImageView.setFitHeight(300);
        StartGifImageView.setFitWidth(400);
        root.setCenter(StartGifImageView);

        /// set start button


        InputStream startButtonPicPath = new FileInputStream("D:\\untitled11\\src\\sample\\resources\\start.png");

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

       /* Media media = new Media(getClass().getResource("resources/pacman_beginning.wav").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(2);

        mediaPlayer.play();

        */




    }
}
