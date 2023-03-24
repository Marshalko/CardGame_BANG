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
        for (int i = 0; i < 2; i++) {

            if (deckOfCards.isEmpty()) {
                deckOfCards.addAll(deckOfTrash);
                Collections.shuffle(deckOfCards);
            }
            player.addCardToHand(deckOfCards.get(0));
            deckOfCards.remove(0);
        }
        System.out.println("-----          Potiahol si si dve karty          ------");
        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
