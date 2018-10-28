public class Card {

    public final int strength; //number on the card, will dictate it's effect

    public Card(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public static String getCard(int num) {
        if (num == 1) {
            return "Guard, Strength: 1";
        }
        else if (num == 2) {
            return "Priest, Strength: 2";
        }
        else if (num == 3) {
            return "Baron, Strength: 3";
        }
        else if (num == 4) {
            return "Handmaiden, Strength: 4";
        }
        else if (num == 5) {
            return "Prince, Strength: 5";
        }
        else if (num == 6) {
            return "King, Strength: 6";
        }
        else if (num == 7) {
            return "Countess, Strength: 7";
        }
        else {
            return "Princess, Strength: 8";
        }
    }

    public String toString() {
        if (strength == 1) {
            return "Guard, Strength: 1";
        }
        else if (strength == 2) {
            return "Priest, Strength: 2";
        }
        else if (strength == 3) {
            return "Baron, Strength: 3";
        }
        else if (strength == 4) {
            return "Handmaiden, Strength: 4";
        }
        else if (strength == 5) {
            return "Prince, Strength: 5";
        }
        else if (strength == 6) {
            return "King, Strength: 6";
        }
        else if (strength == 7) {
            return "Countess, Strength: 7";
        }
        else {
            return "Princess, Strength: 8";
        }
    }
}