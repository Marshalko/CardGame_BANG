package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Card {
    protected String name;//spravit z tohto private
    protected Random random   = new Random();
    protected Scanner scanner = new Scanner(System.in);
    public Card() {
        this.scanner = new Scanner(System.in);
    }
    public abstract void action(Player player, ArrayList<Card> deckOfTresh,ArrayList<Player> listOfPlayers,ArrayList<Card> deckOfCards);//vola potom kartu akciu

    public String getName() {
        return name;
    }


}
