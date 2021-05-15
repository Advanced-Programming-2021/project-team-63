package View;

package CommandLines;
import org.*;

public class Menu {


    public static Scanner scan = new Scanner(System.in);
    public static String command;
    public static JSONObject request_JSON = new JSONObject();
    public static JSONObject respone_JSON = new JSONObject();

/////////////////////////////////////////////////////////////////////login menu

    public void loginMenu() throws JSONException {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu enter (.+)\\s*$") != null)
                    System.out.println("please login first");

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("login menu");

                else if (command.startsWith("user create")) {

                    ///////////////////////////////////////////////JSON REQUEST

                    UserCreate userCreate = new UserCreate();
                    UserCreate userCreate1 = (UserCreate) userCreate.run(command.substring(12));
                    ///////validating//////
                    request_JSON.put("command", "crate_new_user");
                    request_JSON.put("username", userCreate1.username);
                    request_JSON.put("password", userCreate1.password);
                    request_JSON.put("nickname", userCreate1.nickname);
                    API request = new API();
                    JSONObject respone = request.run(respone_JSON);
                    clearJSON_OBJ(request_JSON);

                    if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                    else {
                        System.out.println(API.get("message"));
                    }

                    ////////////////////////////////////////////////


                } else if (command.startsWith("user login")) {

                    //////////////////////////////////////////////////JSON REQUEST

                    UserLogin userlogin = new UserLogin();
                    UserLogin userLogin1 = (UserLogin) userlogin.run(command.substring(11));
                    ///////validating//////
                    request_JSON.put("command", "logn_user");
                    request_JSON.put("username", userLogin1.username);
                    request_JSON.put("password", userLogin1.password);
                    API request = new API();
                    JSONObject respone = request.run(respone_JSON);
                    clearJSON_OBJ(request_JSON);
                    if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                    else {
                        System.out.println(API.get("message"));
                        mainMenu();
                    }

                    ////////////////////////////////////////////////


                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }


        }


    }

/////////////////////////////////////////////////////////////////////////////main menu

    public void mainMenu() {
        while (true) {
            try {
                command = scan.nextLine();


                if (commandMatch(command, "menu enter (.+)") != null) {

                    String menuName = commandMatch(command, "menu enter (.+)").group(1);
                    if (commandMatch(menuName, "scoreboard") != null) scoreboardMenu();

                    else if (commandMatch(menuName, "profile") != null) profileMenu();
                    else if (commandMatch(menuName, "deck") != null) deckMenu();
                    else if (commandMatch(menuName, "duel") != null) duel();
                    else if (commandMatch(menuName, "import/esport") != null) importExportMenu();
                    else if (commandMatch(menuName, "shop") != null) shopMenu();

                } else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("main");

                else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }
    }


/////////////////////////////////////////////////////////////////////////////////scoreboard menu   (JSON NEEDED)

    public void scoreboardMenu() throws JSONException {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*scoreboars show\\s*$") != null) {
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
/////////////////////////////////////////////////////////////////////////////////profile menu


    public void profileMenue() {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("profile");

                else if (commandMatch(command, "^\\s*profile change") != null) {


                    int startwith = commandMatch(command, "^\\s*profile change").end() + 1;
                    ChangeNickname changeNickname = new ChangeNickname();
                    ChangeNickname changeNickname1 = (ChangeNickname) changeNickname.run(command.substring(startwith));

                    if (changeNickname1.nickname != null) {

                        if (changeNickname1.password) System.out.println("invalid command");
                        else if (changeNickname1.current != null) System.out.println("invalid command");
                        else if (changeNickname1.neww != null) System.out.println("invalid command");

                        else {

                            request_JSON.put("command", "changeProfile_nickname");
                            request_JSON.put("nickname",changeNickname1.nickname);
                            API request = new API();
                            JSONObject respone = request.run(respone_JSON);
                            clearJSON_OBJ(request_JSON);
                            if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                            else {
                                System.out.println(API.get("message"));
                            }


                        }
                    } else if (changeNickname1.password) {

                        if (changeNickname1.current == null) System.out.println("invalid command");
                        else if (changeNickname1.neww == null) System.out.println("invalid command");

                        else {
                            request_JSON.put("command", "changeProfile_password");
                            request_JSON.put("currentPass",changeNickname1.current);
                            request_JSON.put("newPass",changeNickname1.neww);
                            API request = new API();
                            JSONObject respone = request.run(respone_JSON);
                            clearJSON_OBJ(request_JSON);
                            if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                            else {
                                System.out.println(API.get("message"));
                            }

                        }

                    } else System.out.println("invalid command");


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


/////////////////////////////////////////////////////////////////////////////////shop menu


    public void shopMenu() {
        while (true) {
            try {
                command = scan.nextLine();
                if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("shop");

                else if (commandMatch(command, "^\\s*shop buy (.)+\\s*$") != null) {

                    Matcher cardName=commandMatch(command, "^\\s*shop buy (.)+\\s*$");
                    request_JSON.put("command", "buyCard");
                    request_JSON.put("cardName",cardName.gruop(1));
                    API request = new API();
                    JSONObject respone = request.run(respone_JSON);
                    clearJSON_OBJ(request_JSON);
                    if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                    else {
                        System.out.println(API.get("message"));
                    }

                } else if (commandMatch(command, "^\\s*shop show --all") != null || commandMatch(command, "^\\s*shop show -a") != null) {

                    request_JSON.put("command", "shop_show_all");
                    API request = new API();
                    JSONObject respone = request.run(respone_JSON);
                    clearJSON_OBJ(request_JSON);
                    if (respone.get("type").equal("error")) System.out.println(API.get("message"));
                    else {
                        System.out.println(API.get("message"));
                    }

                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");

            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }
        }
    }
/////////////////////////////////////////////////////////////////////////////////deck menu

/////////////////////////////////////////////////////////////////////////////////aid functions

    public void clearJSON_OBJ(JSONObject o) {
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

//////////////////////////////////////////////////////////////////////////////////

}
