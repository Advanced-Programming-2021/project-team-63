package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public class MysticalSpaceTyphoon implements Spell{
    public void activate(Game game) {}
    public void activate(Game game, String cardName) {
        for (int i = 0; i < 5; i++) {
            if(game.getInactivePlayer().getField().getMonsterZone()[i].getName().equals(cardName)) game.getInactivePlayer().getField().killMonsterCard(game.getInactivePlayer().getField().getMonsterZone()[i], game);
            if(game.getInactivePlayer().getField().getSpellZone()[i].getName().equals(cardName)) game.getInactivePlayer().getField().killSpellCard(game.getInactivePlayer().getField().getSpellZone()[i]);
        }
    }
}
