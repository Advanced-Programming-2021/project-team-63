package Model.Game.Card.SpellCard.Spell;

import org.json.*;
import Model.Game.*;

public class TorrentialTribute implements Spell{
    public void activate(Game game){
        String string = game.getGameLog().get(game.getGameLog().size()-1);
        JSONObject log = new JSONObject(string);
        if(log.get("type").equals("SUMMON_MONSTER")){
            for (int i = 0; i < 5; i++) {
                game.getPlayer1().getField().killMonsterCard(game.getPlayer1().getField().getMonsterZone()[i], game); 
                game.getPlayer2().getField().killMonsterCard(game.getPlayer2().getField().getMonsterZone()[i], game); 
            }
        }
    }
    public void activate(Game game,String CardName){}
}
