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
        game.selectCard(cardAddress);
    }

    public void deselectCard(){
        game.deselectCard();
    }

    public void summonMonster(){
        game.summonMonster(); 
    }

    public void speciallSummonMonster(){
        game.speciallSummonMonster();        
    }

    public void flipSummonMonster(){
        game.flipSummonMonster();
    }

    public void ritualSummonMonster(){
        game.ritualSummonMonster();
    }

    public void setMonster(){
        game.setMonster();
    }

    public void changeStatusMonster(){
        game.changeStatusMonster();
    }

    public void attack(int targetMonster){
        game.addCardFromDeckToHand(targetMonster);
    }

    public void directAttack(){
        game.directAttack();
    }

    public void activateEffect(){
        game.activateEffect();
    }

    public void setSpell(){
        game.setSpell();
    }

    public void setTrap(){
        game.setTrap();
    }

    public void setSpellOrTrapForOpponent(){
        game.setSpellOrTrapForOpponent();
    }

    public ArrayList<Card> getGraveyard(){
        return game.getGraveyard();
    }

    public Card getSelectedCard(){
        return game.getSelectedCard();
    }

}