package Model.Game.Card.SpellCard.Spell;

import Model.Game.*;

public interface Spell {
    public void activate(Game game);
    public void activate(Game game,String cardName);
    public void deactivate(Game game);
}
