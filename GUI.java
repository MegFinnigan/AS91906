import ecs100.*;
/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // Fields
    private Cards cards;
    private Card card; 

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        this.cards = new Cards(); // is this needed 
        
        UI.initialise();
        UI.addButton("Add card", this::addCard);
        //UI.addButton("Find card", this::findCard);
    }
    
    /**
     * Add a card to the collection
     */
    public void addCard(){ // should I find a more discriptive name diff to addcard
        double cardPrice; // should this be in the do block
        
        String name = UI.askString("Enter the Pokemon name: "); //Force an answer
        
        do {
            cardPrice = UI.askDouble("Enter the pokemon card value: ");
            
            if (cardPrice > 0){
                UI.println("Added");
            }
            else if (cardPrice < 0){  // should this include 0
                UI.println("Must be greater than 0");
            }
            else{
                UI.println("Must be a number");
            }
            
        }while (0 > cardPrice);
        
        String imgFileName = UIFileChooser.open("Choose the image file: ");
        this.cards.addCard(name, cardPrice, imgFileName);
    
    }
    
}
