import View.CommandLines.*;


import Controller.API;
import com.beust.jcommander.ParameterException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {


    public static Scanner scan = new Scanner(System.in);
    public static String command;
    public static JSONObject request_JSON = new JSONObject();
    public static JSONObject respone_JSON = new JSONObject();

//////////////////////////////////////////////////////////////////  login menu  ///////////////////////////////////////////////

    public void loginMenu() throws JSONException {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu enter (.+)\\s*$") != null) System.out.println("please login first");


                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("login menu");


                else if (commandMatch(command,"^\\s*user create")!=null) {

/////////////////////////////////////////////////////////////////////////////////// register


                    int startwith = commandMatch(command, "^\\s*user create").end() + 1;
                    UserCreate userCreate = new UserCreate();
                    UserCreate userCreate1 = (UserCreate) userCreate.run(command.substring(startwith));
                    ///////validating//////

                    JSONObject respone=js_Pass("command", "crate_new_user","username", userCreate1.username,"password", userCreate1.password,"nickname", userCreate1.nickname)

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

///////////////////////////////////////////////////////////////////////////////// login


                } else if (commandMatch(command,"^\\s*user login")!=null) {

                    //////////////////////////////////////////////////JSON REQUEST
                    int startwith = commandMatch(command, "^\\s*user login").end() + 1;
                    UserLogin userlogin = new UserLogin();
                    UserLogin userLogin1 = (UserLogin) userlogin.run(command.substring(startwith));
                    ///////validating//////
                    JSONObject respone=js_Pass("command", "logn_user","username", userLogin1.username,"password", userLogin1.password);

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                        mainMenu();
                    }
////////////////////////////////////////////////////////////////////////////////


                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;


                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }


        }


    }



///////////////////////////////////////////////////////////////   main menu  /////////////////////////////////////////////////////////



    public void mainMenu() {

        while (true) {

            try {
                command = scan.nextLine();

/////////////////////////////////////////////////////////////////////////////////////menu enter

                if (commandMatch(command, "menu enter (.+)") != null) {

                    String menuName = commandMatch(command, "menu enter (.+)").group(1);

                    if (commandMatch(menuName, "scoreboard") != null) scoreboardMenu();
                    else if (commandMatch(menuName, "profile") != null) profileMenue();
                    else if (commandMatch(menuName, "deck") != null) deckMenu();
                    else if (commandMatch(menuName, "duel") != null) duelMenu();
                    else if (commandMatch(menuName, "import/export") != null) importExportMenu();
                    else if (commandMatch(menuName, "shop") != null) shopMenu();

//////////////////////////////////////////////////////////////////////////////////////

                } else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("main");

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;

                else if (commandMatch(command, "^\\s*log out\\s*$") != null) return;

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }
    }


//////////////////////////////////////////////////////////// scoreboard menu ////////////////////////////////////////////////////////


    public void scoreboardMenu() throws JSONException {

        while (true) {

            try {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////show scoreboard

                if (commandMatch(command, "^\\s*scoreboars show\\s*$") != null) {


                    JSONObject respone = js_Pass("command", "show_scorboard");

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        for (String score:
                             respone.get("message")) {

                        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                }  /////show scoreboard


                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("scoreboard");

                else if (command.equals("menu exit")) return;

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }


    }
//////////////////////////////////////////////////////////  profile menu  ///////////////////////////////////////////////



    public void profileMenue() {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("profile");

                else if (commandMatch(command, "^\\s*profile change") != null) {

////////////////////////////////////////////////////////////////////////////////////////////change profile

                    int startwith = commandMatch(command, "^\\s*profile change").end() + 1;
                    ChangeNickname changeNickname = new ChangeNickname();
                    ChangeNickname changeNickname1 = (ChangeNickname) changeNickname.run(command.substring(startwith));

                    if (changeNickname1.nickname != null) {

                        if (changeNickname1.password) System.out.println("invalid command");
                        else if (changeNickname1.current != null) System.out.println("invalid command");
                        else if (changeNickname1.neww != null) System.out.println("invalid command");

                        else {

                            JSONObject respone = js_Pass("command", "changeProfile_nickname","nickname", changeNickname1.nickname);
                            if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                            else {
                                System.out.println(respone.get("message"));
                            }
                        }

                    } else if (changeNickname1.password) {


                        if (changeNickname1.current == null) System.out.println("invalid command");
                        else if (changeNickname1.neww == null) System.out.println("invalid command");

                        else {

                            JSONObject respone = js_Pass("command", "changeProfile_password","currentPass", changeNickname1.current,"newPass", changeNickname1.neww);

                            if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                            else {
                                System.out.println(respone.get("message"));
                            }
                        }

                    } else System.out.println("invalid command");

////////////////////////////////////////////////////////////////////////////////////////////////

                } else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("profile");

                else if (command.equals("menu exit")) return;

                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }

    }


///////////////////////////////////////////////////////////////////////   shop menu  ///////////////////////////////////////////////////


    public void shopMenu() {

        while (true) {

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("shop");

//////////////////////////////////////////////////////////////////////////////////////buy card

                else if (commandMatch(command, "^\\s*shop buy (.+)\\s*$") != null) {

                    Matcher cardName = commandMatch(command, "^\\s*shop buy (.+)\\s*$");

                    JSONObject respone = js_Pass("command", "buyCard", "cardName", cardName.gruop(1));

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

//////////////////////////////////////////////////////////////////////////////////////////////////show all card

                } else if (commandMatch(command, "^\\s*(?:shop show --all)|(?:shop show --all)") != null) {


                    JSONObject respone = js_Pass("command", "shop_show_all");

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));
                    else {
                        for (String card :
                                respone.get("message")) {
                            System.out.println(card);
                        }

////////////////////////////////////////////////////////////////////////////////////////////////////


                    } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                    else System.out.println("invalid command");

                } catch(ParameterException c){
                    System.out.println("invalid command");
                } catch(StringIndexOutOfBoundsException a){
                    System.out.println("invalid command");
                }
            }
        }
    }
