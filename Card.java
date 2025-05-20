import ecs100.*;
/**
 * Support class for Cards.
 * A Card contains a name, monetary value, and an image.
 *
 * @author Meg Finnigan
 * @version 1.0
 */
public class Card
{
    // Fields 
    private String name;
    private double cardPrice;
    private String image;
    static final String DEFAULT_IMAGE = "card.jpeg";

    /**
     * Constructor for objects of class Card
     */
    public Card(String name, double cardPrice, String image)
    {
        this.name = name;
        this.cardPrice = cardPrice;
        if (image == null) {  
            this.image = DEFAULT_IMAGE;
        }else{
            this.image = image;
        }
    }

     /**
     * Constructor for objects of class Card
     */
    public Card(String name, double cardPrice)
    {
        this.name = name;
        this.cardPrice = cardPrice;
        this.image = DEFAULT_IMAGE;
    }
    
    /**
     * Display the image on GUI
     */
    public void displayImage()
    {
        int locX = 100; // Image start position on the x axis
        int locY = 100; // Image start position on the y axis
        
        final double WIDTH = 250;
        final double HEIGHT = 300;
        
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }
    
    
    /**
     * Getter for name
     * @return returns the String name of the pokemon
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Getter for card price 
     * @return returns the double monetary value of the pokemon card
     */
    public double getCardPrice()
    {
        return this.cardPrice;
    }
}
