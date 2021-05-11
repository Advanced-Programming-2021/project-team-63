import Model.JsonObject.MonsterJson;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws IOException {
      //  File me = new File(".");
     //   System.out.println(me.getAbsolutePath());
        ArrayList<MonsterJson> monsters = new ArrayList<>();
        File file = new File("src\\main\\java\\Monster.txt");
        Scanner scanner = new Scanner(file);
        for(int i = 0 ; i < 41 ; i++) {
            monsters.add(new MonsterJson());
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setName(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setLevel(Integer.parseInt(scanner.nextLine().trim()));
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setAttribute(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setMonsterType(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setCardType(scanner.nextLine());
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setAtk(Integer.parseInt(scanner.nextLine().trim()));
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setDef(Integer.parseInt(scanner.nextLine().trim()));
        }
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setDescription(scanner.nextLine());
        }
        monsters.get(36).setDescription(monsters.get(36).getDescription()+"\nIMPORTANT: Capturing the \"Wattaildragon\" is forbidden by the Ancient Rules and is a Level 6 offense, the minimum sentence for which is imprisonment for no less than 2500 heliocycles.");
        for(int i = 0 ; i < 41 ; i++){
            monsters.get(i).setPrice(Integer.parseInt(scanner.nextLine().trim()));
        }
        scanner.close();
        FileWriter writer = new FileWriter("src\\main\\java\\MonstersInfo.txt");
        writer.write(new Gson().toJson(monsters));
        writer.close();
    }
}
