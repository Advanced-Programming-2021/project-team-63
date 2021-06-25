import Controller.ProgramController;
import Model.Game.Card.GameLogType;
import Model.Game.Phase;
import Model.JsonObject.CardJson;
import Model.JsonObject.MonsterJson;
import Model.JsonObject.SpellAndTrapJson;
import View.Menu;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.loginMenu();
    }
}