/////////////////////////////////////////////////////////////////  deck menu  ////////////////////////////////////////////////////////

    public void deckMenu() {

        while (true) {

            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("scoreboard");


////////////////////////////////////////////////////////////////////////////////////////////create deck


                else if (commandMatch(command, "^\\s*deck create (.+)\\s*$") != null) {

                    Matcher deckName = commandMatch(command, "^\\s*deck create (.+)\\s*$");

                    JSONObject respone = js_Pass("command", "crate_deck","deckName", deckName.group(1));

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

///////////////////////////////////////////////////////////////////////////////////////////delete deck

                } else if (commandMatch(command, "^\\s*deck delete (.+)\\s*$") != null) {

                    //validating
                    Matcher deckName = commandMatch(command, "^\\s*deck delete (.+)\\s*$");

                    JSONObject respone = js_Pass("command", "delete_deck","deckName", deckName.group(1));

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

///////////////////////////////////////////////////////////////////////////////////////////remove card of deck

                } else if (commandMatch(command, "^\\s*deck rm-card") != null) {

                    int startwith = commandMatch(command, "^\\s*deck rm-card").end() + 1;
                    AddCardToDeck addCardToDeck = new AddCardToDeck();
                    AddCardToDeck addCardToDeck1 = (AddCardToDeck) addCardToDeck.run(command.substring(startwith));



                    JSONObject respone = js_Pass("command", "remove_card_deck","deckName", addCardToDeck1.deckName,"cardName", addCardToDeck1.cardName,"main_side_?", addCardToDeck1.side);

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

////////////////////////////////////////////////////////////////////////////////////////////add card to deck


                } else if (commandMatch(command, "^\\s*deck add-card") != null) {

                    int startwith = commandMatch(command, "^\\s*deck add-card").end() + 1;
                    AddCardToDeck addCardToDeck = new AddCardToDeck();
                    AddCardToDeck addCardToDeck1 = (AddCardToDeck) addCardToDeck.run(command.substring(startwith));


                    JSONObject respone = js_Pass("command", "add_card_deck","deckName", addCardToDeck1.deckName,"cardName", addCardToDeck1.cardName,"main_side_?", addCardToDeck1.side);

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

                } else if (commandMatch(command, "^\\s*(?:deck show --all)|(?:deck show --a)") != null ){

                    JSONObject respone = js_Pass("command", "show_deck_all");

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }


////////////////////////////////////////////////////////////////////////////////////show deck


                } else if (commandMatch(command, "^\\s*deck show") != null) {

                    int startwith = commandMatch(command, "^\\s*deck add-card").end() + 1;
                    ShowDeck showDeck = new ShowDeck();
                    ShowDeck showDeck1 = (ShowDeck) showDeck.run(command.substring(startwith));

                    JSONObject respone = js_Pass("command", "show_deck","deckName", addCardToDeck1.deckName,"main_side_?", addCardToDeck1.side);

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }
                }

//////////////////////////////////////////////////////////////////////////////////

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }
    }

