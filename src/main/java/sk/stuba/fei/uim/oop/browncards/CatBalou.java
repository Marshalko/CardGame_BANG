package sk.stuba.fei.uim.oop.browncards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;

public class CatBalou extends BrownCards {
    public CatBalou() {
        this.name = "CATBALOU";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        int temp = 0, temp2;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("||||||    Ktoremu hracovi chces zobrat kartu ?   ||||||");
            for (int i = 0; i < listOfPlayers.size(); i++) {
                if (player.getID() != listOfPlayers.get(i).getID())
                    System.out.println((i + 1) + " : " + listOfPlayers.get(i).getName());
            }
            String userInput = scanner.nextLine();
            try {
                temp = Integer.parseInt(userInput);
                temp = temp - 1;

                if (player.getID() != temp && temp >= 0 && temp < listOfPlayers.size() && (!listOfPlayers.get(temp).getCardsOnHand().isEmpty() && !listOfPlayers.get(temp).getCardsOnBoard().isEmpty())) {
                    validInput = true;
                } else System.out.println("????? Zadaj cislo hraca ktoremu chces zobrat kartu a ma karty ?????");
            } catch (NumberFormatException e) {
                System.out.println("????? Zadaj cislo hraca ktoremu chces zobrat kartu a ma karty?????");
            }
        }

        while (true) {
            System.out.println("|||||| Chces zobrat kartu z ruky alebo z plochy ?||||||");
            System.out.println("1. Z ruky\n2. Z plochy");
            String userInput2 = scanner.nextLine();

            try {
                temp2 = Integer.parseInt(userInput2);
                if (temp2 == 1 && !listOfPlayers.get(temp).getCardsOnHand().isEmpty()) {
                    int randomCard = random.nextInt(listOfPlayers.get(temp).getCardsOnHand().size());
                    deckOfTrash.add(listOfPlayers.get(temp).getCardsOnHand().get(randomCard));
                    listOfPlayers.get(temp).getCardsOnHand().remove(randomCard);
                    break;
                }
                if (temp2 == 2 && !listOfPlayers.get(temp).getCardsOnBoard().isEmpty()) {
                    int randomCard = random.nextInt(listOfPlayers.get(temp).getCardsOnBoard().size());
                    deckOfTrash.add(listOfPlayers.get(temp).getCardsOnBoard().get(randomCard));
                    listOfPlayers.get(temp).getCardsOnBoard().remove(randomCard);
                    break;
                } else
                    System.out.println("?????? Zadaj cislo 1 alebo 2, ber odtial kde ma karty ??????");
            } catch (NumberFormatException e) {
                System.out.println("?????? Zadaj cislo 1 alebo 2, ber odtial kde ma karty ??????");
            }
        }
        player.removeCardFromHand(this);
        deckOfTrash.add(this);
    }
}
