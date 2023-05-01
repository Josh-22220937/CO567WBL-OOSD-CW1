import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * The Event class is responsible for holding information on each event and allows for the 
 * Modification of information about the event * 
 */
public class Event
{
    private Integer eventId;
    private String eventName;
    private String eventDescription;
    private Integer eventDate;
    private HashMap<Integer, Show> eventShows;
    // Initalise the private fields for the Input class

    /**
     * Constructor for objects of class Event
     */
    public Event(Integer id, String name, String desc, Integer date)
    {
        eventId = id;
        eventName = name;
        eventDescription = desc;
        eventDate = date;
        eventShows = new HashMap<Integer, Show>();
        // Give the private fields values that were passed to the object on creation
    }

    /**
     * Returns the events ID
     */
    public Integer getId()
    {
        return eventId;
        // Returns the id of the event
    }
    
    /**
     * Returns the events name
     */    
    public String getName()
    {
        return eventName;
        // Returns the name of the event
    }

    /**
     * Add a show to the event
     */
    public void addShow(Integer id, Show show)
    {
        eventShows.put(id, show);
        // Adds a show to the events show-HashMap
    }
    
    /**
     * Remove a show from the event
     */
    public void removeShow(Integer id) 
    {
        Iterator<Integer> iterator = eventShows.keySet().iterator();
        while (iterator.hasNext()) {
            // Checks over the showId's to look for a match to the inputted Id
            Integer showId = iterator.next();
            if (showId.equals(id)) {
                // If the showId matches the inputted Id, remove it from the shows HashMap
                iterator.remove();
            }
        }
    }   
    
    /**
     * Returns a hashmap of shows in the event
     */
    public HashMap returnShows()
    {
        return eventShows;
        // Returns the shows HashMap
    }
}
