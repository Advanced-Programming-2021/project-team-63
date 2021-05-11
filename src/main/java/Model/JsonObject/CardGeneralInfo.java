package Model.JsonObject;

public class CardGeneralInfo {
    private String name;
    private String description;
    private int price;

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

    public int getPrice(){
        return this.price;
    }
}
