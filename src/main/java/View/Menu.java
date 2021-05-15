package View;

public class Menu {



    public static Scanner scan = new Scanner(System.in);
    public static String command;


    public void loginMenu() {

        while (true) {
            try {
                command = scan.nextLine();

                if (commandMatch(command, "^\\s*menu enter (.+)\\s*$") != null)
                    System.out.println("please login first");

                else if (commandMatch(command, "^\\s*menu show-current\\s*$") != null) System.out.println("login menu");

                else if (command.startsWith("user create")) {
                    UserCreate userCreate = new UserCreate();

                    UserCreate userCreate1 = (UserCreate) userCreate.run(command.substring(12));
                    ///////validating//////
                    ///////send parameters/////////////
                    System.out.println("user created successfully");


                } else if (command.startsWith("user login")) {
                    UserLogin userlogin = new UserLogin();
                    UserLogin userLogin1 = (UserLogin) userlogin.run(command.substring(11));
                    //seneding parameters//
                    //validating
                    System.out.println("user logged in successfully!");
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


}
