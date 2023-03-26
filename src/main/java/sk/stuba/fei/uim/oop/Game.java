package sk.stuba.fei.uim.oop;


import sk.stuba.fei.uim.oop.bluecards.Barel;
import sk.stuba.fei.uim.oop.bluecards.BlueCards;
import sk.stuba.fei.uim.oop.bluecards.Dynamite;
import sk.stuba.fei.uim.oop.bluecards.Vazenie;
import sk.stuba.fei.uim.oop.browncards.*;

import java.util.*;

public class Game {
    private final ArrayList<Player> listOfPlayers = new ArrayList<>();
    private final ArrayList<BlueCards> listOfBlueCards = new ArrayList<>();
    private final ArrayList<BrownCards> listOfBrownCards = new ArrayList<>();
    private final ArrayList<Card> deckOfCards = new ArrayList<>();
    private final ArrayList<Card> deckOfTrash = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    public Game() {
        createPlayers();
        createBlueCards();
        createBrownCards();
        createPack(listOfBlueCards, listOfBrownCards);
        drawCardAtBeginnig();
        playGame();
    }

    private void createPlayers() {
        System.out.println("========          HRA ZACINA        =========");

        int numberOfPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Zadaj pocet hracov:");
            String userInput = scanner.nextLine();
            try {
                numberOfPlayers = Integer.parseInt(userInput);
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    validInput = true;

                } else {
                    System.out.println("????????    Musis zadat cislo od 2 do 4     ????????");
                }
            } catch (NumberFormatException e) {
                System.out.println("?????????   Zadaj cislo od 2 do 4     ????????");
            }

        }

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Zadaj meno " + (i + 1) + " hraca.");
            String name = scanner.nextLine();
            listOfPlayers.add(new Player(name, i, 4, false));
        }
        listOfPlayers.forEach(player -> System.out.println("Meno " + (player.getID() + 1) + " hraca je :" + player.getName()));
        System.out.println("\n");
    }

    private void createBlueCards() {
        for (int i = 0; i < 12; i++) {
            listOfBlueCards.add(new Vazenie());
        }
        for (int i = 0; i < 2; i++) {
            listOfBlueCards.add(new Barel());
        }
        for (int i = 0; i < 20; i++)
            listOfBlueCards.add(new Dynamite());
    }

    private void createBrownCards() {
        for (int i = 0; i < 30; i++) {
            listOfBrownCards.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            listOfBrownCards.add(new Vedle());
        }
        for (int i = 0; i < 8; i++) {
            listOfBrownCards.add(new Pivo());
        }
        for (int i = 0; i < 6; i++) {
            listOfBrownCards.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            listOfBrownCards.add(new Dostavnik());
        }
        for (int i = 0; i < 2; i++) {
            listOfBrownCards.add(new Indiani());
        }
    }

    private void createPack(ArrayList<BlueCards> listOfBlueCards, ArrayList<BrownCards> listOfBrownCards) {
        this.deckOfCards.addAll(listOfBlueCards);
        this.deckOfCards.addAll(listOfBrownCards);
        Collections.shuffle(deckOfCards);
        //return deckOfCards;
    }

    private void drawCardAtBeginnig() {
        for (Player listOfPlayer : listOfPlayers) {
            for (int j = 0; j < 4; j++) {
                listOfPlayer.addCardToHand(deckOfCards.get(0));
                deckOfCards.remove(0);
            }
        }
    }

    private void drawCards(Player player) {
        for (int i = 0; i < 2; i++) {
            if (deckOfCards.isEmpty() && !deckOfTrash.isEmpty()) {
                deckOfCards.addAll(deckOfTrash);
                Collections.shuffle(deckOfCards);
            }
            if (!deckOfCards.isEmpty()) {
                player.addCardToHand(deckOfCards.get(0));
                deckOfCards.remove(0);
            } else System.out.println("------              dosli karty             ------");
        }
    }

    private void playHand(Player player) {
        player.setInPrison(false);
        int playedCard = 0;
        player.displayCardsOnBoard();

        BlueCards dynamite = player.hasDynamite();
        if (dynamite != null) {
            dynamite.isOnTable(player, deckOfTrash, listOfPlayers);
        }

        // prison check
        BlueCards vazenie = player.hasVazenie();
        if (vazenie != null) {
            vazenie.isOnTable(player, deckOfTrash, listOfPlayers);
        }

        //playing card
        while (playedCard != 420 && player.isInPrison()) {
            boolean validInput = false;
            while (!validInput) {
                player.displayCardsOnHand();
                System.out.println("420: nezahraj nic");
                System.out.println("||||||        Ktoru kartu chces zahrat ?         ||||||");
                String userInput = scanner.nextLine();
                try {
                    playedCard = Integer.parseInt(userInput);
                    if (playedCard > 0 && playedCard <= player.getCardsOnHand().size()) {
                        player.getCardsOnHand().get(playedCard - 1).action(player, deckOfTrash, listOfPlayers, deckOfCards);
                        validInput = true;
                    }
                    if (playedCard == 420) break;
                    else if (playedCard > player.getCardsOnHand().size() || playedCard <= 0)
                        System.out.println("???????         Zadaj cislo karty more...        ??????");
                } catch (NumberFormatException e) {
                    System.out.println("???????         Zadaj cislo karty more...        ??????");
                }
                System.out.println(" ");
            }
        }
        //removing excessive cards
        while (player.getCardsOnHand().size() > player.getLives() && player.isInPrison()) {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("------      Mas viac kariet ako zivotou.         ------");
                player.displayCardsOnHand();
                System.out.println("|||||||      Ktoru kartu chces vyhodit?          ||||||");
                String userInput = scanner.nextLine();
                try {
                    int temp = Integer.parseInt(userInput);
                    if (temp >= 1 && temp <= player.getCardsOnHand().size()) {
                        player.cardRemove(temp, deckOfTrash);
                        validInput = true;
                    } else {
                        System.out.println("??????? Musis zadat cislo karty ktoru vlastnis... ?????????");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("?????????   Zadaj cislo karty ktoru mas na ruke...  ?????????");
                }
            }
        }
    }

    private int checkDeaths(int survivors) {
        int numberOfDeaths = 0;

        for (int i = 0; i < listOfPlayers.size(); i++) {
            if (listOfPlayers.get(i).isDead()) {
                numberOfDeaths++;
            }
            survivors = listOfPlayers.size() - numberOfDeaths;
        }
        return survivors;
    }

    private void playGame() {
        int round = listOfPlayers.size() - 1;
        int survivors = listOfPlayers.size();
        int turnID;

        while (survivors != 1) {
            round++;
            turnID = round % listOfPlayers.size();
            if (!listOfPlayers.get(turnID).isDead()) {
                drawCards(listOfPlayers.get(turnID));
                playHand(listOfPlayers.get(turnID));
                System.out.println("------               Tah skoncil.                ------");
            }
            survivors = checkDeaths(survivors);
        }
        System.out.println("========             HRA SKONCILA             =========");
    }
}

// dynamit, barel, vazenie nemat 2x pred sebou opravit
// vazenie dynamit
// ked dojdu karty fix


