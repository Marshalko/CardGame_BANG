package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Objects;

public class Indiani extends BrownCards {
    public Indiani() {
        this.name = "INDIANI";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        boolean hasBang = false;
        for (int i = 0; i < listOfPlayers.size(); i++) {
           hasBang = false;
            if (listOfPlayers.get(i).getID() != player.getID()) {
                for (int j = 0; j < listOfPlayers.get(i).getCardsOnHand().size(); j++) {
                    if (!hasBang && Objects.equals(listOfPlayers.get(i).getCardsOnHand().get(j).getName(), "BANG")) {
                        hasBang = true;
                        System.out.println(listOfPlayers.get(i).getName() + " pouzil BANG a nestratil zivot");
                        deckOfTrash.add(listOfPlayers.get(i).getCardsOnHand().get(j));
                        listOfPlayers.get(i).getCardsOnHand().remove(j);
                    }
                }
                if (!hasBang) {
                    listOfPlayers.get(i).takeLife(1);
                }

            }
        }


        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
