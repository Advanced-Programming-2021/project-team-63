package Model.JsonObject;

import java.util.*;

public class CardJson {
    private String name;

    public CardJson(String cardName){
        this.name = cardName;
    }

    public String getName(){
        return this.name;
    }
}
