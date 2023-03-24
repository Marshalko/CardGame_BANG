package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import java.util.ArrayList;

public class Indiani extends BrownCards {
    public Indiani() {
        this.name = "INDIANI";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        boolean hasBang ;
        for (Player listOfPlayer : listOfPlayers) {
            hasBang = false;
            if (listOfPlayer.getID() != player.getID()) {
                for (int j = 0; j < listOfPlayer.getCardsOnHand().size(); j++) {
                    if (!hasBang && listOfPlayer.getCardsOnHand().get(j) instanceof Bang) {
                        hasBang = true;
                        System.out.println(listOfPlayer.getName() + " pouzil BANG a nestratil zivot");
                        deckOfTrash.add(listOfPlayer.getCardsOnHand().get(j));
                        listOfPlayer.getCardsOnHand().remove(j);
                    }
                }
                if (!hasBang) {
                    listOfPlayer.takeLife(1);
                }
            }
        }
        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
