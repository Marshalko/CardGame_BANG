package sk.stuba.fei.uim.oop.bluecards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class Vazenie extends BlueCards {
    public Vazenie() {
        this.name = "VAZENIE";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);

        boolean validInput = false;
        int temp;

        while (!validInput) {
            System.out.println("||||||   Ktoreho hraca chces poslat do vazenia?  ||||||");
            for (int i = 0; i < listOfPlayers.size(); i++) {
                if (player.getID() != listOfPlayers.get(i).getID())
                    System.out.println((i + 1) + " : " + listOfPlayers.get(i).getName());
            }

            String userInput = scanner.nextLine();

            try {
                temp = Integer.parseInt(userInput);
                temp = temp - 1;

                if (player.getID() != temp && temp >= 0 && temp < listOfPlayers.size()) {
                    boolean alreadyHasVazenia = false;
                    for (int i = 0; i < player.getCardsOnBoard().size(); i++) {
                        if (player.getCardsOnBoard().get(i) instanceof Vazenie) {
                            alreadyHasVazenia = true;
                            break;
                        }
                    }
                    if (!alreadyHasVazenia) {
                        System.out.println("++++++         Dal si ho do vazenia         ++++++");
                        listOfPlayers.get(temp).addCardToBoard(this);
                        player.removeCardFromHand(this);
                    } else System.out.println("???????         Mas uz vazenie pred sebou          ??????");

                    validInput = true;
                } else System.out.println("????? Zadaj cislo hraca ktoreho chces poslat do vazenia ?????");
            } catch (NumberFormatException e) {
                System.out.println("????? Zadaj cislo hraca ktoreho chces poslat do vazenia ?????");
            }
        }
    }

    @Override
    public void isOnTable(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayer) {
        super.isOnTable(player, deckOfTrash, listOfPlayer);

        if (random.nextInt(4) == 0) {
            System.out.println("-----              Vyhol si sa vazeniu            -----");
            player.setInPrison(false);
            deckOfTrash.add(this);
            player.getCardsOnBoard().remove(this);
        } else {player.setInPrison(true);
            System.out.println("------              ostal si v base              ------");
                player.removeCardFromBoard(this,deckOfTrash);
        }
    }
}


