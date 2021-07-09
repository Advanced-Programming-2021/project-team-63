package sample.View.Graphic;

//import View.Graphic.ProfileMenu;
import View.Graphic.LoginMenu;
import View.Graphic.ShopMenu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.InputStream;

public class MainMenu extends Application {


    public   String nickname;
    public Button logOutButton = new Button("logout");
    public Label title = new Label();
    public HBox optionsBox = new HBox();
    public String username;
    LoginMenu loginMenu;

  //  public CardMenu cardMenu=new CardMenu();
  //  public ProfileMenu profileMenu=new ProfileMenu();
  //  public ScoreBoardMenu scoreBoardMenu=new ScoreBoardMenu();
  //  public ShopMenu shopMenu=new ShopMenu();

    public MainMenu(String username,String nickname){
        this.username=username;
        this.nickname=nickname;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        //set scene and more

        BorderPane root = new BorderPane();
        root.setBottom(logOutButton);
        root.setCenter(optionsBox);
        title.setText("Main");


        //set background



        InputStream stream = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\" +
                "resources\\Database\\Assets\\1300534.jpg");
        Image backGroundImage = new Image(stream);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,
                false, true, false);
        root.setBackground(new Background(new BackgroundImage(backGroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));


        //set app logo

        InputStream startGifPath = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\resources\\Database\\" +
                "Assets\\Logos\\_images_text_yugioh.dds2.png");
        Image image = new Image(startGifPath);
        ImageView i = new ImageView(image);
        BorderPane.setAlignment(i,Pos.CENTER);

        root.setTop(i);




        primaryStage.setTitle("YO-GUY-OH");
        Scene scene = new Scene(root, 900, 550);


        primaryStage.setScene(scene);
        primaryStage.show();


        //set buttons with images


        Rectangle shopButton = new Rectangle();
        Rectangle cardButton = new Rectangle();
        Rectangle scoreboardButton = new Rectangle();
        Rectangle importExportButton = new Rectangle();
        Rectangle duelButton = new Rectangle();
        Rectangle profileButton = new Rectangle();

        shopButton.setWidth(100);
        shopButton.setHeight(100);

        cardButton.setWidth(100);
        cardButton.setHeight(100);

        scoreboardButton.setWidth(100);
        scoreboardButton.setHeight(100);

        profileButton.setWidth(100);
        profileButton.setHeight(100);

        importExportButton.setWidth(100);
        importExportButton.setHeight(100);

        duelButton.setWidth(100);
       duelButton.setHeight(100);

        /////////////////////////////////////////load images

        InputStream stream1 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\" +
                "main\\resources\\Database\\Assets\\shop.png");

        Image image1 = new Image(stream1);
        ImagePattern img1 = new ImagePattern(image1);

        InputStream stream2 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\" +
                "src\\main\\resources\\Database\\Assets\\Icons\\card.png");

        Image image2 = new Image(stream2);
        ImagePattern img2 = new ImagePattern(image2);


        InputStream stream3 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\" +
                "src\\main\\resources\\Database\\Assets\\scorboard.png");

        Image image3 = new Image(stream3);
        ImagePattern img3 = new ImagePattern(image3);

        InputStream stream4 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\" +
                "src\\main\\resources\\Database\\Assets\\account.png");

        Image image4 = new Image(stream4);
        ImagePattern img4 = new ImagePattern(image4);

        InputStream stream5 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\resources\\Database\\" +
                "Assets\\import-export-1780274-1513718.png");

        Image image5 = new Image(stream5);
        ImagePattern img5 = new ImagePattern(image5);


        InputStream stream6 = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\Phase2_MGH\\src\\main\\" +
                "resources\\Database\\Assets\\download.jfif");

        Image image6 = new Image(stream6);
        ImagePattern img6 = new ImagePattern(image6);


        /////////////////////////////////////////

        shopButton.setFill(img1);
        cardButton.setFill(img2);
        scoreboardButton.setFill(img3);
        profileButton.setFill(img4);
        importExportButton.setFill(img5);
        duelButton.setFill(img6);
        optionsBox.getChildren().addAll(shopButton, cardButton, scoreboardButton, profileButton,importExportButton,duelButton);
        optionsBox.setAlignment(Pos.CENTER);
        optionsBox.setSpacing(20);
        optionsBox.setPadding(new Insets(10, 10, 10, 10));
        optionsBox.setStyle("-fx-background-color: lightblue;-fx-max-height: 120;-fx-max-width: 120");

        BorderPane.setAlignment(optionsBox, Pos.CENTER);


        //////////////////////////////////////////buttons actions

        shopButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               //set priorer
                try {
                    ShopMenu shopMenu=new ShopMenu();
                    shopMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        shopButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopButton.setEffect(new DropShadow());
            }
        });

        shopButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopButton.setEffect(null);
            }

        });

        cardButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cardButton.setEffect(new DropShadow());
            }
        });

        cardButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cardButton.setEffect(null);
            }

        });

        cardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //set prior
                try {
                    CardMenu cardMenu=new CardMenu();
                    cardMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scoreboardButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scoreboardButton.setEffect(new DropShadow());
            }
        });

        scoreboardButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scoreboardButton.setEffect(null);
            }

        });
        scoreboardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //set prior
                try {
                    ScoreBoardMenu scoreBoardMenu=new ScoreBoardMenu();
                    scoreBoardMenu.setPriorMenu(new MainMenu(username,nickname));
                    scoreBoardMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        profileButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                profileButton.setEffect(new DropShadow());
            }
        });

        profileButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                profileButton.setEffect(null);
            }

        });


        profileButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //set prior
                try {
                    ProfileMenu profileMenu=new ProfileMenu();
                    profileMenu.setPriorMenu(new MainMenu(username,nickname));
                    profileMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });









        duelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //set proir

            }
        });
        duelButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                duelButton.setEffect(new DropShadow());
            }
        });

        duelButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                duelButton.setEffect(null);
            }

        });



        importExportButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //
            }
        });

        importExportButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                importExportButton.setEffect(new DropShadow());
            }
        });

        importExportButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                importExportButton.setEffect(null);
            }

        });




