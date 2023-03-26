package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Dostavnik extends BrownCards {
    public Dostavnik() {
        this.name = "DOSTAVNIK";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        boolean emptyDeck = false;
        for (int i = 0; i < 2; i++) {

            if (deckOfCards.isEmpty() && !deckOfTrash.isEmpty()) {
                deckOfCards.addAll(deckOfTrash);
                Collections.shuffle(deckOfCards);
            }
            if (!deckOfCards.isEmpty()) {
                player.addCardToHand(deckOfCards.get(0));
                deckOfCards.remove(0);
            } else {
                emptyDeck = true;
                break;
            }
        }
        if (!emptyDeck) System.out.println("-----          Potiahol si si dve karty          ------");
        if (emptyDeck) System.out.println("------                Dosli karty                ------");
        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