////////////////////////////////////////////////////////////  import/export menu  //////////////////////////////////////////////////
    public void importExportMenu() {
        //import card [card name]
        //export card [card name]

        while(true){
            true{

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("import/export menu");

//////////////////////////////////////////////////////////////////////////////////////////////import card

                else if (commandMatch(command, "^\\s*import card (.+)\\s*$") != null) {

                    Matcher cardName = commandMatch(command, "^\\s*import card (.+)\\s*$");

                    JSONObject respone = js_Pass("command", "import_card","card_name ", cardName.group(1));

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

                }
////////////////////////////////////////////////////////////////////////////////////////////export card

                else if (commandMatch(command, "^\\s*export card (.+)\\s*$") != null) {


                    Matcher cardName = commandMatch(command, "^\\s*export card (.+)\\s*$");

                    JSONObject respone = js_Pass("command", "export_card","card_name ", cardName.group(1));

                    if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                    else {
                        System.out.println(respone.get("message"));
                    }

                }

////////////////////////////////////////////////////////////////////////////////////////////

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;

        else System.out.println("invalid command");

    } catch (ParameterException c) {
        System.out.println("invalid command");
    } catch (StringIndexOutOfBoundsException a) {
        System.out.println("invalid command");
    }
}
    }


/////////////////////////////////////////////////////////////  duel menu  ///////////////////////////////////////////////


        public void duelMenu() {

            while (true) {

                try {
                    command = scan.nextLine();

                    if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("duel");

                    else if (commandMatch(command, "^\\s*duel") != null) {

//////////////////////////////////////////////////////////////////////////////////////duel start

                        int startwith = commandMatch(command, "^\\s*duel").end() + 1;
                        DuelNewGame duelNewGame = new DuelNewGame();
                        DuelNewGame duelNewGame1 = (DuelNewGame) duelNewGame.run(command.substring(startwith));

                        if (duelNewGame1.secondPlayerUsername != null) {

                            if (duelNewGame1.ai != null) System.out.println("invalid command");

                            else {
                                Integer round = duelNewGame1.round;
                                JSONObject respone = js_Pass("command", "duel_new_game", "Opponent", duelNewGame1.secondPlayerUsername, "round", round,"opponent_type","sec_player");

                                if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                                else {
                                    System.out.println(respone.get("message"));
                                    duelBoardMenu();
                                }

                            } else if (duelNewGame1.ai == null) System.out.println("invalid command");

                            else {
                                Integer round = duelNewGame1.round;
                                JSONObject respone = js_Pass("command", "duel_new_game", "Opponent", duelNewGame1.ai, "round", round,"opponent_type","ai");

                                if (respone.get("type").equal("error")) System.out.println(respone.get("message"));

                                else {
                                    System.out.println(respone.get("message"));
                                    duelBoardMenu();
                                }
                            }


//////////////////////////////////////////////////////////////////////////////////////////


                        } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                        else System.out.println("invalid command");


                    } catch(ParameterException c){
                        System.out.println("invalid command");
                    } catch(StringIndexOutOfBoundsException a){
                        System.out.println("invalid command");
                    }
                }
            }

        }


    //////////////////////////////////////////////////duelBoardMenu//////////////////////////////////////////////////////


    public void duelBoardMenu()

    {
        while (true) {

            try {

                command = scan.nextLine();

////////////////////////////////////////////////////////////////////////////////set card
               if(commandMatch(command,"set")!=null) {
                   JSONObject respone = js_Pass("command", "set_card");
                   System.out.println( respone.get("message"));
               }
               else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("duel");

///////////////////////////////////////////////////////////////////////////////summon card
                else if(commandMatch(command,"summon")!=null) {
                   JSONObject respone = js_Pass("command", "summon_card");
                   System.out.println( respone.get("message"));

               }

               else    if(commandMatch(command,"flip-summmon")!=null){
                   JSONObject respone = js_Pass("command", "flip_summon_card");
                   System.out.println( respone.get("message"));
               }

////////////////////////////////////////////////////////////////////////////////select card

                else if (commandMatch(command, "^\\s*select") != null) {

                    int startwith = commandMatch(command, "^\\s*select").end() + 1;
                    Zone zone=new Zone();
                    Zone zone1 = (Zone) zone.run(command.substring(startwith));

                    if (zone1.monster!= null) {
                        if (zone1.spell!= null) System.out.println("invalid command");
                        else if (zone1.hand!= null) System.out.println("invalid command");
                        else if (zone1.field!= null) System.out.println("invalid command");
                        else{
                            JSONObject respone = js_Pass("command", "select_card","zone","monster_zone","place",zone1.monster,"side",zone1.opponent);
                            System.out.println( respone.get("message"));
                        }
                    }
                    else if (zone1.spell!= null) {
                        if (zone1.hand!= null) System.out.println("invalid command");
                        else if (zone1.field!= null) System.out.println("invalid command");
                        else{

                            JSONObject respone = js_Pass("command", "select_card","zone","spell_zone","place",zone1.spell,"side",zone1.opponent);
                            System.out.println( respone.get("message"));
                        }
                    }
                    else if (zone1.hand!= null) {
                        if (zone1.field!= null) System.out.println("invalid command");
                        else{
                            JSONObject respone = js_Pass("command", "select_card","zone","hand_zone","place",zone1.hand);
                            System.out.println( respone.get("message"));
                        }
                    }
                    else if (zone1.field!= null) {

                        JSONObject respone = js_Pass("command", "select_card","zone","field_zone","place","side",zone1.opponent);
                        System.out.println( respone.get("message"));
                    }
                    else System.out.println("invalid command");


                }

                else if (commandMatch(command,"^\\s*select -d\\s*$")) {
                    JSONObject respone = js_Pass("command", "back_select");
                    System.out.println( respone.get("message"));
                }

               else    if(commandMatch(command,"^\\s*((?:card show --selected)|(?:card show -s))\\s*$")!=null){

                   JSONObject respone = js_Pass("command", "show_selected_card");
                   System.out.println( respone.get("message"));
               }
////////////////////////////////////////////////////////////////////////////////////attack card
               else    if(commandMatch(command,"^\\s*attack (\\u+)\\s*$")!=null){
                   String place=commandMatch(command,"^\\s*attack (\\u+)\\s*$").group(1);
                   JSONObject respone = js_Pass("command", "attack","place",place);
                   System.out.println( respone.get("message"));

               }
               else    if(commandMatch(command,"^\\s*attack direct\\s*$")!=null){


                   JSONObject respone = js_Pass("command", "attack_direct");
                   System.out.println( respone.get("message"));

               }

               else    if(commandMatch(command,"^\\s*surrender\\s*$")!=null){

                   JSONObject respone = js_Pass("command","surrender");
                   System.out.println( respone.get("message"));

               }
////////////////////////////////////////////////////////////////////////////////////active efect

               else    if(commandMatch(command,"^\\s*active effect\\s*$")!=null){

                   JSONObject respone = js_Pass("command","active_effect");
                   System.out.println( respone.get("message"));

               }

 ///////////////////////////////////////////////////////////////////////////////////show graveyard

               else    if(commandMatch(command,"^\\s*show grave yard\\s*$")!=null) {

                   JSONObject respone = js_Pass("command","show_graveyard");
                   System.out.println( respone.get("message"));

               }
                 else    if(commandMatch(command,"^\\s*back\\s*$")!=null){

                   JSONObject respone = js_Pass("command", "back_graveyard");
                   System.out.println( respone.get("message"));

                }

///////////////////////////////////////////////////////////////////////////////////cheats
               else    if(commandMatch(command,"^\\s*increase money (\\u+)\\s*$")!=null) {

                   String money=commandMatch(command,"^\\s*increase money (\\u+)\\s*$").group(1);
                   JSONObject respone = js_Pass("command", "increase_money","amount",money);
                   System.out.println( respone.get("message"));

               }

                 else    if(commandMatch(command,"^\\s*duel set-winner (.+)\\s*$")!=null){
                   String winner=commandMatch(command,"^\\s*duel set-winner (.+)\\s*$").group(1);
                   JSONObject respone = js_Pass("command", "set_winner","who?",winner);
                   System.out.println( respone.get("message"));

               }

////////////////////////////////////////////////////////////////////////////////////

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }

    }



/////////////////////////////////////////////////////////////////////////////////aid functions

    public void clearJSON_OBJ(JSONObject o) {
        while (o.length() > 0) {
            o.remove((String) o.keys().next());
        }
    }

    public static Matcher commandMatch(String command,  String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) return matcher;
        else return null;
    }


    public static JSONObject js_Pass(String ...args) {
        for (int i=0;i<= args.length-2;i=+2){
            request_JSON.put(args[i],args[i+1]);
        }
        API request = new API();
        JSONObject respone = request.run(respone_JSON);
        clearJSON_OBJ(request_JSON);
        return respone;
    }

//////////////////////////////////////////////////////////////////////////////////

}
