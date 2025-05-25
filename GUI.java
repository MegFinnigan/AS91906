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
    private Cards cards; // ?
    private Card card; 
    
    private static int x = 50; //
    private static int y = 50; //
    private Card hiddenCard = null;

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
        
        UI.setMouseListener(this::doMouse);
        
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
        x = 50; //  
    }
    
    /**
     * Draws all the cards in collections
     */
    public void displayCards(){
        UI.clearGraphics();
        x = 50;
        for (Card card: cards.collection.values()){ // make a getter 
            if (card == hiddenCard){
                continue;
            }
            
            drawCard(card);
        }
        hiddenCard = null; // this resets it so if all the cards are printed again it isnt skipped
    }
    
    /**
     * Draws a card
     */
    public static void drawCard(Card card){ // is it okay that this is static
        final int WIDTH = 150;
        final int HEIGHT = 200;
        
        UI.drawImage(card.getImage(), x, y, WIDTH, HEIGHT);
        UI.drawString("Name: " + card.getName(), x, y + HEIGHT + 20);
        UI.drawString("Price: " + card.getCardPrice(), x, y + HEIGHT + 40);
        x += 150 + 30; // 150 should be width declare it as field? 
    }
    
    /**
     * Prints out the image and details for a single card.
     */
    public static void drawSingleCard(Card card){
        UI.clearGraphics();
        x = 50; // set x-axis back to 50px
        drawCard(card);
    }
    
    
    public void doMouse(String action, double mouseX, double mouseY){
        if (action.equals("clicked")){
            final int WIDTH = 150;
            final int HEIGHT = 200;
            x = 50;
            for (Card card: cards.collection.values()){
                if (mouseX >= x && mouseX <= x + WIDTH && mouseY >= y && mouseY <= y + HEIGHT){
                    hiddenCard = card;
                    displayCards();
                    return;
                }
                x += WIDTH + 30;
            }
    
        } // I can always just cover it but then there would be gaps 
    
    }
}
