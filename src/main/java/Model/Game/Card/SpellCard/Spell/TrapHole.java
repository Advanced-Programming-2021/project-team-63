package Model.Game.Card.SpellCard.Spell;

import org.json.*;
import Model.Game.*;
import Model.Game.Card.MonsterCard.MonsterCard;

public class TrapHole implements Spell{
    public void activate(Game game){
        String string = game.getGameLog().get(game.getGameLog().size()-1);
        JSONObject log = new JSONObject(string);
        if(log.get("type").equals("SUMMON_MONSTER") || log.get("type").equals("FLIP_SUMMON_MONSTER")){
            int code = log.getInt("mainCard");
            MonsterCard monsterCard = new MonsterCard();
            for (int i = 0; i < 5; i++) {
                if(game.getActivePlayer().getField().getMonsterZone()[i].hashCode() == code) monsterCard = game.getActivePlayer().getField().getMonsterZone()[i];
            }
            if(monsterCard.getAtk()>=1000) game.getActivePlayer().getField().killMonsterCard(monsterCard, game);
        }
    }
    public void activate(Game game,String CardName){}
}
