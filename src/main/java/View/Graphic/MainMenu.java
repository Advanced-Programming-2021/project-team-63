package View.Graphic;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainMenu extends Application {


    private LoginMenu loginMenu;


    @Override
    public void start(Stage stage) throws Exception {

    }


    public void setPriorMenu(LoginMenu loginMenu){this.loginMenu=loginMenu;}

}
