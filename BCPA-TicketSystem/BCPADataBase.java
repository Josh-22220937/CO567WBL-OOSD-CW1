import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * The BCPADataBase class is responsible for holding all the events, shows and promotions in the system
 * And also for adding and removing them from said system
 */
public class BCPADataBase
{
    private HashMap<Integer, Event> events;
    private HashMap<Integer, Show> shows;
    private HashMap<Integer, Promotion> promos;
    // Initalise the private fields for the BCPADataBase class

    /**
     * Constructor for objects of class BCPADataBase
     */
    public BCPADataBase()
    {
        events = new HashMap<Integer, Event>();
        shows = new HashMap<Integer, Show>();
        promos = new HashMap<Integer, Promotion>();
        // Give the private fields values
    }
    
    /**
     * Add a new event to the BCPADataBase
     */
    public void addEvent(Integer id, Event newEvent)
    {
        events.put(id, newEvent);
        // Add the event to the event HashMap
    }
    
    /**
     * Add a new show to the BCPADataBase
     */
    public void addShow(Integer id, Show newShow)
    {
        shows.put(id, newShow);
        // Add the show to the show HashMap
    }
    
    /**
     * Add a new promotion to the BCPADataBase
     */
    public void addPromo(Integer id, Promotion newPromo)
    {
        promos.put(id, newPromo); 
        // Add the promotion to the promo HashMap
    }
    
    /**
     * Removes an event from the BCPADataBase
     */
    public void removeEvent(Integer id)
    {
        Set<Integer> eventKeys = events.keySet();
        Iterator<Integer> eventKeyIt = eventKeys.iterator();
        while (eventKeyIt.hasNext()) {
            // Checks over the eventId's to look for a match to the inputted Id
            Integer eventKey = eventKeyIt.next();
            if (eventKey == id) {
                // If the entered Id matches to an eventId, remove that event from the DB
                eventKeyIt.remove();
            }
        }   
    }
    
    /**
     * Removes all occurances of a show matching the given id, the show is removed from the 
     * BCPADataBase and also from any event objects that were holding that show
     */
    public void removeShow(Integer id)
    {
        // First remove the Show from the internal DB
        Set<Integer> showKeys = shows.keySet();
        Iterator<Integer> showKeyIt = showKeys.iterator();
        while (showKeyIt.hasNext()) {
            // Iterates over the shows HashMap creating a set of showId keys
            Integer showKey = showKeyIt.next();
            if (showKey == id) {
                // If the current showId key being checked matches the showId entered
                // the showId is removed from the BCPADataBase
                showKeyIt.remove();
            }
        }
        
        // Secondly remove the show from any events in the DB the show occurs in
        Set<Integer> eventKeys = events.keySet();
        for (Iterator<Integer> eventIt = eventKeys.iterator(); eventIt.hasNext(); ) {
            // Iterates over the events in the BCPADatabase creating a set of eventId keys
            Integer eventKey = eventIt.next();
            // Assign local event object to the eventId key
            Event event = events.get(eventKey);
            HashMap<Integer, Show> eventShows = event.returnShows();
            Set<Integer> eventShowKeys = eventShows.keySet();
            for (Iterator<Integer> eventShowIt = eventShowKeys.iterator(); eventShowIt.hasNext(); ) {
                // Iterates over the Shows in the current event creating a set of showId keys
                Integer eventShowKey = eventShowIt.next();
                if (eventShowKey == id) {
                    // If the current showId matches the showId entered, the show is removed
                    eventShowIt.remove();
                    event.removeShow(id);
                }
            }
        }   
    }
    
    /**
     * Removes a specifc show from a specific event, using their respective Id's
     */
    public void removeShow(Integer eventId, Integer showId)
    {
        Set<Integer> eventKeys = events.keySet();
        for(Integer eventKey : eventKeys) {
            // Iterates over the events in the BCPADataBase creating a set of eventId keys
            if (eventKey == eventId) {
                Event event = events.get(eventKey);
                // If the eventKey found matches the eventId entered
                // Assign a local event object to the event object matching the key
                HashMap<Integer, Show> eventShows = event.returnShows();
                Set<Integer> eventShowKeys = eventShows.keySet();
                Iterator<Integer> eventShowKeyIterator = eventShowKeys.iterator();
                while (eventShowKeyIterator.hasNext()) {
                    // Iterates over the Shows stored in the event creating a set of showId keys
                    Integer eventShowKey = eventShowKeyIterator.next();
                    if (eventShowKey == showId) {
                        // If the eventShowKey found matches the showId entered
                        eventShowKeyIterator.remove();
                        event.removeShow(showId);
                        // The show is removed from the events HashMap of shows
                    }
                }
            }
        }           
    }
    
