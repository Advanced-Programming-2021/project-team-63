package Controller;

import java.util.ArrayList;

import Model.Account;
import Model.Game.Game;
import Model.Game.Card.Card;

public class GameController{
    private Game game ;
    
    public void createGame(Account player1 , Account player2){
        game = new Game(player1 , player2) ;
    }
   
    public void nextPhase(){
        game.nextPhase();
    }

    public void addCardFromDeckToHand(){
        game.addCardFromDeckToHand();
    }

    public void selectCard(String cardAddress){

    }

    public void deselectCard(){

    }

    public void summonMonster(){

    }

    public void speciallSummonMonster(){
        
    }

    public void flipSummonMonster(){

    }

    public void ritualSummonMonster(){

    }

    public void setMonster(){

    }

    public void changeStatusMonster(){

    }

    public void attack(int targetMonster){

    }

    public void directAttack(){

    }

    public void activateEffect(){

    }

    public void setSpell(){

    }

    public void setTrap(){

    }

    public void setSpellOrTrapForOpponent(){

    }

    public ArrayList<Card> getGraveyard(){
        return game.getGraveyard();
    }

    public Card getSelectedCard(){
        return game.getSelectedCard();
    }

}