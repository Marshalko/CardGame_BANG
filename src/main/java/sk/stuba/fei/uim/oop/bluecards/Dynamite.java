package sk.stuba.fei.uim.oop.bluecards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Dynamite extends BlueCards {


    public Dynamite() {
        this.name = "DYNAMITE";

    }

    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        boolean alreadyHasDynamite=false;
        for(int i =0; i<player.getCardsOnBoard().size(); i++){
            if (player.getCardsOnBoard().get(i) instanceof Dynamite) {
                alreadyHasDynamite = true;
                break;
            }
        }
        if(!alreadyHasDynamite) {
            System.out.println("++++++         Dal si si pred seba dynamit         ++++++");
            player.addCardToBoard(this);
            player.removeCardFromHand(this);
        }
        else System.out.println("???????         Mas uz dynamit pred sebou          ??????");
    }

    public void isOnTable(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers){

        if (dynamitCheckBoom()) {
            System.out.println("++++++             Dynamit ti vybuchol           ++++++");
            player.takeLife(3);
            player.removeCardFromBoard(this,deckOfTrash);
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
                    System.out.println("++++++            Dynamit ti nevybuchol          ++++++");
                    break;
                }
                temp = temp - 1;
            }
        }
    }
    private boolean dynamitCheckBoom() {
        return random.nextInt(8) == 0;
    }
}


