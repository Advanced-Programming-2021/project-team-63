package Model.JsonObject;

public class CardGeneralInfo {
    private final String name;
    private final String description;
    private final int price;

    public CardGeneralInfo(MonsterJson card){
        this.name = card.getName();
        this.description = card.getDescription();
        this.price = card.getPrice();
    }

    public CardGeneralInfo(SpellAndTrapJson card){
        this.name = card.getName();
        this.description = card.getDescription();
        this.price = card.getPrice();
    }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public int getPrice(){
        return this.price;
    }

}
