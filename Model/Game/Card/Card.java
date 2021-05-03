package Model.Game.Card;

public abstract class Card {
    private String name;
    private String description;
    private String number;
    private Category category;

    public static Card Construct(Category category){
        return null;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
