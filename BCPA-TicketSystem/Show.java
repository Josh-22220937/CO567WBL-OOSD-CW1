import java.util.HashMap;
import java.util.Iterator;
/**
 *  The Show class is responsible for holding the information about a specific show
 *  And returning and modifying certain information in the class 
 */
public class Show
{
    private Integer showId;
    private String showName;
    private String showDescription;
    private Integer showDate;
    private Integer showTime;
    private Integer maxSeatsPerCust;
    private Integer promoId;
    private HashMap<Integer, Seat> showSeats;
    // Initalise the private fields for the OnlineTicketSystem class
    
    /**
     * Constructor for objects of class Show
     */
    public Show(Integer id, String name, String desc, Integer date, Integer time, Integer maxSeats)
    {
        showId = id;
        showName = name;
        showDescription = desc;
        showDate = date;
        showTime = time;
        maxSeatsPerCust = maxSeats;
        promoId = null;
        // Give the private fields values with some values that are passed to the class on creation

        HashMap<Integer, Seat> showSeats = new HashMap<Integer, Seat>();
        
        for(int i = 0; i < 15; i++) {
            Integer key = i;
            Seat seat = new Seat(i,showId);
            showSeats.put(i,seat);
        }
        // Populate the HashMap of seats
    }
    
    
    /**
     * Returns the show objects ID
     */
    public Integer getId()
    {
        return showId;
        // Returns the show ID
    }
    
    /**
     * Returns the show objects name
     */
    public String getName()
    {
        return showName;
        // Returns the show name
    }
    
    /**
     * Returns the show objects seat purchase limit
     */
    public Integer getMaxSeats()
    {
        return maxSeatsPerCust;
        // Returns the seat purchase limit
    }
    
    /**
     * Sets the show objects promotion ID
     */
    public void setPromo(Integer promoId)
    {
        this.promoId = promoId;
        // Assign the objects private field to the value passed to the function
    }
    
    /**
     * Returns the promotion ID
     */
    public Integer getPromoId()
    {
        return promoId;
        // Returns the promotion ID
    }
    
    /**
     * Resets the promotion ID to null
     */
    public void removePromo()
    {
        promoId = null;
        // Remove the value of the promotion ID
    }
    
    /**
     * Updates the seat purchase limit
     */
    public void updateMaxSeats(Integer seats)
    {
        maxSeatsPerCust = seats;
        // Assign the objects private field to the value passed to the function
    }
}