    /**
     * Removes a specific promo from the BCPADataBase aswell as any shows that contain it
     */
    public void removePromo(Integer promoId)
    {
        // First remove the promo from the internal DB
        Set<Integer> promoKeys = promos.keySet();
        Iterator<Integer> promoKeyIt = promoKeys.iterator();
        while (promoKeyIt.hasNext()) {
            // Iterates over the promos stored in the BCPADatabase creating a set of showId keys
            Integer promoKey = promoKeyIt.next();
            if (promoKey == promoId) {
                // If the promoKey matches the promo entered 
                promoKeyIt.remove();
                // Remove the promo from the BCPADataBase
            }
        }
        
        // Secondly remove the promo from any shows in the DB the promo occurs in
        Set<Integer> showKeys = shows.keySet();
        for (Iterator<Integer> showIt = showKeys.iterator(); showIt.hasNext(); ) {
            // Iterates over the shows stored in the BCPADatabase creating a set of showId keys
            Integer showKey = showIt.next();
            Show show = shows.get(showKey);
            // Assign a local variable to the show object matching the entered showId
            if (show.getPromoId() == promoId) {
                show.removePromo(); 
                // If the promoId of the show matches the promoId entered, then remove the promo from the show
            }
        }
    }

    /**
     * Removes a promo from one specfic show
     */
    public void removePromo(Integer showId, Integer promoId)
    {
        Set<Integer> showKeys = shows.keySet();
        for(Integer showKey : showKeys) {
            // Checks through the shows in the DB to see if the entered showId exists
            if (showKey == showId) {
                // If showId exists in the DB assign a local variable to the show object
                Show show = shows.get(showId);
                if (show.getPromoId() == promoId) {
                    // If the shows promoId matches the entered promoId, remove it from the show
                    show.removePromo();
                }
            }
        }         
    }
    
    /**
     * Add a specific show to a specific event, using their respective Id's
     */
    public void addShowToEvent(Integer eventId, Integer showId)
    {
        Set<Integer> eventKeys = events.keySet();
        for(Integer eventKey : eventKeys) {
            // Checks through the events in the DB to see if the entered eventId exists
            if (eventKey == eventId) {
                // If event is found, assign local variable to event object
                Event event = events.get(eventKey);
                // Look through the shows database
                Set<Integer> showKeys = shows.keySet();
                for(Integer showKey : showKeys) {
                    if (showKey == showId) {
                        // If show is found, assing local variable to show object
                        Show show = shows.get(showId);
                        // Use the event objects function to add show object to the events show hashmap
                        event.addShow(showId, show);
                    }
                }
            }
        }
    }
    
    /**
     * Adds a promo to a show using their respective ID's
     */
    public void addPromoToShow(Integer showId, Integer promoId)
    {       
        Set<Integer> showKeys = shows.keySet();
        for(Integer showKey : showKeys) {
            // Checks through the shows in the DB to see if the entered showId exists
            if (showKey == showId) {
                // If showId exists in the DB assign a local variable to the show object
                Show show = shows.get(showId);                
                Set<Integer> promoKeys = promos.keySet();
                for(Integer promoKey : promoKeys) {
                    // Checks through the promos in the DB to see if the entered promoId exists
                    if (promoKey == promoId) {
                        // If the promo exists assign its Id to the show
                        show.setPromo(promoId); 
                    }
                }
            }
        }
    }  
    
    /**
     * Returns the current HashMap of events
     */
    public HashMap returnEvents()
    {
        return events;
        // Return the events HashMap
    }
    
    /**
     * Returns the current HashMap of shows
     */
    public HashMap returnShows()
    {
        return shows;
        // Return the shows HashMap
    }
    
    /**
     * Returns the current HashMap of promos
     */
    public HashMap returnPromos()
    {
        return promos;
        // Return the promos HashMap
    }
}
