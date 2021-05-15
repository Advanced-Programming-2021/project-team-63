package View;
package CommandLines;
import org.*;

public class Menu {



    public static Scanner scan = new Scanner(System.in);
    public static String command;
    public static JSONObject request_JSON=new JSONObject();
    public static JSONObject respone_JSON=new JSONObject();


    public void loginMenu() {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu enter (.+)\\s*$") != null)
                    System.out.println("please login first");

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("login menu");

                else if (command.startsWith("user create")) {
                    ///////////////////////////////////////////////

                    UserCreate userCreate = new UserCreate();
                    UserCreate userCreate1 = (UserCreate) userCreate.run(command.substring(12));
                    ///////validating//////
                    request_JSON.put("command","crate_new_user");
                    request_JSON.put("username",userCreate1.username);
                    request_JSON.put("password",userCreate1.password);
                    request_JSON.put("nickname",userCreate1.nickname);
                    //api.run(request_JSON);
                    clearJSON_OBJ(request_JSON);

                    ////////////////////////////////////////////////
                    //recieve respone and print result


                } else if (command.startsWith("user login")) {
                    //////////////////////////////////////////////////

                    UserLogin userlogin = new UserLogin();
                    UserLogin userLogin1 = (UserLogin) userlogin.run(command.substring(11));
                    ///////validating//////
                    request_JSON.put("command","logn_user");
                    request_JSON.put("username",userLogin1.username);
                    request_JSON.put("password",userLogin1.password);
                    //api.run(request_JSON);
                    clearJSON_OBJ(request_JSON);

                    ////////////////////////////////////////////////
                    //recieve respone and print result
                   // mainMenu();

                } else if (commandMatch(command, "^\\s*menu exit\\s*$") != null) return;
                else System.out.println("invalid command");


            } catch (ParameterException c) {
                System.out.println("invalid command");
            } catch (StringIndexOutOfBoundsException a) {
                System.out.println("invalid command");
            }


        }


    }

















/////////////////////////////////////////////////////////////////////////////////aid functions

    public void clearJSON_OBJ(JSONObject o){
        while(o.length()>0){
            o.remove((String) o.keys().next());
        }
    }
    public static Matcher commandMatch(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) return matcher;
        else return null;
    }
    /////////////////////////////////////////////////////////////////////////////

}
