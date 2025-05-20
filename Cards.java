
import java.util.HashMap;
import java.util.Scanner;
import ecs100.*;
/**
 * Holds a collection of pokemon cards in a hashmap
 * Allows a user to add a card, find a card, and hide a cards details
 *
 * @author Meg Finnigan
 * @version 1.0
 */
public class Cards
{
    // fields 
    private HashMap<String, Card> collection; // would the name be the key?
    private Scanner scanner;
    
    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // initialise instance variables
        collection = new HashMap<String, Card>();
        scanner = new Scanner(System.in);
        
        Card c1 = new Card("Charizard", 200);
        Card c2 = new Card("Pikachu", 1000, ""); // how to put an image
        Card c3 = new Card("Snivy", 400, "");
    }

}
