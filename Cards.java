
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
    public HashMap<String, Card> collection; // How can I make this private!!!
    private Scanner scanner;
    
    /**
     * Constructor for objects of class Cards
     */
    public Cards()
    {
        // initialise instance variables
        collection = new HashMap<String, Card>();
        //scanner = new Scanner(System.in); //?
        
        // add cards to the collection
        this.collection.put("Charizard", new Card("Charizard", 200, "Charizard.jpg"));
        this.collection.put("Pikachu", new Card("Pikachu", 1000, "Pikachu.png"));
        this.collection.put("Snivy", new Card("Snivy", 400, "Snivy.jpg")); 
    }
    
    
    /**
     * 
     */
    public Card addCard(){

        double cardPrice;
        String name;
        
        do {
            name = UI.askString("Enter the Pokemon name: ").trim();
            
            if (name.isEmpty()){
                UI.println("The name cannot be empty");
            }
            
            else if (collection.containsKey(name)){
                UI.println("This Pokemon card already exists");
                return null;
            }
            
        }while (name.isEmpty());
        
        
        do {
            cardPrice = UI.askDouble("Enter the pokemon card value: ");
            
            if (cardPrice > 0){
                //UI.println("Added");
            }
            else if (cardPrice <= 0){
                UI.println("Must be greater than 0");
            }
            else{
                UI.println("Must be a number");
            }
            
        }while (0 >= cardPrice);
        
        String imgFileName = UIFileChooser.open("Choose the image file: ");
        UI.println("Added");
        
        Card card = new Card(name, cardPrice, imgFileName);
        
        this.collection.put(name, card); 
        
        return card;
    }
    
    /**
     * 
     */
    public Card findCard(){
        double cardPrice; // should this be at the top of the page
        String image;
        
        String name = UI.askString("Enter the name of the Pokemon card: ");
        
        Card card = this.collection.get(name);
        
        if (card == null){
            UI.println("That card is not in your collection!");
        }else{
            name = card.getName();
            cardPrice = card.getCardPrice();
            image = card.getImage();
            // create a method in GUI and pass in as param
            GUI.drawSingleCard(card); //should this be in GUI
            
        }
        return card; // does it matter if it returns a card that doesnt exist, 
    }
    
    // clear one card when it is tapped 
}
