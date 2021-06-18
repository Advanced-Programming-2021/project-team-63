import Controller.ProgramController;
import Model.Game.Card.GameLogType;
import Model.Game.Phase;
import Model.JsonObject.CardJson;
import Model.JsonObject.MonsterJson;
import Model.JsonObject.SpellAndTrapJson;
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
        //var p = new ProgramController();
        /*System.out.println(p.register("mohammad","12345","mmd").getMessage());
        System.out.println(p.login("mohammad","12345").getMessage());
        System.out.println(p.createDeck("batmanDeck").getMessage());
        System.out.println(p.buyCard("Battle OX").getMessage());
        System.out.println(p.buyCard("Trap Hole").getMessage());
        System.out.println(p.addCardToDeck("Trap Hole","batmanDeck",false).getMessage());
        System.out.println(p.addCardToDeck("Battle OX","batmanDeck",false).getMessage());
        System.out.println(p.selectActiveDeck("batmanDeck").getMessage());
        System.out.println(p.addCardToDeck("Trap Hole","batmanDeck",false).getMessage());
        System.out.println(p.removeCardFromDeck("Trap Hole","batmanDeck",false).getMessage());
        System.out.println(p.showDeck("batmanDeck",false).getMessage());*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", GameLogType.SELECT_CARD);
        jsonObject.put("cardIdentity", 123);
        System.out.println(jsonObject.toString());

    }
}
