package sk.stuba.fei.uim.oop.bluecards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Barel extends BlueCards {
    public Barel() {
        this.name = "BAREL";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        player.addCardToBoard(this);
        player.removeCardFromHand(this);
    }
    public boolean barelCheck(){
        if( random.nextInt(4)==0) return true ;
         return  false;
    }
}
