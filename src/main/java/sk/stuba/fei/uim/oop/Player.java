package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.bluecards.Barel;
import sk.stuba.fei.uim.oop.bluecards.BlueCards;
import sk.stuba.fei.uim.oop.bluecards.Dynamite;
import sk.stuba.fei.uim.oop.bluecards.Vazenie;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final int ID;
    private int lives;
    private boolean isDead;
    private boolean isInPrison;
    private final ArrayList<Card> cardsOnHand = new ArrayList<>();
    private final ArrayList<BlueCards> cardsOnBoard = new ArrayList<>();


    public boolean isInPrison() {
        return isInPrison;
    }

    public void setInPrison(boolean inPrison) {
        isInPrison = inPrison;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return isDead;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public ArrayList<BlueCards> getCardsOnBoard() {
        return cardsOnBoard;
    }

    public void addCardToHand(Card card) {
        this.cardsOnHand.add(card);
    }

    public void addCardToBoard(BlueCards card) {
        cardsOnBoard.add(card);
    }

    public void removeCardFromHand(Card card) {
        cardsOnHand.remove(card);
    }

    public void removeCardFromBoard(BlueCards card, ArrayList<Card> deckOfTrash) {
        deckOfTrash.add(card);
        cardsOnBoard.remove(card);

    }


    public void displayCardsOnBoard() {
        System.out.println("-------------------------------------------------------");
        System.out.println("------      Na tahu je hrac  : " + (this.getID() + 1) + "                 ------");
        System.out.println("------      Pocet zivotov je : " + this.getLives() + "                 ------\n");
        System.out.println("------      Modre karty na hracej ploche su:     ------");
        if (this.getCardsOnBoard().size() == 0)
            System.out.println("------      Nemas karty na hracej ploche         ------\n");
        else
            for (int i = 0; i < this.getCardsOnBoard().size(); i++) {
                System.out.println(i + " : " + this.getCardsOnBoard().get(i).getName());
            }
    }

    public void displayCardsOnHand() {
        System.out.println("------      Toto su tvoje karty na ruke :        ------");
        for (int i = 0; i < this.getCardsOnHand().size(); i++)
            System.out.println((i + 1) + " : " + this.getCardsOnHand().get(i).getName());


    }

    public BlueCards hasDynamite() {
        for (int i = 0; i < this.getCardsOnBoard().size(); i++) {
            if (this.getCardsOnBoard().get(i) instanceof Dynamite)
                return this.getCardsOnBoard().get(i);
        }
        return null;
    }


    public BlueCards hasVazenie() {
        for (int i = 0; i < this.getCardsOnBoard().size(); i++) {
            if (this.getCardsOnBoard().get(i) instanceof Vazenie)
                return this.getCardsOnBoard().get(i);
        }
        return null;
    }

    public BlueCards hasBarel() {
        for (int i = 0; i < this.getCardsOnBoard().size(); i++) {
            if (this.getCardsOnBoard().get(i) instanceof Barel)
                return this.getCardsOnBoard().get(i);
        }
        return null;
    }


    public void takeLife(int count) {
        this.lives -= count;
        System.out.println("++++++  " + this.getName() + "stratil zivot a ma uz len " + this.getLives() + " zivotov      ++++++\n");
        if (this.lives < 1) {
            this.isDead = true;
            System.out.println("XXXXXX       A preto " + this.getName() + " zomrel                  XXXXXX");
        }

    }

    public void cardRemove(int removedCard, ArrayList<Card> deckOfTrash) {
        deckOfTrash.add(this.getCardsOnHand().get(removedCard - 1));
        this.removeCardFromHand(this.getCardsOnHand().get(removedCard - 1));
    }

    public Player(String name, int ID, int lives, boolean isDead) {
        this.name = name;
        this.ID = ID;
        this.lives = lives;
        this.isDead = isDead;
    }

}
