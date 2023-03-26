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
        boolean alreadyHasBarel=false;
        for(int i =0; i<player.getCardsOnBoard().size(); i++){
            if (player.getCardsOnBoard().get(i) instanceof Barel) {
                alreadyHasBarel = true;
                break;
            }
        }
        if(!alreadyHasBarel) {
            System.out.println("++++++         Dal si si pred seba barel         ++++++");
            player.addCardToBoard(this);
            player.removeCardFromHand(this);
        }
        else System.out.println("???????         Mas uz barel pred sebou          ??????");
    }

    public boolean barelCheck(){
        return random.nextInt(4) == 0;
    }
}
