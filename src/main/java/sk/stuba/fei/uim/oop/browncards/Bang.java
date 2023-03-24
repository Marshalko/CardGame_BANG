package sk.stuba.fei.uim.oop.browncards;


import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Objects;

public class Bang extends BrownCards {
    public Bang() {
        this.name = "BANG";
    }

    @Override
    public void action(Player player, ArrayList<Card> deckOfTrash, ArrayList<Player> listOfPlayers, ArrayList<Card> deckOfCards) {
        super.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        boolean validInput = false;
        boolean hasVEDLE = false;
        int temp = 0;
        while (!validInput) {
            System.out.println("||||||     Vyber si na koho chces vystrelit:     ||||||");
            for (int i = 0; i < listOfPlayers.size(); i++) {
                if (!listOfPlayers.get(i).isDead() && player.getName() != listOfPlayers.get(i).getName())
                    System.out.println((i + 1) + "-" + listOfPlayers.get(i).getName());
            }
            String userInput = scanner.nextLine();
            try {
                temp = Integer.parseInt(userInput);
                temp = temp - 1;
                if (temp >= 0 && temp < listOfPlayers.size() && temp != player.getID() && !listOfPlayers.get(temp).isDead()) {
                    for (int i = 0; i < listOfPlayers.get(temp).getCardsOnHand().size(); i++) {

                        if (Objects.equals(listOfPlayers.get(temp).getCardsOnHand().get(i).getName(), "VEDLE")) {
                            deckOfTrash.add(listOfPlayers.get(temp).getCardsOnHand().get(i));
                            listOfPlayers.get(temp).getCardsOnHand().remove(i);
                            player.removeCardFromHand(this);
                            deckOfTrash.add(this);
                            System.out.println("++++++     "+listOfPlayers.get(temp).getName() + " pouzil VEDLE    ++++++\n");
                            hasVEDLE = true;
                            break;
                        }
                    }
                    if (!hasVEDLE) {
                        listOfPlayers.get(temp).takeLife(1);
                        player.removeCardFromHand(this);
                        deckOfTrash.add(this);
                    }
                    validInput = true;
                }
                else System.out.println("???????    Vystrel iba na hraca na ktoreho mozes    ?????????");
            } catch (NumberFormatException e) {
                System.out.println("???????    Vystrel iba na hraca na ktoreho mozes    ?????????");
            }
        }
//        System.out.println("||||||     Vyber si na koho chces vystrelit: ||||||");
//        for (int i = 0; i < listOfPlayers.size(); i++) {
//            if (!listOfPlayers.get(i).isDead() && player.getName() != listOfPlayers.get(i).getName())
//                System.out.println((i + 1) + "-" + listOfPlayers.get(i).getName());
//        }
//        int temporal = scanner.nextInt();
//        temporal = temporal - 1;
//
//        //
//        if (listOfPlayers.get(temporal).isDead()) {
//            System.out.println("??????  Na mrtvych sa nestriela...  ????????");
//
//        }
//        for (int i = 0; i < listOfPlayers.get(temporal).getCardsOnHand().size(); i++) {
//
//            if (listOfPlayers.get(temporal).getCardsOnHand().get(i).getName() == "VEDLE") {
//                deckOfTrash.add(listOfPlayers.get(temporal).getCardsOnHand().get(i));
//                listOfPlayers.get(temporal).getCardsOnHand().remove(i);
//                System.out.println(listOfPlayers.get(temporal).getName() + " pouzil VEDLE");
//                hasVEDLE = true;
//                break;
//            }
//        }
//        if (!hasVEDLE) {
//            listOfPlayers.get(temporal).takeLife(1);
//
//        }
    }

}
