package View;


import Controller.API;
import Model.Game.Card.MonsterCard.Mode;
import Model.Game.Card.Status;
import Model.Game.Phase;
import Model.JsonObject.*;
import View.CommandLines.*;
import View.CommandLines.Set;
import com.beust.jcommander.ParameterException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Menu {

    API request = new API();
    public Scanner scan = new Scanner(System.in);
    public String command;
    public JSONObject request_JSON = new JSONObject();
    public JSONObject response;
    public static boolean ID=true;


///////////////////////////////////////////////////////////////  login menu  //////////////////////////////////////////////////

    public void loginMenu() throws Exception {



        while (true) {

            if (ID){
                System.out.println("its login menu");
                ID=false;
            }

            try {
                command = scan.nextLine();


                if (commandMatch(command, "^\\s*menu enter (.+)\\s*$") != null) System.out.println("please login first");

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("login menu");

                else if (commandMatch(command, "^\\s*user create") != null) {

                    int startwith = commandMatch(command, "^\\s*user create").end() + 1;
                    registerLogin(command.substring(startwith));

                } else if (commandMatch(command, "^\\s*user login") != null) {

                    int startwith = commandMatch(command, "^\\s*user login").end() + 1;
                    if (loginLogin(command.substring(startwith)) == true){
                        ID=true;
                        mainMenu();
                    }

                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) {
                    System.out.println("till next time! ;D");
                    return;
                }

                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }

        }

    }


///////////////////////////////////////////////////////////////   main menu  ////////////////////////////////////////////////////////////


    public void mainMenu() throws Exception {


        while (true) {


            if (ID){
                System.out.println("its main menu");
                ID=false;
            }

            try {
                command = scan.nextLine();



                if (commandMatch(command, "menu enter (.+)") != null) {

                    String menuName = commandMatch(command, "menu enter (.+)").group(1);

                    if (commandMatch(menuName, "scoreboard") != null) {
                        ID=true;
                        scoreboardMenu();
                    }
                    else if (commandMatch(menuName, "profile") != null) {
                        ID=true;
                        profileMenue();
                    }
                    else if (commandMatch(menuName, "deck") != null){
                        ID=true;
                        deckMenu();
                    }
                    else if (commandMatch(menuName, "duel") != null) {
                        ID=true;
                        duelMenu();
                    }
                    else if (commandMatch(menuName, "import/export") != null) {
                        ID=true;
                        importExportMenu();
                    }
                    else if (commandMatch(menuName, "shop") != null) {
                        ID=true;
                        shopMenu();
                    }
                    else System.out.println("no such menu exists!");

                }

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("main");

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) {

                    ID=true;
                    System.out.println("user logged out successfully!");
                    return;

                }

                else if (commandMatch(command, "^\\s*user logout\\s*$") != null){
                    ID=true;
                    System.out.println("user logged out successfully!");
                    return;
                }

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


//////////////////////////////////////////////////////////// scoreboard menu ////////////////////////////////////////////////////////


    public void scoreboardMenu() throws Exception {



        while (true) {

            if (ID){
                System.out.println("its scoreboard menu");
                ID=false;
            }

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*scoreboard show\\s*$") != null) {

                    JSONObject response = js_Pass("command", "show_scorboard");

                    if (response.get("type").equals("error")) System.out.println(response.get("message"));

                    else {

                        Gson gson = new Gson();
                        Type userListType = new TypeToken<ArrayList<ScoreboardInfo>>() {}.getType();
                        ArrayList<ScoreboardInfo> usersArray = gson.fromJson((String) response.get("message"), userListType);
                        showScoreboard(usersArray);

                    }

                } else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null)
                    System.out.println("scoreboard");

                else if (command.equals("menu exit")){
                    ID=true;
                    return;
                }

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }


    }
//////////////////////////////////////////////////////////  profile menu  ///////////////////////////////////////////////


    public void profileMenue() throws Exception {
        while (true) {
            if (ID){
                System.out.println("its profile menu");
                ID=false;
            }

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("profile");

                else if (commandMatch(command, "^\\s*profile change") != null) {

                    int startwith = commandMatch(command, "^\\s*profile change").end() + 1;
                    changeProfile(command.substring(startwith));

                }
                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("profile");

                else if (command.equals("menu exit")){
                    ID=true;
                    return;
                }

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


/////////////////////////////////////////////////////////////   shop menu  ///////////////////////////////////////////////////


    public void shopMenu() throws Exception {
        while (true) {

            if (ID){

                System.out.println("its shop menu");
                ID=false;
            }

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("shop");


                else if (commandMatch(command, "^\\s*shop buy (.+)\\s*$") != null) {

                    Matcher cardName = commandMatch(command, "^\\s*shop buy (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "buyCard", "cardName", cardName.group(1));

                    System.out.println(response.get("message"));


                } else if (commandMatch(command, "^\\s*(?:shop show --all)|(?:shop show -a)") != null) {


                    JSONObject response
                            = js_Pass("command", "shop_show_all");

                    if (response
                            .get("type").equals("error")) System.out.println(response
                            .get("message"));
                    else {

                        Gson gson = new Gson();

                        Type cardListType = new TypeToken<ArrayList<CardGeneralInfo>>() {}.getType();

                        ArrayList<CardGeneralInfo> cardsArray = gson.fromJson((String) response.get("message"), cardListType);
                       showAllShopCards(cardsArray);

                    }

                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) {
                    ID=true;
                    return;
                }
                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
/////////////////////////////////////////////////////////////////  deck menu  ////////////////////////////////////////////////////////

    public void deckMenu() throws Exception {
        while (true) {

            if (ID){
                System.out.println("its deck menu");
                ID=false;
            }

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("scoreboard");


                else if (commandMatch(command, "^\\s*deck create (.+)\\s*$") != null) {

                    Matcher deckName = commandMatch(command, "^\\s*deck create (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "crate_deck", "deckName", deckName.group(1));

                    System.out.println(response.get("message"));


                } else if (commandMatch(command, "^\\s*deck delete (.+)\\s*$") != null) {

                    //validating
                    Matcher deckName = commandMatch(command, "^\\s*deck delete (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "delete_deck", "deckName", deckName.group(1));

                    System.out.println(response.get("message"));

                } else if (commandMatch(command, "^\\s*deck set-activate (.+)\\s*$") != null) {

                    //validating
                    Matcher deckName = commandMatch(command, "^\\s*deck set-activate (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "set_deck_activate", "deckName", deckName.group(1));

                    System.out.println(response.get("message"));


                } else if (commandMatch(command, "^\\s*deck rm-card") != null) {

                    int startwith = commandMatch(command, "^\\s*deck rm-card").end() + 1;
                    removeCardOfDeck(command.substring(startwith));


                } else if (commandMatch(command, "^\\s*deck add-card") != null) {

                    int startwith = commandMatch(command, "^\\s*deck add-card").end() + 1;
                    addCardToDeck(command.substring(startwith));


                } else if (commandMatch(command, "^\\s*(?:deck show --all)|(?:deck show -a)") != null) {

                    JSONObject response = js_Pass("command", "show_deck_all");

                    if (response.get("type").equals("error")) System.out.println(response
                            .get("message"));

                    else {

                        Gson gson = new Gson();

                        ShowAllDecksJson cardsArray = gson.fromJson((String) response.get("message"), ShowAllDecksJson.class);

                        showAllDecks(cardsArray);
                    }


                } else if (commandMatch(command, "^\\s*deck show") != null) {

                    int startwith = commandMatch(command, "^\\s*deck show").end() + 1;
                    showDeck(command.substring(startwith));
                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) {
                    ID=true;
                    return;
                }
                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    ////////////////////////////////////////////////////////////  import/export menu  //////////////////////////////////////////////////
    public void importExportMenu() throws Exception {



        while (true) {

            if (ID){
                System.out.println("its import/export menu");
                ID=false;
            }

            try {

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null)
                    System.out.println("import/export menu");


                else if (commandMatch(command, "^\\s*import card (.+)\\s*$") != null) {

                    Matcher cardName = commandMatch(command, "^\\s*import card (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "import_card", "card_name ", cardName.group(1));

                    System.out.println(response.get("message"));

                } else if (commandMatch(command, "^\\s*export card (.+)\\s*$") != null) {


                    Matcher cardName = commandMatch(command, "^\\s*export card (.+)\\s*$");

                    JSONObject response
                            = js_Pass("command", "export_card", "card_name ", cardName.group(1));

                    System.out.println(response.get("message"));

                }

////////////////////////////////////////////////////////////////////////////////////////////

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null){
                    ID=true;
                    return;
                }

                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


/////////////////////////////////////////////////////////////  duel menu  ///////////////////////////////////////////////


    public void duelMenu() throws Exception {

        while (true) {

            if (ID){
                System.out.println("its duel menu");
                ID=false;
            }

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("duel");

                else if (commandMatch(command, "^\\s*duel") != null) {


                    int startwith = commandMatch(command, "^\\s*duel").end() + 1;
                   if (startDuel(command.substring(startwith))) duelBoardMenu();

                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) {
                    ID=true;
                    return;
                }
                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }

    }


    //////////////////////////////////////////////////duelBoardMenu//////////////////////////////////////////////////////


    public void duelBoardMenu() throws Exception {



        while (true) {

            if (ID){
                System.out.println("its game board! start playing");
                ID=false;
            }

            try {

                command = scan.nextLine();

                if (commandMatch(command, "^\\s*next phase") != null) {
                    JSONObject response = js_Pass("command", "next_phase");
                    Gson gson1 = new Gson();
                    Phase phase = gson1.fromJson( response.get("message").toString(), Phase.class);
                    System.out.println("Phase: "+ phase.name());

                    if (phase==Phase.DRAW_PHASE){
                        JSONObject response1 = js_Pass("command", "add_card_to_hand");
                        System.out.println(response1.get("message"));
                    }
                    else if (phase==Phase.END_PHASE){
                       // System.out.println("its "+);                                                 //bayad esme harif ro dashte basham
                    }
                }





                else if (commandMatch(command, "^\\s*set") != null) set(command);

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("duel");

                else if (commandMatch(command, "summon") != null) {
                    JSONObject response = js_Pass("command", "summon_card");
                    String tributeNumber= (String) response.get("message");

                    if (commandMatch(tributeNumber, "^\\{\"tribute\":\\d\\}$") != null) getTribute(tributeNumber);
                    else System.out.println(response.get("message"));



                } else if (commandMatch(command, "flip-summmon") != null) {
                    JSONObject response
                            = js_Pass("command", "flip_summon_card");
                    System.out.println(response
                            .get("message"));
                } else if (commandMatch(command, "^\\s*select -d\\s*$")!=null) {

                    JSONObject response = js_Pass("command", "back_select");
                    System.out.println(response.get("message"));

                } else if (commandMatch(command, "^\\s*select") != null) {

                    int startwith = commandMatch(command, "^\\s*select").end() + 1;
                    selectCard(command.substring(startwith));


                } else if (commandMatch(command, "^\\s*((?:card show --selected)|(?:card show -s))\\s*$") != null) {        //?????

                    JSONObject response = js_Pass("command", "show_selected_card");
                    if (response.get("type").equals("error")) System.out.println(response.get("message"));
                    else{
                        Gson gson = new Gson();
                        CardGeneralInfo card = gson.fromJson((JsonElement) response.get("message"), CardGeneralInfo.class);
                        showSelectedCard(card);
                    }





                } else if (commandMatch(command, "^\\s*attack (\\d+)\\s*$") != null) {
                    String place = commandMatch(command, "^\\s*attack (\\d+)\\s*$").group(1);
                    JSONObject response
                            = js_Pass("command", "attack", "place", place);
                    System.out.println(response
                            .get("message"));

                } else if (commandMatch(command, "^\\s*attack direct\\s*$") != null) {


                    JSONObject response = js_Pass("command", "attack_direct");
                    if (response.get("type").equals("error")) System.out.println(response.get("message"));
                    else{
                        if (response.has("isOver")){
                            System.out.println("the winner is :"+response.get("winner"));
                            return;
                        }
                        else{
                            System.out.println(response.get("message"));
                        }

                    }
                } else if (commandMatch(command, "^\\s*surrender\\s*$") != null) {

                    JSONObject response = js_Pass("command", "surrender");
                    System.out.println(response.get("message"));



                }
////////////////////////////////////////////////////////////////////////////////////active efect

                else if (commandMatch(command, "^\\s*active effect\\s*$") != null) {

                    JSONObject response
                            = js_Pass("command", "active_effect");
                    System.out.println(response
                            .get("message"));

                }

                ///////////////////////////////////////////////////////////////////////////////////show graveyard

                else if (commandMatch(command, "^\\s*show grave yard\\s*$") != null) {
                    JSONObject response = js_Pass("command", "show_graveyard");
                    if (response.get("type").equals("error")) System.out.println(response.get("message"));

                    else {
                        Gson gson = new Gson();

                        Type cardListType = new TypeToken<ArrayList<CardGeneralInfo>>() {
                        }.getType();

                        ArrayList<CardGeneralInfo> cardsArray = gson.fromJson((String) response.get("message"), cardListType);
                        showGraveYard(cardsArray);

                    }


                } else if (commandMatch(command, "^\\s*back\\s*$") != null) {

                    JSONObject response = js_Pass("command", "back_graveyard");
                    System.out.println(response.get("message"));

                }

///////////////////////////////////////////////////////////////////////////////////cheats
                else if (commandMatch(command, "^\\s*increase money (\\d+)\\s*$") != null) {

                    String money = commandMatch(command, "^\\s*increase money (\\d+)\\s*$").group(1);
                    JSONObject response = js_Pass("command", "increase_money", "amount", money);
                    System.out.println(response.get("message"));

                } else if (commandMatch(command, "^\\s*duel set-winner (.+)\\s*$") != null) {
                    String winner = commandMatch(command, "^\\s*duel set-winner (.+)\\s*$").group(1);
                    JSONObject response = js_Pass("command", "set_winner", "who?", winner);
                    System.out.println(response.get("message"));

                }



////////////////////////////////////////////////////////////////////////////////////

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null){
                    ID=true;
                    return;
                }
                else System.out.println("invalid command");

                if (true) {
                    JSONObject response = js_Pass("command", "get_phase");

                    Gson gson1 = new Gson();

                    Phase phase = gson1.fromJson( response.get("message").toString(), Phase.class);
                    if (phase == Phase.MAIN_PHASE_1) {
                        JSONObject response1 = js_Pass("command", "get_board");
                        Gson gson = new Gson();
                        BoardJson boardJson = gson.fromJson(response1.get("message").toString(), BoardJson.class);
                    }

                }
                JSONObject response1 = js_Pass("command", "get_board");
                Gson gson = new Gson();
                BoardJson boardJson = gson.fromJson(response1.get("message").toString(), BoardJson.class);
                printBoard(boardJson);




            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }




/////////////////////////////////////////////////////////////////////////////////aid functions

    public static void clearJSONOBJ(JSONObject o) {
        while (o.length() > 0) {
            o.remove((String) o.keys().next());
        }
    }

    public static Matcher commandMatch(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) return matcher;
        else return null;
    }


    public JSONObject js_Pass(String... args) throws Exception {
        for (int i = 0; i <= args.length - 2; i += 2) {
            request_JSON.put(args[i], args[i + 1]);
        }

        JSONObject response = request.run(request_JSON);
        request_JSON=new JSONObject();
        return response;
    }

    private void clearJSON_OBJ(JSONObject object) {
        Iterator keys = object.keys();
        while (keys.hasNext())
            object.remove((String) object.keys().next());
    }

    public void showScoreboard(ArrayList<ScoreboardInfo> scoreboardInfos) {


        LinkedList<ScoreboardInfo> scoreList = new LinkedList<>(scoreboardInfos);
        List<ScoreboardInfo> interChange = new ArrayList<>();
        int quantity = scoreboardInfos.size();

if (quantity==0){
    System.out.println("none");return;
}
        for (int i = 0; i <= quantity - 2; i++) {
            for (int j = i + 1; i <= quantity - 1; i++) {

                if (scoreList.get(i).score < scoreList.get(j).score) {
                    interChange.add(scoreboardInfos.get(j));
                    scoreList.set(j, scoreList.get(i));
                    scoreList.set(i, interChange.get(0));
                    interChange.clear();

                } else if ((scoreList.get(i).score == scoreList.get(j).score)) {

                    if (scoreList.get(i).nickname.compareTo(scoreList.get(j).nickname) < 0) {
                        interChange.add(scoreList.get(j));
                        scoreList.set(j, scoreList.get(i));
                        scoreList.set(i, interChange.get(0));
                        interChange.clear();
                    }

                }


            }


        }


        int priorScore = 0;
        int i = 1, j = 1;
        for (ScoreboardInfo user :
                scoreList) {
            String scoreLine;


            if (i == 1) {
                System.out.println(i + "- " + user.nickname + ": " + user.score);
                j++;
            } else if (user.score == priorScore) {
                System.out.println(i + "-  " + user.nickname + ": " + user.score);
                j++;
            } else {
                System.out.println(j + "- " + user.nickname + ":  " + user.score);
                i = j;
                j++;
            }
            priorScore = user.score;


        }

//////////////////////////////////////////////////////////////////////////////////

    }

    public void showAllShopCards(ArrayList<CardGeneralInfo> cards) {

        LinkedList<CardGeneralInfo> orderdCardList = new LinkedList<>(cards);

        orderdCardList.sort(Comparator.comparing(CardGeneralInfo::getName));


        for (CardGeneralInfo card :
                orderdCardList) {

            System.out.println(card.getName() + ":" + card.getPrice());


        }

    }

    public void showAllDecks(ShowAllDecksJson object) {
        System.out.println("Decks:");
        System.out.println("Active deck:");
        if (object.activeDeck != null) {
            //valid boodan nist
            System.out.println(object.activeDeck.name + ":" + " main deck " + object.activeDeck.mainDeckSize + "," + "side deck " + object.activeDeck.sideDeckSize);
        }
        System.out.println("Other decks:");

        for (DeckGeneralInfo object1 : object.decks) {
            System.out.println(object1.name + ":" + " main deck " + object1.mainDeckSize + "," + "side deck " + object1.sideDeckSize);
            System.out.println();

        }


    }

    public void showPurchaisedCards(ArrayList<CardGeneralInfo> cards){

        LinkedList<CardGeneralInfo> orderdCardList = new LinkedList<>(cards);

        orderdCardList.sort(Comparator.comparing(CardGeneralInfo::getName));


        for (CardGeneralInfo card : orderdCardList) {

            System.out.println(card.getName() + ":" + card.getDescription());

        }


    }

    public void showDeck(ShowDeckJson object) {
        System.out.println("Deck: " + object.getName());
        if (object.isSideDeck()) System.out.println("Side deck:");
        else System.out.println("Main deck:");
        System.out.println("Monsters:");
        ///print monsters in irderd way

        LinkedList<CardGeneralInfo> orderdMonsters = new LinkedList<>(object.getMonsters());
        LinkedList<CardGeneralInfo> interChange = new LinkedList<>();
        int quantity = orderdMonsters.size();
        for (int i = 0; i <= quantity - 2; i++) {
            for (int j = i + 1; i <= quantity - 1; i++) {

                if (orderdMonsters.get(i).getName().compareTo(orderdMonsters.get(j).getName()) < 0) {
                    interChange.add(orderdMonsters.get(j));
                    orderdMonsters.set(j, orderdMonsters.get(i));
                    orderdMonsters.set(i, interChange.get(0));
                    interChange.clear();
                }

            }
        }
        for (CardGeneralInfo card :
                orderdMonsters) {
            System.out.println(card.getName() + ":" + card.getDescription());
        }

//spell and traps

        LinkedList<CardGeneralInfo> orderdSpellsAndTraps = new LinkedList<>(object.getSpellAndTraps());

        int quantity1 = orderdSpellsAndTraps.size();
        for (int i = 0; i <= quantity1 - 2; i++) {
            for (int j = i + 1; i <= quantity1 - 1; i++) {

                if (orderdSpellsAndTraps.get(i).getName().compareTo(orderdSpellsAndTraps.get(j).getName()) < 0) {
                    interChange.add(orderdSpellsAndTraps.get(j));
                    orderdSpellsAndTraps.set(j, orderdSpellsAndTraps.get(i));
                    orderdSpellsAndTraps.set(i, interChange.get(0));
                    interChange.clear();
                }

            }
        }
        for (CardGeneralInfo card :
                orderdSpellsAndTraps) {
            System.out.println(card.getName() + ":" + card.getDescription());
        }
    }

    public void showGraveYard(ArrayList<CardGeneralInfo> object) {
        if (object.size() == 0) System.out.println("graveyard empty");
        else {

            for (CardGeneralInfo card :
                    object) {
                System.out.println(card.getName() + ":" + card.getDescription());

            }


        }
    }

    public void attackMessages(AttackInfo object) {
        if (object.getTargetStatus() == Status.SUMMON) {
            if (object.getTargetMode() == Mode.ATTACK) {
                if (object.getAttakerAtk() > object.getTargetAtk())
                    System.out.println("your opponent’s monster is destroyed and your opponent receives " + (object.getAttakerAtk() - object.getTargetAtk()) + " battle damage");
                else if (object.getAttakerAtk() == object.getTargetAtk())
                    System.out.println("both you and your opponent monster cards are destroyed and no" + "one receives damage");
                else
                    System.out.println("Your monster card is destroyed and you received " + (object.getTargetAtk() - object.getAttakerAtk()) + " battle damage");
            } else if (object.getTargetMode() == Mode.DEFENSE) {

                if (object.getAttakerAtk() > object.getTargetDef())
                    System.out.println("the defense position monster is destroyed");
                else if (object.getAttakerAtk() == object.getTargetDef()) System.out.println("no card is destroyed");
                else
                    System.out.println("no card is destroyed and you received " + (object.getTargetDef() - object.getAttakerAtk()) + " battle damage");

            }
        } else {

            if (object.getTargetMode() == Mode.ATTACK) {
                if (object.getAttakerAtk() > object.getTargetAtk())
                    System.out.println("opponent’s monster card was " + object.getTargetMonsterName() + "and your opponent’s monster is destroyed and your opponent receives " + (object.getAttakerAtk() - object.getTargetAtk()) + " battle damage");
                else if (object.getAttakerAtk() == object.getTargetAtk())
                    System.out.println("opponent’s monster card was " + object.getTargetMonsterName() + "and both you and your opponent monster cards are destroyed and no" + "one receives damage");
                else
                    System.out.println("opponent’s monster card was " + object.getTargetMonsterName() + "and Your monster card is destroyed and you received " + (object.getTargetAtk() - object.getAttakerAtk()) + " battle damage");
            } else if (object.getTargetMode() == Mode.DEFENSE) {

                if (object.getAttakerAtk() > object.getTargetDef())
                    System.out.println("opponent’s monster card was " + object.getTargetMonsterName() + "and the defense position monster is destroyed");
                else if (object.getAttakerAtk() == object.getTargetDef()) System.out.println("no card is destroyed");
                else
                    System.out.println("opponent’s monster card was " + object.getTargetMonsterName() + "and no card is destroyed and you received " + (object.getTargetDef() - object.getAttakerAtk()) + " battle damage");

            }

        }
    }

    public void registerLogin(String command) throws Exception {
        UserCreate userCreate = new UserCreate();
        UserCreate userCreate1 = (UserCreate) userCreate.run(command);
        ///////validating//////

        JSONObject response = js_Pass("command", "crate_new_user", "username", userCreate1.username, "password", userCreate1.password, "nickname", userCreate1.nickname);

        if (response.get("type").equals("error")) System.out.println(response.get("message"));

        else {
            System.out.println(response.get("message"));
        }

    }

    public boolean loginLogin(String command) throws Exception {
        UserLogin userlogin = new UserLogin();
        UserLogin userLogin1 = (UserLogin) userlogin.run(command);
        ///////validating//////
        JSONObject response
                = js_Pass("command", "login_user", "username", userLogin1.username, "password", userLogin1.password);

        if (response.get("type").equals("error")) {
            System.out.println(response.get("message"));

            return false;

        } else {
            System.out.println(response
                    .get("message"));
            return true;
        }

    }

    public void changeProfile(String command) throws Exception {
        ChangeNickname changeNickname = new ChangeNickname();
        ChangeNickname changeNickname1 = (ChangeNickname) changeNickname.run(command);

        if (changeNickname1.nickname != null) {

            if (changeNickname1.password) System.out.println("invalid command");
            else if (changeNickname1.current != null) System.out.println("invalid command");
            else if (changeNickname1.neww != null) System.out.println("invalid command");

            else {

                JSONObject response
                        = js_Pass("command", "change_Profile_nickname", "nickname", changeNickname1.nickname);
                if (response
                        .get("type").equals("error")) System.out.println(response
                        .get("message"));

                else {
                    System.out.println(response
                            .get("message"));
                }
            }

        } else if (changeNickname1.password ) {


            if (changeNickname1.current == null) System.out.println("invalid command");
            else if (changeNickname1.neww == null) System.out.println("invalid command");

            else {

                JSONObject response
                        = js_Pass("command", "change_" +
                        "Profile_password", "currentPass", changeNickname1.current, "newPass", changeNickname1.neww);

                if (response
                        .get("type").equals("error")) System.out.println(response
                        .get("message"));

                else {
                    System.out.println(response
                            .get("message"));
                }
            }

        } else System.out.println("invalid command");

    }

    public void checkStatus() {
    }

    public void removeCardOfDeck(String command) throws Exception {
        AddCardToDeck addCardToDeck = new AddCardToDeck();
        AddCardToDeck addCardToDeck1 = (AddCardToDeck) addCardToDeck.run(command);

        StringBuilder sb = new StringBuilder();
        String cardName;
        for (String nameSpell:addCardToDeck1.cardName){
            sb.append(nameSpell);
            sb.append(" ");
        }

        cardName = sb.toString().trim();

        JSONObject response
                = js_Pass("command", "remove_card_deck", "deckName", addCardToDeck1.deckName, "cardName",cardName , "main_side_?",  Boolean.toString(addCardToDeck1.side));

        if (response
                .get("type").equals("error")) System.out.println(response
                .get("message"));

        else {
            System.out.println(response
                    .get("message"));
        }
    }

    public void addCardToDeck(String command) throws Exception {
        AddCardToDeck addCardToDeck = new AddCardToDeck();
        AddCardToDeck addCardToDeck1 = (AddCardToDeck) addCardToDeck.run(command);

        StringBuilder sb = new StringBuilder();
        String cardName;
        for (String nameSpell:addCardToDeck1.cardName){
        sb.append(nameSpell);
        sb.append(" ");
        }

        cardName = sb.toString().trim();


        JSONObject response = js_Pass("command", "add_card_deck", "deckName",
                addCardToDeck1.deckName, "cardName",cardName , "main_side_?",  Boolean.toString(addCardToDeck1.side));

        if (response.get("type").equals("error")) System.out.println(response.get("message"));

        else {
            System.out.println(response
                    .get("message"));
        }
    }

    public void showDeck(String command) throws Exception {

        ShowDeck showDeck = new ShowDeck();
        ShowDeck showDeck1 = (ShowDeck) showDeck.run(command);

        if (showDeck1.cards==false && showDeck1.deckName != null) {

            JSONObject response
                    = js_Pass("command", "show_deck", "deckName", showDeck1.deckName, "main_side_?",  Boolean.toString( showDeck1.side));

            if (response.get("type").equals("error")) System.out.println(response.get("message"));

            else {
                Gson gson = new Gson();
                ShowDeckJson Deck = gson.fromJson((JsonElement) response.get("message"), ShowDeckJson.class);
                showDeck(Deck);
            }
        } else if (showDeck1.cards != false && showDeck1.deckName == null ) {
            JSONObject response
                    = js_Pass("command", "show_deck_all");
            if (response.get("type").equals("error")) System.out.println(response.get("message"));

            else {
                Gson gson = new Gson();

                Type cardListType = new TypeToken<ArrayList<CardGeneralInfo>>() {}.getType();

                ArrayList<CardGeneralInfo> cardsArray = gson.fromJson((String) response.get("message"), cardListType);
                showPurchaisedCards(cardsArray);
            }

        } else System.out.println("invalid command");
    }

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

    public void set(String command) throws Exception {
        int startwith = commandMatch(command, "^\\s*set").end() ;

        if (commandMatch(command,"^\\s*set$")!=null) {
            JSONObject response
                    = js_Pass("command", "set_card");
            System.out.println(response
                    .get("message"));


        } else {

            Set set = new Set();
            Set set1 = (Set) set.run(command.substring(startwith+1));
            JSONObject response
                    = js_Pass("command", "set_position", "position", set1.position);
            System.out.println(response
                    .get("message"));

        }


    }

    public void selectCard(String command) throws Exception {
        Zone zone = new Zone();
        Zone zone1 = (Zone) zone.run(command);

        if (zone1.monster != 0) {
            if (zone1.spell != 0) System.out.println("invalid command");
            else if (zone1.hand != 0) System.out.println("invalid command");
            else if (zone1.field) System.out.println("invalid command");
            else {
                JSONObject response
                        = js_Pass("command", "select_card", "zone", "monster_zone", "place", Integer.toString(zone1.monster), "side",Boolean.toString( zone1.opponent));
                System.out.println(response
                        .get("message"));
            }
        } else if (zone1.spell != 0) {
            if (zone1.hand != 0) System.out.println("invalid command");
            else if ( zone1.field ) System.out.println("invalid command");
            else {

                JSONObject response
                        = js_Pass("command", "select_card", "zone", "spell_zone", "place", Integer.toString(zone1.spell), "side",Boolean.toString( zone1.opponent));
                System.out.println(response
                        .get("message"));
            }
        } else if (zone1.hand!=0) {
            if (zone1.field ) System.out.println("invalid command");
            else {
                JSONObject response
                        = js_Pass("command", "select_card", "zone", "hand_zone", "place",Integer.toString( zone1.hand));
                System.out.println(response
                        .get("message"));
            }
        } else if (zone1.field) {

            JSONObject response
                    = js_Pass("command", "select_card", "zone", "field_zone", "place", "side",Boolean.toString( zone1.opponent));
            System.out.println(response
                    .get("message"));
        } else System.out.println("invalid command");

    }

    public void printBoard(BoardJson boardJson) {
        ArrayList<Integer> iterator = new ArrayList<>();
        iterator.add(5);
        iterator.add(3);
        iterator.add(1);
        iterator.add(2);
        iterator.add(4);
        ArrayList<Integer> iterator1 = new ArrayList<>();
        iterator1.add(4);
        iterator1.add(2);
        iterator1.add(1);
        iterator1.add(3);
        iterator1.add(5);

        System.out.println(boardJson.getActivePlayer().getNickName() + ":" + boardJson.getActivePlayer().getLife());
        //print c for in hand cards with for loop
        for(int i = 0 ; i < boardJson.getActivePlayer().getHandSize() ; i++){
            System.out.print("\tc");
        }
        System.out.println();
        // print DN zone for in deck card numbers     System.out.println(boardJson.getActivePlayer().);
        System.out.println(boardJson.getActivePlayer().getDeckSize());
        System.out.print("\t");
        for (int number : iterator) {      //spell zone
            for (int j = 1; j <= 5; j++) {
                if (number == j) {
                    if (boardJson.getActivePlayer().getSpellZone()[j-1] == null) System.out.print("E");
                    else if (boardJson.getActivePlayer().getSpellZone()[j-1].getStatus() == Status.SET)
                        System.out.print("H");
                    else System.out.print("O");
                }
            }
            System.out.print("\t");
        }
        System.out.println();
        System.out.print("\t");
        for (int number1 : iterator) {//monster zone
            for (int j = 1; j <= 5; j++) {
                if (number1 == j) {
                    if (boardJson.getActivePlayer().getMonsterZone()[j-1] == null) System.out.print("E");

                    else if (boardJson.getActivePlayer().getMonsterZone()[j-1].getMode() == Mode.DEFENSE) {
                        if (boardJson.getActivePlayer().getMonsterZone()[j-1].getStatus() == Status.SET)
                            System.out.print("DH");
                        else System.out.print("DO");

                    } else if (boardJson.getActivePlayer().getMonsterZone()[j-1].getMode() == Mode.ATTACK) {
                        if (boardJson.getActivePlayer().getMonsterZone()[j-1].getStatus() == Status.SET)
                            System.out.print("OH");
                        else System.out.print("OO");
                    }
                }
            }
            System.out.print("\t");
        }
        System.out.println();

        //print gy and fz
        String fieldZone;
        if (boardJson.getActivePlayer().getFieldZone() == null) fieldZone = "E";
        else fieldZone = "O";
        System.out.println(boardJson.getActivePlayer().getGraveyardSize() + "\t\t\t\t\t\t" + fieldZone);

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println();

        //opponent
        String fieldZone1 = null;
        if (boardJson.getInActivePlayer().getFieldZone() == null) fieldZone1 = "E";
        else fieldZone1 = "O";
        System.out.println(fieldZone1 + "\t\t\t\t\t\t" + boardJson.getInActivePlayer().getGraveyardSize());

        //monster zone
        System.out.print("\t");
        for (int number2 : iterator1) {//monster zone

            for (int j = 1; j <= 5; j++) {

                if (number2 == j) {
                    if (boardJson.getInActivePlayer().getMonsterZone()[j-1] == null) System.out.print("E");

                    else if (boardJson.getInActivePlayer().getMonsterZone()[j-1].getMode() == Mode.DEFENSE) {
                        if (boardJson.getInActivePlayer().getMonsterZone()[j-1].getStatus() == Status.SET)
                            System.out.print("DH");
                        else System.out.print("DO");

                    } else if (boardJson.getInActivePlayer().getMonsterZone()[j-1].getMode() == Mode.ATTACK) {
                        if (boardJson.getInActivePlayer().getMonsterZone()[j-1].getStatus() == Status.SET)
                            System.out.print("OH");
                        else System.out.print("OO");

                    }
                }
            }
            System.out.print("\t");
        }
        System.out.println();
        System.out.print("\t");
        for (int number3 : iterator1) {      //spell zone
            for (int j = 1; j <= 5; j++) {
                if (number3 == j) {
                    if (boardJson.getInActivePlayer().getSpellZone()[j-1] == null)
                        System.out.print("E");

                    else if (boardJson.getInActivePlayer().getSpellZone()[j-1].getStatus() == Status.SET)
                        System.out.print("H");
                    else System.out.print("O");
                }
            }
            System.out.print("\t");
        }
        System.out.println();
        System.out.println(boardJson.getInActivePlayer().getDeckSize());
        System.out.println();
        for(int i = 0 ; i < boardJson.getInActivePlayer().getHandSize() ; i++){
            System.out.print("\tc");
        }
        System.out.println();
        // print DN zone for in deck card numbers     System.out.println(boardJson.getActivePlayer().);
        //Dn in hands
        System.out.println(boardJson.getInActivePlayer().getNickName() + ":" + boardJson.getInActivePlayer().getLife());
    }

    private void getTribute(String tributeNumber) throws Exception {
        JSONObject jsonObject = null;
       //do {

           if (tributeNumber.equals("{\"tribute\":1}")) {

               System.out.println("you have to tribute 1 card...enter the address");
               String command1 = scan.nextLine();
               if(!command1.equals("cancel")) {
                   jsonObject = js_Pass("command", "tribute", "number", "1", "address1", command1);
                   System.out.println(jsonObject.get("message"));
               }
           }
           else  if (tributeNumber.equals("{\"tribute\":2}")) {
               System.out.println("you have to tribute 2 card...enter the addresses with enter");
               String command1 = scan.nextLine();
               String command2 = scan.nextLine();
               if(!command1.equals("cancel") && !command2.equals("cancel")) {
                   jsonObject = js_Pass("command", "tribute", "number", "2", "address1", command1, "address2", command2);
                   System.out.println(jsonObject.get("message"));
               }
           }

           //else if (tributeNumber.equals("cancel")) break;

       //}while(jsonObject.get("message").equals("error"));
    }

    public  void showSelectedCard(CardGeneralInfo card){

    }
}


//ghorbani ha
