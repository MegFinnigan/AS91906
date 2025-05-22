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
        UI.addButton("Find card", this::findCard);
        UI.addButton("Show all cards", this::displayCards);
        UI.addButton("Hide all cards", this::hideCard);
    }
    
    /**
     * Add a card to the collection 
     */
    public void addCard(){
        this.cards.addCard(); // should the methods have different names to eachother
        //this.displayCards(); // is it fine to just have it not show up until after maybe just print the one card this can be a function with find aswell 
    }
    
    /**
     * Calls the method findCard in Cards
     */
    public void findCard(){
        this.cards.findCard();
    }
    
    /**
     * 
     */
    public void hideCard(){
        UI.clearGraphics();
    }
    
    /**
     * 
     */
    public void displayCards(){
        UI.clearGraphics();
        int x = 50; // Image start position on the x axis
        int y = 50; // Image start position on the y axis
        
        final double WIDTH = 150;
        final double HEIGHT = 200;
        
        for (Card card: cards.collection.values()){ //? getter 
            UI.drawImage(card.getImage(), x, y, WIDTH, HEIGHT);
            UI.drawString("Name: " + card.getName(), x, y + HEIGHT + 20);
            UI.drawString("Price: " + card.getCardPrice(), x, y + HEIGHT + 40);
            x += WIDTH + 30;
            
        }
    
    
    }
    
}
