import ecs100.*;
import java.util.*;

/**
 * This class handles all the user interactions through the graphical user interface.
 * 
 * It allows the user to add new cards to the collection,find and display a specific card from the collection,
 show all cards in the collection, hide all cards in the collection, and click on cards to hide them.
 * 
 * The cards are displayed in rows of four.
 * The cards are drawn so their name and value are printed underneath their image. 
 *
 * @author Meg Finnigan
 * @version 1.0
 */
public class GUI
{
    // Fields
    private Cards cards;
    // This is a list which stores Card objects that the user has clicked to hide.
    // These cards won't be shown on the screen when displayCards in called.
    private List<Card> hiddenCards = new ArrayList<>();
    // This tracks whether the cards have been drawn on the screen yet.
    private boolean hasPrintedCards = false;
    
    private final int CARD_WIDTH = 150;
    private final int CARD_HEIGHT = 200;

    /**
     * Constructor for the graphical user interface.
     * 
     * Sets up buttons, sets up mouse input, and hides all the cards by default.
     */
    public GUI()
    {
        // initialise instance variables
        this.cards = new Cards();
        
        // Set up the UI and add buttons
        UI.initialise();
        UI.addButton("Add card", this::handleAddCard);
        UI.addButton("Find card", this::handleFindCard);
        UI.addButton("Show all cards", this::showAllCards);
        UI.addButton("Hide all cards", this::hideCards);
        
        // Set the mouse input listener
        UI.setMouseListener(this::doMouse);
    
        // Adds every card from 'collection' to the hiddenCards list, so none are shown when the GUI first loads.
        for (Card card: cards.collection.values()){
            hiddenCards.add(card);
        }
    }
    
    /**
     * This method handles adding a new card to the HashMap 'collection'.
     * 
     * Calls the addCard method from the Cards class to create and return a new card.
     * If no cards have been displayed, it draws only the new card.
     * If cards have already been displayed, it draws the cards again, including the new one.
     */
    public void handleAddCard(){
        Card newCard = cards.addCard();
    
        // If no card was returned because the user tried to add a duplicate card, exit the method. 
        if (newCard == null){
            return;
        }
        
        // If no cards have been displayed, draw only the new card.
        if (hasPrintedCards == false){
            drawSingleCard(newCard);
        }else{
            // Otherwise print all the cards to inlcude the new one.
            displayCards();
        }
    }
    
    /**
     * This method handles finding a Pokemon card in the HashMap 'collection'.
     * 
     * Calls the findCard method from the Cards class to find and then return the card.
     * If the card is found, all the other cards are added to the hiddenCards list so only the found card is shown.
     * If the found card was hidden before, it is removed from the hiddenCards list so it can be displayed. 
     */
    public void handleFindCard(){
        Card foundCard = this.cards.findCard();
        
        // If no card was returned, exit the method. 
        if (foundCard == null){
            return;
        }
        
        // Add all the other cards to the hiddenCards list so only the foundCard is visable
        for (Card card: cards.collection.values()){
            if (card != foundCard){
                hiddenCards.add(card);
            }
        }
        
        // If the foundCard is in hiddenCards, remove it so it becomes visable.
        if (hiddenCards.contains(foundCard)){
            hiddenCards.remove(foundCard);
        }
    }
    
    /**
     * This method shows all the cards in 'collection' by clearing the hiddenCards list and calling
    displayCards to redraw every card on the screen.
     */
    public void showAllCards(){
        hiddenCards.clear();
        displayCards();
    }
    
    /**
     * This method hides all the cards by clearing the graphics from the screen.
     */
    public void hideCards(){
        UI.clearGraphics();
    }
    
