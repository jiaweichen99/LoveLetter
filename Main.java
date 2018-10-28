import java.util.*;

public class Main {

    public static void printCardList () {
        System.out.println("Guard, Strength: 1");
        System.out.println("Priest, Strength: 2");
        System.out.println("Baron, Strength: 3");
        System.out.println("Handmaiden, Strength: 4");
        System.out.println("Prince, Strength: 5");
        System.out.println("King, Strength: 6");
        System.out.println("Countess, Strength: 7");
        System.out.println("Princess, Strength: 8");
    }

    public static void main (String[] args) {
        System.out.println ("Welcome to Love Letter!");
        System.out.println ("Bring another person: let's see who will be the last one standing.");
        System.out.println ();

        Scanner input = new Scanner(System.in);
        int temp = 0;
        int turn = 1;
        int numPlayers = 2;
        int deckIndex = 1;  // starts at one, we will be going until we are out of cards
        Card playersCards[] = new Card[4];

        //manually take the card deck and populate them

        Card deck[] = new Card[16]; // we gather all 16 cards here
        for (int i = 0; i < 5; i++) {
            deck[i] = new Card(1);
        }
        deck[5] = new Card(2);
        deck[6] = new Card(2);
        deck[7] = new Card(3);
        deck[8] = new Card(3);
        deck[9] = new Card(4);
        deck[10] = new Card(4);
        deck[11] = new Card(5);
        deck[12] = new Card(5);
        deck[13] = new Card(6);
        deck[14] = new Card(7);
        deck[15] = new Card(8);

        /*
        for (int i = 0; i < 16; i++) {
            System.out.println (deck[i].toString());
        }
        */
        System.out.println ("Shuffling deck...");
        //shuffle our array
        for (int i = 0; i < 16; i++) {

            int index = (int)(Math.random()*16);

            //swap cards
            Card a = deck[index];
            deck[index] = deck[i];
            deck[i] = a;
        }

        /*
        for (int i = 0; i < 16; i++) {
            System.out.println (deck[i].toString());
        }
        */

        // for simplicity's sake, will we just be having two players
        // after all, it is a bit unwieldy to play with just a terminal for multiple ppl anyways
        /*
        while (temp == 0) {
            try {
                System.out.println("Please select an option: ");
                numPlayers = Integer.parseInt(input.nextLine());
                if (numPlayers < 2 || numPlayers > 4) {
                    throw new IllegalArgumentException("Number of players is not between 2 and 4!");
                }
                temp = 1;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
        */

        System.out.println ("Dealing cards...");
        System.out.println ();

        //starting hands of the players
        playersCards[0] = deck[1];
        playersCards[2] = deck[2];
        deckIndex = deckIndex + 2;
        int choice = 0;
        boolean handmaiden = false;
        String enter;

        // keep going until someone loses or we go through the deck
        while (deckIndex < 16) {
            for (int i = 0; i < 2; i++) {

                if (deckIndex == 16) {
                    break;
                }

                System.out.println ("Turn " + turn);

                System.out.println ("Player " + (i+1) + "'s Turn");
                System.out.println ("Player " + (i+1) + " has drawn a card.");

                // we have an array of size 4, two slots are allocated for each player
                // a player will have at most 2 cards at any given time
                playersCards[i*2 + 1] = deck[deckIndex];
                deckIndex++;

                System.out.println ("Player " + (i+1) + "'s current hand: ");
                System.out.println (playersCards[i*2]);
                System.out.println (playersCards[i*2 + 1]);

                // testing purposes
                System.out.println();
                for (int j = 0; j < 4; j++) {
                    System.out.println (playersCards[j]);
                }

                while (temp == 0) {

                    System.out.println("Enter 0 or 1 to play the first or second card respectively.");
                    System.out.println();
                    try {
                        choice = Integer.parseInt(input.nextLine());
                        if (choice != 0 && choice != 1) {
                            throw new IllegalArgumentException("Enter 0 or 1!");
                        }
                        else {
                            temp = 1;
                        }
                    }
                    catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                temp = 0;


                Card c = playersCards[i*2 + choice];
                int a = c.getStrength();

                if (handmaiden && a != 4 && a != 7 && a != 8) {
                    a = 9;
                    handmaiden = false;
                }

                if (a == 1) {
                    System.out.println ("Declare a number to check the opponents hand for!");
                    System.out.println ("Here's the full card list for reference: ");
                    System.out.println ("Remember: you can't declare Guard.");
                    printCardList();

                    int declaration = 0;

                    while (temp == 0) {
                        try {
                            declaration = Integer.parseInt(input.nextLine());
                            if (declaration < 2 || declaration > 8) {
                                throw new IllegalArgumentException("Card must be from 2-8!");
                            }
                            else {
                                temp = 1;
                            }
                        }
                        catch (IllegalArgumentException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    temp = 0;

                    //check whose turn it currently is and use the card on the enemy player
                    // player 1 turn, check player 2 hand
                    if (i == 0) {
                        Card enemy = playersCards[2];
                        if (declaration == enemy.getStrength()) {
                            System.out.println ("Card guessed correctly!");
                            System.out.println ("Player 2 has been eliminated");
                            System.out.println ("Player 1 wins!");
                            System.exit(0);
                        }
                        else {
                            System.out.println ("Incorrect guess!");
                        }
                    }
                    // player 2 turn, check player 1 hand
                    else {
                        Card enemy = playersCards[0];
                        if (declaration == enemy.getStrength()) {
                            System.out.println ("Card guessed correctly!");
                            System.out.println ("Player 1 has been eliminated");
                            System.out.println ("Player 2 wins!");
                            System.exit(0);
                        }
                        else {
                            System.out.println ("Incorrect guess!");
                        }
                    }



                }

                else if (a == 2) {
                    //player 1 sees player 2's hand
                    if (i == 0) {
                        System.out.println ("Player 2 has: ");
                        System.out.println (playersCards[2]);
                    }
                    //player 2 sees player 1's hand
                    else {
                        System.out.println ("Player 1 has: ");
                        System.out.println (playersCards[0]);
                    }
                }

                //compare hands
                else if (a == 3) {

                    System.out.println ("Showdown! Compare hands to see who has the bigger hand.");
                    //shift into correct place for comparison
                    if (choice == 0) {
                        playersCards[i * 2] = playersCards[i * 2 + 1];
                    }

                    System.out.println ("Player 1 is holding: ");
                    System.out.println (playersCards[0]);
                    System.out.println ();
                    System.out.println ("Player 2 is holding: ");
                    System.out.println (playersCards[2]);
                    System.out.println ();

                    if (playersCards[0].getStrength() > playersCards[2].getStrength()) {
                        System.out.println ("Player 1 wins!");
                        System.exit(0);
                    }
                    else if (playersCards[0].getStrength() < playersCards[2].getStrength()) {
                        System.out.println ("Player 2 wins!");
                        System.exit(0);
                    }

                }
                else if (a == 4) {
                    System.out.println ("Player " + (i+1) + " receives the handmaiden protection!");
                    System.out.println ("They will not be affected by opposing card effects. ");
                    handmaiden = true;
                }
                else if (a == 5) {
                    System.out.println ("Which player should discard a card and draw a new one? (1 or 2)");
                    int choose = -1;
                    while (temp == 0) {
                        try {
                            choose = Integer.parseInt(input.nextLine());
                            if (choose != 1 && choose != 2) {
                                throw new IllegalArgumentException("Choose Player 1 or Player 2!");
                            }
                            else {
                                temp = 1;
                            }
                        }
                        catch (IllegalArgumentException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    temp = 0;

                    if ((i+1) == choose) {
                        playersCards[i * 2] = playersCards[i * 2 + 1];
                    }

                    if (choose == 1) {
                        System.out.println ("Player " + (choose) + " discards a card.");
                        System.out.print ("Card discarded: ");
                        System.out.println (playersCards[0]);
                        if (playersCards [0].getStrength() == 8) {
                            System.out.println ("The princess has been killed!");
                            System.out.println ("Player 2 wins the game!");
                            System.exit(0);
                        }
                        else {
                            playersCards [0] = deck[deckIndex++];
                        }

                    }
                    else {
                        System.out.println ("Player " + (choose) + " discards a card.");
                        System.out.print ("Card discarded: ");
                        System.out.println (playersCards[2]);
                        if (playersCards [2].getStrength() == 8) {
                            System.out.println ("The princess has been killed!");
                            System.out.println ("Player 1 wins the game!");
                            System.exit(0);
                        }
                        else {
                            playersCards [2] = deck[deckIndex++];
                        }

                    }
                }

                else if (a == 6) {
                    System.out.println ("Swapping cards between Player 1 and Player 2...");

                    // edge case, king is the first card, shift second card over
                    if (playersCards[i*2].getStrength() == 6) {
                        playersCards[i*2] = playersCards[i*2 + 1];
                    }

                    //swap cards
                    Card z = playersCards[0];
                    playersCards[0] = playersCards[2];
                    playersCards[2] = z;
                }

                else if (a == 7) {
                    System.out.println ("Countess has been played.");
                }

                else if (a == 8) {
                    System.out.println ("What?! The princess has been killed! We must find who did this!");
                    System.out.println ("Player " + (i+1) + " has lost the game!");
                    if (i == 0) {
                        System.out.println ("Player 2 wins the game!");
                        System.exit(0);
                    }
                    else {
                        System.out.println ("Player 1 wins the game!");
                        System.exit(0);
                    }
                }
                else {
                    System.out.println ("Opposing player is protected by handmaiden!");
                }

                // if we choose the first card, we need to shift over to signify the card has been used
                // the second card will get overwritten naturally by program
                if (choice == 0) {
                    playersCards[i * 2] = playersCards[i * 2 + 1];
                }

                System.out.println ();
                turn = turn + 1;

                /*
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println ("Switching Players...");
                System.out.println ("Enter anything to continue.");
                enter = input.nextLine();
                */
            }

        }

        System.out.println ("No cards left in deck!");
        System.out.println ("Let's see who's holding the highest card!");
        System.out.println ("Player 1 is holding: ");
        System.out.println (playersCards[0]);
        System.out.println ();
        System.out.println ("Player 2 is holding: ");
        System.out.println (playersCards[2]);
        System.out.println ();

        if (playersCards[0].getStrength() > playersCards[2].getStrength()) {
            System.out.println ("Player 1 wins!");
        }
        else if (playersCards[0].getStrength() < playersCards[2].getStrength()) {
            System.out.println ("Player 2 wins!");
        }
        else {
            System.out.println ("It seems to be a tie...for now!");
        }



    }

}