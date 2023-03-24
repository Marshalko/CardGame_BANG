package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Pivo extends BrownCards {
    public Pivo() {
        this.name = "PIVO";
    }

    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        player.setLives((player.getLives()+1));

        System.out.println("++++++     Dal si si pivecko a mas " + player.getLives() + " zivotov.    ++++++\n");

        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
