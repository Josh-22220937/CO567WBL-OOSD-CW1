
/**
 *  The OnlineTicketSystem class is responsible for creating VenueManagers, Customers, Agents etc
 *  In this implementation it only creates a VenueManager as this is the reach of the systems scope
 */
public class OnlineTicketSystem
{
    private VenueManager vMan;
    private BCPADataBase dB;
    // Initalise the private fields for the OnlineTicketSystem class

    /**
     * Constructor for objects of class OnlineTicketSystem
     */
    public OnlineTicketSystem()
    {
        dB = new BCPADataBase();
        vMan = new VenueManager(dB);
        // Give the private fields values
        startVenueManager();
        // Calls a function to start the VenueManager
    }

    /**
     * Starts the interface for the VenueManager to interact with the system
     */
    public void startVenueManager()
    {
        System.out.println("Welcome to the OTS! Please Login as VenueManager");
        // Print welcome information
        vMan.userInterface();
        // Use the VenueManager built in function to start the interface
    }
}
