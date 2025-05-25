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
    static final String DEFAULT_IMAGE = "default.png";

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
    
    /**
     * Getter for the image
     * @return returns the String image file name
     */
    public String getImage(){
        return this.image;
    }
}
