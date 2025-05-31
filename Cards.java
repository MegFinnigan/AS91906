
import java.util.HashMap;
import java.util.Scanner; // ?
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
    final int MAX_STR_LENGTH = 15; // should this be in the constructor
    
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
        String nameOutput;
        
        do {
            name = UI.askString("Enter the Pokemon name: ").trim();
            
            if (name.isEmpty()){
                UI.println("The name cannot be empty");
            }
            
            // source the website I got this from
            else if (name.matches(".*\\d.*")){
                UI.println("The name cannot contain numbers");
            }
            
            else if (name.length() > MAX_STR_LENGTH){
                UI.println("The name must be less than " + MAX_STR_LENGTH + " characters");
            }
            
            // There is an error if I normalise the input when they put ana empty string
            else{
                // Make sure the first letter is capatilised. normalise the string
                nameOutput = name.substring(0, 1).toUpperCase() + name.substring(1);
        
                if (collection.containsKey(nameOutput)){
                    UI.println("This Pokemon card already exists");
                    return null;
                }
            
                else{
                    break;
                }
            }
        }while (true);
        

        do {
            cardPrice = UI.askDouble("Enter the pokemon card value: ");
            
            // should I do constants 
            if (cardPrice < 1 || cardPrice > 100000){
                UI.println("The value must be between 1 and 100,000");
            }
            
        }while (cardPrice < 1 || cardPrice > 100000);
        
        String imgFileName = UIFileChooser.open("Choose the image file: ");
        UI.println("Added");
        
        Card card = new Card(nameOutput, cardPrice, imgFileName);
        
        this.collection.put(nameOutput, card); 
        
        return card;
    }
    
    /**
     * 
     */
    public Card findCard(){
        double cardPrice; // should this be at the top of the page
        String image;
        String name; // declare this at the start
        String nameOutput;
        
        
        // make another method to reuse this code as its repetitve
        do {
            name = UI.askString("Enter the Pokemon name: ").trim();
            
            if (name.isEmpty()){
                UI.println("The name cannot be empty");
            }
            
            // source the website I got this from
            else if (name.matches(".*\\d.*")){
                UI.println("The name cannot contain numbers");
            }
            
            else if (name.length() > MAX_STR_LENGTH){
                UI.println("The name must be less than " + MAX_STR_LENGTH + " characters");
            }
            
            // There is an error if I normalise the input when they put ana empty string
            else{
                // Make sure the first letter is capatilised. normalise the string
                nameOutput = name.substring(0, 1).toUpperCase() + name.substring(1);
                break;
            }
        }while (true);
        
        Card card = this.collection.get(nameOutput);
        
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