//////////////////////////////////////////////////////////////
        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginMenu.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        logOutButton.setPadding(new Insets(20));
logOutButton.setStyle("-fx-padding: 20");
        BorderPane.setAlignment(logOutButton, Pos.CENTER);
       // BorderPane.setAlignment(i, Pos.CENTER);









    }


    public void setPriorMenu(LoginMenu loginMenu){this.loginMenu=loginMenu;}

    /*

    public boolean startDuel(String command) throws Exception {

        DuelNewGame duelNewGame = new DuelNewGame();
        DuelNewGame duelNewGame1 = (DuelNewGame) duelNewGame.run(command);

        if (duelNewGame1.secondPlayerUsername != null) {

            if ( duelNewGame1.ai) System.out.println("invalid command");

            else {
                Integer round = duelNewGame1.round;
                JSONObject response = js_Pass("command", "duel_new_game", "Opponent", duelNewGame1.secondPlayerUsername,
                        "round", Integer.toString( duelNewGame1.round), "opponent_type", "sec_player");

                if (response.get("type").equals("error")) System.out.println(response.get("message"));

                else {
                    System.out.println(response.get("message"));
                    return true;
                }
            }
        } else if ( ! duelNewGame1.ai) System.out.println("invalid command");

        else {
            Integer round = duelNewGame1.round;
            JSONObject response = js_Pass("command", "duel_new_game", "Opponent",  Boolean.toString(duelNewGame1.ai),
                    "round", Integer.toString(duelNewGame1.round), "opponent_type", "ai");

            if (response.get("type").equals("error")) System.out.println(response.get("message"));

            else {
                System.out.println(response.get("message"));
                duelBoardMenu();
            }
        }


        return false;
    }

     */


    }

