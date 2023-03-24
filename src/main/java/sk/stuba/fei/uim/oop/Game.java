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
    Scanner myObj = new Scanner(System.in);

    public Game() {
        createPlayers();
        createBlueCards();
        createBrownCards();
        createPack(listOfBlueCards, listOfBrownCards);
        drawCardAtBeginnig();
        playGame();
    }

    private void createPlayers() {
        System.out.println("========GAME IS ABOUT TO BEGIN=========");

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
        for (int i = 0; i < 2; i++) {
            listOfBlueCards.add(new Vazenie());
        }
        for (int i = 0; i < 3; i++) {
            listOfBlueCards.add(new Barel());
        }
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
        for (int i = 0; i < 16; i++) {
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
            if (deckOfCards.isEmpty()) {
                deckOfCards.addAll(deckOfTrash);
                Collections.shuffle(deckOfCards);
            }

            player.addCardToHand(deckOfCards.get(0));
            deckOfCards.remove(0);
        }
    }

    private void playHand(Player player) {
        int playedCard = 0;
        player.displayCardsOnBoard();

        Card dynamite = player.hasDynamite();
        if (dynamite != null) {
            dynamite.action(player, deckOfTrash, listOfPlayers, deckOfCards);
        }

        // prison check

        //playing card
        while (true) {
            boolean validInput = false;

            player.displayCardsOnHand();
            System.out.println("420: nezahraj nic");

            System.out.println("||||||     Ktoru kartu chces zahrat              ||||||");
            playedCard = myObj.nextInt();
            if (playedCard == 420) break;


            //card action
            player.getCardsOnHand().get(playedCard - 1).action(player, deckOfTrash, listOfPlayers, deckOfCards);
        }


        //removing excessive cards

        while (player.getCardsOnHand().size() > player.getLives()) {
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

// dynamit nemat 2x pred sebou opravit
// vazenie dynamit a barel overit
// vstup osetrit pre hrane karty
// ak dojde deckOfCArds neh prehodi trashDeck do deckOfCards osetrit aj pre dostavnik
// instance of overit ci pojde dopici
