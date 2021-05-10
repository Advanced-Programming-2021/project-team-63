package Model.Game.Card.SpellCard;

import Model.Game.Card.Card;
import Model.Game.Card.SpellCard.Spell.*;

public class SpellCard extends Card{
    private Spell spell;
    private SpellCategory SpellCategory;
    private Icon icon;

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpellCategory(SpellCategory spellCategory) {
        SpellCategory = spellCategory;
    }

    public SpellCategory getSpellCategory() {
        return SpellCategory;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }
}
