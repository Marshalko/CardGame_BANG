package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.bluecards.Barel;

import java.util.ArrayList;

public class Bang extends BrownCards {
    public Bang() {
        this.name = "BANG";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        boolean validInput = false;
        boolean hasVedle = false;
        int temp;
        while (!validInput) {
            System.out.println("||||||     Vyber si na koho chces vystrelit:     ||||||");
            for (int i = 0; i < listOfPlayers.size(); i++) {
                if (!listOfPlayers.get(i).isDead() && player.getID() != listOfPlayers.get(i).getID())
                    System.out.println((i + 1) + "-" + listOfPlayers.get(i).getName());
            }
            String userInput = scanner.nextLine();
            try {
                temp = Integer.parseInt(userInput);
                temp = temp - 1;
                if (temp >= 0 && temp < listOfPlayers.size() && temp != player.getID() && !listOfPlayers.get(temp).isDead()) {

                    if (listOfPlayers.get(temp).hasBarel() != null) {
                        Barel barel = (Barel) listOfPlayers.get(temp).hasBarel();
                        if (barel.barelCheck()) {
                            System.out.println("------  " + listOfPlayers.get(temp).getName() + " pouzil Barel     -------");
                            player.removeCardFromHand(this);
                            deckOfTrash.add(this);
                            break;
                        }
                    }

                    for (int i = 0; i < listOfPlayers.get(temp).getCardsOnHand().size(); i++) {
                        if (listOfPlayers.get(temp).getCardsOnHand().get(i) instanceof Vedle) {
                            deckOfTrash.add(listOfPlayers.get(temp).getCardsOnHand().get(i));
                            listOfPlayers.get(temp).getCardsOnHand().remove(i);
                            player.removeCardFromHand(this);
                            deckOfTrash.add(this);
                            System.out.println("++++++     " + listOfPlayers.get(temp).getName() + " pouzil VEDLE    ++++++\n");
                            hasVedle = true;
                            break;
                        }
                    }

                    if (!hasVedle) {
                        listOfPlayers.get(temp).takeLife(1);
                        player.removeCardFromHand(this);
                        deckOfTrash.add(this);
                    }
                    validInput = true;
                } else System.out.println("???????    Vystrel iba na hraca na ktoreho mozes    ?????????");
            } catch (NumberFormatException e) {
                System.out.println("???????    Vystrel iba na hraca na ktoreho mozes    ?????????");
            }
        }
    }
}
