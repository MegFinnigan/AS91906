import ecs100.*;
import java.util.*;

/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // Fields
    private List<Card> hiddenCards = new ArrayList<>(); // used geeksforgeeks 
    private Cards cards; // ?
    private Card card; 
    
    private boolean hasPrintedCards;

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
        UI.addButton("Show all cards", this::showAllCards);
        UI.addButton("Hide all cards", this::hideCards);
        
        UI.setMouseListener(this::doMouse);
        
        hasPrintedCards = false;
        
    }
    
    /**
     * Add a card to the collection 
     */
    public void addCard(){
        Card card = this.cards.addCard(); // should the methods have different names to eachother
        // change the variable name
        if (hasPrintedCards == false){
            drawSingleCard(card);
        }else{
            displayCards();
            hasPrintedCards = true; //? do
        }
    }
    
    /**
     * Calls the method findCard in Cards
     */
    public void findCard(){
        Card foundCard = this.cards.findCard(); // change variable name
        // add all the other cards to hiddenlist 
        for (Card card: cards.collection.values()){ // make a getter 
            if (card != foundCard){
                hiddenCards.add(card);
            }
            }
        
    }
    
    /**
     * 
     */
    public void hideCards(){
        UI.clearGraphics();
        //int x = 50; //  
    }
    
    public void showAllCards(){
        hiddenCards.clear();
        displayCards();
    
    }
    
    
    
    /**
     * Draws all the cards in collections
     */
    public void displayCards(){
        UI.clearGraphics();
        int x = 50;
        int y = 50;
        for (Card card: cards.collection.values()){ // make a getter 
            if (hiddenCards.contains(card)){
                continue;
            }else{
                drawCard(card, x, y);
                x += 150 + 30; // 150 should be width declare it as field?
            }
            
        }
        hasPrintedCards = true;
         // this resets it so if all the cards are printed again it isnt skipped
    }
    
    /**
     * Draws a card
     */
    public static void drawCard(Card card, int x, int y){ // is it okay that this is static
        final int WIDTH = 150;
        final int HEIGHT = 200;
        
        UI.drawImage(card.getImage(), x, y, WIDTH, HEIGHT);
        UI.drawString("Name: " + card.getName(), x, y + HEIGHT + 20);
        UI.drawString("Price: " + card.getCardPrice(), x, y + HEIGHT + 40); 
    }
    
    /**
     * Prints out the image and details for a single card.
     */
    public static void drawSingleCard(Card card){
        UI.clearGraphics();
        int x = 50; // set x-axis back to 50px
        int y = 50;
        drawCard(card, x, y);
    }
    
    
    public void doMouse(String action, double mouseX, double mouseY){
        int x = 50;
        int y = 50;
        final int WIDTH = 150;
        final int HEIGHT = 200;
        
        if (action.equals("clicked")){
            
            for (Card card: cards.collection.values()){
                if (hiddenCards.contains(card)){
                    continue;
                }
                
                
                
                if (mouseX >= x && mouseX <= x + WIDTH && mouseY >= y && mouseY <= y + HEIGHT){
                    hiddenCards.add(card);
                    displayCards();
                    return;
                }
                x += WIDTH + 30;
                }
            }
        } // I can always just cover it but then there would be gaps
    }

