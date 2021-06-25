package Model.Game.Card.SpellCard.Spell;

import org.json.*;
import Model.Game.*;
import Model.Game.Card.MonsterCard.*;

public class NegateAttack implements Spell{
    public void activate(Game game){
        String string = game.getGameLog().get(game.getGameLog().size()-1);
        JSONObject log = new JSONObject(string);
        if(log.get("type").equals("ATTACK")){
            int code = log.getInt("mainCard");
            MonsterCard monsterCard = new MonsterCard();
            for (int i = 0; i < 5; i++) {
                if(game.getActivePlayer().getField().getMonsterZone()[i].hashCode() == code) monsterCard = game.getActivePlayer().getField().getMonsterZone()[i];
            }
            monsterCard.setMonsterAttackInTurn(true);
            game.nextPhase();
        }
    }
    public void activate(Game game,String CardName){}
}
