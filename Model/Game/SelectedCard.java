package Model.Game;
import Model.Game.Card.Card;

public class SelectedCard {
    private Card card;
    private CardAddress cardAddress;
    public SelectedCard(){
        card = null;
        cardAddress = CardAddress.NONE;
    }
    
    public void setCard(Card card){
        this.card = card;
    }
    public Card getCard(){
        return this.card;
    }
    
    public void setCardAddress(CardAddress cardAddress){
        this.cardAddress = cardAddress;
    } 
    public CardAddress getCardAddress(){
        return this.cardAddress;
    }
}
