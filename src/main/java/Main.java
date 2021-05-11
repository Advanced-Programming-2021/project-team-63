import Model.JsonObject.CardJson;
import Model.JsonObject.MonsterJson;
import Model.JsonObject.SpellAndTrapJson;
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
        ArrayList<SpellAndTrapJson> spellAndTrapJsons = new ArrayList<>();
        File file = new File("src\\main\\java\\Spell&Trap.txt");
        Scanner scanner = new Scanner(file);
        for(int i = 0 ; i < 35 ; i++) {
            spellAndTrapJsons.add(new SpellAndTrapJson());
        }
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setName(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setType(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setIcon(scanner.nextLine().trim());
        }
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setDescription(scanner.nextLine().trim());
        }
        spellAndTrapJsons.get(33).setDescription(spellAndTrapJsons.get(33).getDescription()+"\nâ—\u008F Attack Position: It gains ATK equal to its original DEF." +
                "\nâ—\u008F Defense Position: It gains DEF equal to its original ATK.");
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setStatus(scanner.nextLine());
        }
        for(int i = 0 ; i < 35 ; i++){
            spellAndTrapJsons.get(i).setPrice(Integer.parseInt(scanner.nextLine().trim()));
        }
        scanner.close();

        FileWriter writer = new FileWriter("src\\main\\java\\Spell&TrapsInfo.txt");
        writer.write(new Gson().toJson(spellAndTrapJsons));
        writer.close();
    }
}