    /**
     * This method draws all the visable cards from the 'collection'.
     * 
     * It loops through each card in 'collection' and the cards that are in hiddenCards list are skipped.
     * The visable cards are drawn in rows of four. For each card drawn, the x-posistion is increased 
     so the cards sit next to each other. After every fourth card, the x-position is reset to 50 and 
     the y-position is increased to move to a new row.
     * 
     */
    public void displayCards(){
        // Clear the graphics from the screen
        UI.clearGraphics();
        
        // Set the starting position for drawing the cards
        int xPosition = 50;
        int yPosition = 50;
        int cardsPerRow = 4;
        // This will track how many cards have been printed out
        int cardCount = 0;
        
        // Loop through each card in 'collection'
        for (Card card: cards.collection.values()){
            if (hiddenCards.contains(card)){  // Skip the card if it's inside hiddenCards
                continue;
            }else{
                // Draw the card at the current x and y positions
                drawCard(card, xPosition, yPosition);
                cardCount += 1; 
            
                // If the row of four is full, reset the xPosition and move y down to start the new row
                if (cardCount % cardsPerRow == 0){
                    xPosition = 50;
                    yPosition = CARD_HEIGHT + 110; // 110 is the spacing between the rows 
                
                }else{
                    // Otherwise, move the x position to the right for the next card
                    xPosition += CARD_WIDTH + 30; // 30 is the spacing in between the images
                }
            }
        }
        // Mark that the cards have been printed
        hasPrintedCards = true;
    }
    
    /**
     * Draws a card on the screen at the given position.
     * 
     * The card image is drawn at the given x and y positions
     * The cards name and price are displayed below the image
     * 
     * @param card the Card object to be drawn 
     * @param xPosition the x-coordinate where the image is drawn
     * @param yPosition the y-coordinate where the image is drawn
     */
    public void drawCard(Card card, int xPosition, int yPosition){
        UI.drawImage(card.getImage(), xPosition, yPosition, CARD_WIDTH, CARD_HEIGHT);
        UI.drawString("Name: " + card.getName(), xPosition, yPosition + CARD_HEIGHT + 20);
        UI.drawString("Price: " + card.getCardPrice(), xPosition, yPosition + CARD_HEIGHT + 40); 
    }
    
    /**
     * Clears the screen and draws the image and details of a single card.
     * 
     * This method clears the graphics from the screen, then draws the given card at the first position
     * in the grid layout
     * 
     * @param card the Card object to be drawn 
     */
    public void drawSingleCard(Card card){
        UI.clearGraphics();
        int xPosition = 50; 
        int yPosition = 50;
        drawCard(card, xPosition, yPosition);
    }
    
    /**
     * Handles the click actions on the displayed cards.
     * 
     * This method checks if the user has clicked on a visable card. It loops through the cards in 'collection',
     * any that are in hiddenCards list are skipped. It checks whether the mouse click position falls within the 
     * card's image on the screen. 
     * 
     * If a card is clicked, it's added to the hiddenCards list and the display is drawn again to see the change.
     * The x and y positions are updated after each card to match the grid layout.
     * 
     * @param action The mouse action that is made
     * @param mouseX The x-coordinate of the mouse when it was clicked
     * @param mouseY The y-coordinate of the mouse when it was clicked
     */
    public void doMouse(String action, double mouseX, double mouseY){
        // Set the starting positions of the cards. Where they would be printed out 
        int xPosition = 50;
        int yPosition = 50;
        int cardsPerRow = 4;
        // This will track how many cards have been printed out
        int cardCount = 0;
        
        if (action.equals("clicked")){
            for (Card card: cards.collection.values()){ // Loop through each card in 'collection'
                if (hiddenCards.contains(card)){ // Skip the card if it's inside hiddenCards
                    continue;
                }
                
                // If the mouse click position falls within the card's image on the screen add the card to hiddenCards. 
                if (mouseX >= xPosition && mouseX <= xPosition + CARD_WIDTH && mouseY >= yPosition && mouseY <= yPosition + CARD_HEIGHT){
                    hiddenCards.add(card); 
                    displayCards(); // display all the visable cards again to see the change 
                    return; // exit the method
                }
                
                cardCount += 1;
                // If the row of four is full, reset the xPosition and move y down to start the new row
                if (cardCount % cardsPerRow == 0){
                    xPosition = 50;
                    yPosition = CARD_HEIGHT + 110; 
                }else{
                    // Otherwise, move the x position to the right for the next card
                    xPosition += CARD_WIDTH + 30; 
                }
            }
        }
    }
}
