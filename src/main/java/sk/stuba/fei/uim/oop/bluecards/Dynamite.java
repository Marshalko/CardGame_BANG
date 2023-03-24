package sk.stuba.fei.uim.oop.bluecards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Dynamite extends BlueCards {


    public Dynamite() {
        this.name = "DYNAMITE";

    }

    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers) {

        if (dynamitCheckBoom()) {
            player.takeLife(3);
            player.removeCardFromBoard(player.hasDynamite(), deckOfTrash);
        } else {
            int temp = player.getID();
            int max = listOfPlayers.size();
            for (int i = 0; i < max; i++) {
                if (temp == 0) {
                    temp = max;
                }
                if (!listOfPlayers.get(temp - 1).isDead()) {
                    listOfPlayers.get(temp - 1).addCardToBoard(this);
                    player.getCardsOnBoard().remove(this);
                    break;
                }
                temp = temp - 1;
            }

        }
    }

    private boolean dynamitCheckBoom() {
        if (random.nextInt(8) == 0) return true;
        return false;
    }
}


