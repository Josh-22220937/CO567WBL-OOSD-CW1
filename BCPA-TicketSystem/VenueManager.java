import java.util.HashMap;
import java.util.Set;
/**
 *  The VenueManager class is responsible for managing shows, events and promotions  
 *  within the BCPA - Ticket System
 */
public class VenueManager
{
    private BCPADataBase dataBase;
    private CredentialsManager credentials;
    private Input input;
    private boolean run;
    private boolean loggedIn;
    private int rollingId;
    // Initalise the private fields for the VenueManager class

    /**
     * Constructor for objects of class VenueManager
     */
    public VenueManager(BCPADataBase obj)
    {
        this.dataBase = obj;
        credentials = new CredentialsManager();
        input = new Input();
        run = true;
        loggedIn = false;
        rollingId = 0;
        // Give the private fields values, with a database object passed into the VenueManager object when it was created
    }

    /**
     * Provide the user with a login interface, and then an interface to enter commands if the login is valid
     */
    public void userInterface()
    {   
        while (loggedIn == false) {
            //Keep asking the user for UN and PW until the correct details are entered
            System.out.print('\u000C');
            // System.out.print('\u000C'); clears the terminal window to keep the UI clear of clutter
            String userN = input.getInput("Enter Username");
            String passW = input.getInput("Enter Password");               
            loggedIn = credentials.verify(userN,passW);
            // The user entered details are passed to the credetials manager to verify them
            System.out.println("Credentials valid = : " + loggedIn);
        }
        while (run == true){
            commandPrinter();
            String command = input.getInput("\nEnter Action");
            // Keep asking the user for more actions to perform until they choose to exit
            System.out.print('\u000C');
            // System.out.print('\u000C'); clears the terminal window to keep the UI clear of clutter
            if(command.equals("DisplayAll")){
                displayAll();
            }
            else if(command.equals("AddShow")){
                addShow();
            }
            else if(command.equals("AddEvent")){
                addEvent();
            }
            else if(command.equals("RemoveShow")){
                removeShow();
            }
            else if(command.equals("RemoveEvent")){
                removeEvent();
            }
            else if(command.equals("AddShowToEvent")){
                addShowToEvent();
            }
            else if(command.equals("RemoveShowFromEvent")){
                removeShowFromEvent();
            }
            else if(command.equals("SetShowSeatLimit")){
                setShowSeatLimit();
            }
            else if(command.equals("AddPromo")){
                addPromo();
            }
            else if(command.equals("AddPromoToShow")){
                addPromoToShow();
            }
            else if(command.equals("RemovePromoFromShow")){
                removePromoFromShow();
            }
            else if(command.equals("RemovePromo")){
                removePromo();
            }
            else if(command.equals("Exit")){
                run = false;
            }
            // The above if - else if block checks for all the valid user inputs
        }
    }
    
    /**
     * Displays all the valid commands a user can enter and provides detail on how to use them
     */
    public void commandPrinter()
    {
        System.out.print("\n------------------");
        String c1 = ("\n    - DisplayAll - Shows the user all Events | Shows | Promos");
        String c2 = ("\n    - AddShow - Add a new Show to the system");
        String c3 = ("\n    - AddEvent - Add a new Event to the system");
        String c4 = ("\n    - RemoveShow - Remove a show From the system, including any Events the show is in");
        String c5 = ("\n    - RemoveEvent - Remove an Event from the system ");
        String c6 = ("\n    - AddShowToEvent - Add an existing Show to an Existing Event");
        String c7 = ("\n    - RemoveShowFromEvent - Remove a Show that was previously added to an Event");
        String c8 = ("\n    - SetShowSeatLimit - Change the maxmimum Seats a customer can buy for each Show");
        String c9 = ("\n    - AddPromo - Add a new Promotion structure to the system");
        String c10 = ("\n    - RemovePromo - Remove a Promotion structure from the system");
        String c11 = ("\n    - AddPromoToShow - Add an existing Promo to an existing Show");
        String c12 = ("\n    - RemovePromoFromShow - Remove a Promo that was previously added to a Show");
        String c13 = ("\n    - Exit - Exit the application");
        System.out.print("\nList of possible commands: "+ c1+c2+c3+c4+c5+c6+c7+c8+c9+c10+c11+c12+c13);
        System.out.print("\n------------------");
        
        // The above prints out all valid user commands with instructions on how to use them
    }
    
    /**
     * Display all information stored in the system 
     */
    public void displayAll()
    {
        HashMap events = dataBase.returnEvents();
        Set<Integer> eventKeys = events.keySet();
        // Gets all the events in the database and create a set of all the keys of the event objects 
        System.out.print("\n\n---------- Events ----------");      
        for(Integer eventKey : eventKeys) {
            Event event = (Event) events.get(eventKey);
            // Assigns a local variable to the instance of the Event object matching the key in the keyset
            System.out.print("\nEvent ID: " + event.getId() + " | Event Name: " + event.getName());
            // Uses the objects built in funcitons to return information about it
            HashMap eventShows = event.returnShows();
            Set<Integer> eventShowKeys = eventShows.keySet();
            // Gets all the shows that are a part of the event and create a set of all the keys of the show objects 
            for(Integer eventShowKey : eventShowKeys) {
                Show eventShow = (Show) eventShows.get(eventShowKey);
                // Assigns a local variable to the instance of the Show object matching the key in the keyset
                System.out.print("\n    - Show ID: " + eventShow.getId() + " | Show Name: " + eventShow.getName());
                // Uses the objects built in funcitons to return information about it
            }
        }
        
        HashMap shows = dataBase.returnShows();
        Set<Integer> showKeys = shows.keySet();
        // Gets all the shows  in the database and create a set of all the keys of the show objects
        System.out.print("\n---------- Shows ----------");
        for(Integer showKey : showKeys) {
            Show show = (Show) shows.get(showKey);
            // Assigns a local variable to the instance of the Show object matching the key in the keyset
            if (show.getPromoId() == null) {
                System.out.print("\nShow ID: " + show.getId() + " | Show Name: " + show.getName() + " | MaxSeats: " + show.getMaxSeats());
            } else {
                System.out.print("\nShow ID: " + show.getId() + " | Show Name: " + show.getName() + " | MaxSeats: " + show.getMaxSeats() + " | Promo ID = " + show.getPromoId());
            }
            // An if-else block is used to print one of two print blocks, one will print the shows promoId if it has one assigned to it, the other does not
        }
        
        
        HashMap promos = dataBase.returnPromos();
        Set<Integer> promoKeys = promos.keySet();
        // Gets all the promos in the database and create a set of all the keys of the promotion objects
        System.out.println("\n---------- Promos ----------");
        for(Integer promoKey : promoKeys) {
            Promotion promo = (Promotion) promos.get(promoKey);
            // Assigns a local variable to the instance of the show object matching the key in the keyset
            String chi = (" | Child Discount % : " +promo.getChildDiscount());
            String stu = (" | Student Discount % : " +promo.getStudentDiscount());
            String adu = (" | Adult Discount % : " +promo.getAdultDiscount());
            String sen = (" | Senior Discount % : " +promo.getSeniorDiscount());
            // Use the objects built in functions to return information about the object and store it into local variables
            System.out.print("\nPromotion ID: " + promo.getId() + chi + stu + adu + sen);
            // Print out all the information for that object
        }
    }
    
    /**
     * Add a show to the system
     */
    public void addShow()
    {
        String name = input.getInputLine("Enter Show name ");
        String description = input.getInputLine ("Enter Show description ");
        Integer date = input.getIntInput("Enter Show date(int) ");
        Integer time = input.getIntInput("Enter Show time(int) ");
        Integer maxSeats = input.getIntInput("Enter max seats per customer(int) ");
        // Obtain and store into local variables various bits of information about the show to be added

        Show show = new Show(rollingId, name, description, date, time, maxSeats);
        // Create a new Show object with the local information
        dataBase.addShow(rollingId, show);
        // Add the show to the database
        rollingId += 1;
        // Increment the unique ID this is used to make sure each event/show/promo object has a unique reference 
    }
    
    /**
     * Add an event to the system
     */
    public void addEvent()
    {
        String name = input.getInputLine("Enter Event name ");
        String description = input.getInputLine ("Enter Show description ");
        Integer date = input.getIntInput("Enter Event date(int) ");
        Integer time = input.getIntInput("Enter Event time(int) ");
        // Obtain and store into local variables various bits of information about the event to be added

        Event event = new Event(rollingId, name, description, date);
        // Create a new Event object with the local information
        dataBase.addEvent(rollingId, event);
        // Add the event to the database
        rollingId += 1;
        // Increment the unique ID this is used to make sure each event/show/promo object has a unique reference
    }
    
    /**
     * Add a show to a specific event
     */
    public void addShowToEvent()
    {
        Integer eventId = input.getIntInput("Enter EventID to target ");
        Integer showId = input.getIntInput("Enter ShowID to add ");
        // Obtain and store into local variables the information regarding the show and event its being added to
        
        dataBase.addShowToEvent(eventId,showId);
        // Add the show to the events internal database of shows
    }
    
    /**
     * Add a promotion to the system
     */
    public void addPromo()
    {
        Integer childDis = addPromoInput("Enter % discount for children ");
        Integer studentDis = addPromoInput("Enter % discount for students ");
        Integer adultDis = addPromoInput("Enter % discount for adults ");
        Integer seniorDis = addPromoInput("Enter % discount for seniors ");
        // Obtain and store into local variables the information regarding the promotion
        
        Promotion promo = new Promotion(rollingId,childDis, studentDis, adultDis, seniorDis);
        // Create a new Promotion object with the local information
        dataBase.addPromo(rollingId, promo);
        // Add the promotion to the database
        rollingId += 1;
        // Increment the unique ID this is used to make sure each event/show/promo object has a unique reference
    }
    
    /**
     * Obtain and value check the inputs for the promotion object creation
     */
    public Integer addPromoInput(String prompt)
    {
        Integer promoInput = input.getIntInput(prompt);
        if (promoInput > 100) {
            promoInput = 100;
        }
        // Checks if the input is above 100% which does not sense for a discount as it would imply the venue is paying the customer to watch a show
        // And sets it to a hard limit of 100% which means at most the show is free to watch
        if (promoInput < 0) {
            promoInput = 0;
        }
        // Checks if the input is below 0 which does not make sense for a discount as it would imply the customer is paying above the standard ticket price
        // And sets it to a hard limit of 0 which means at most the customer is paying full standard ticket price
        return promoInput;
        // Returns the promoInput to wherever the function was called from
    }
    
    /**
     * Add a promotion to a specific show
     */
    public void addPromoToShow()
    {
        Integer showId = input.getIntInput("Enter ShowID to target ");
        Integer promoId = input.getIntInput("Enter PromoID to add ");
        // Obtain and store into local variables the information regarding the promo and show its being added to
        
        dataBase.addPromoToShow(showId,promoId);
        // Add the PromoId to the shows internal promotion tracker
    }
    
    /**
     * Remove a show from the entire system
     */
    public void removeShow()
    {
        Integer showId = input.getIntInput("Enter ShowID to target ");
        // Obtain and store into local variable the ID of the show to remove from the system
        
        dataBase.removeShow(showId);
        // Remove the show from the database
    }
    
    /**
     * Remove an event from the entire system
     */
    public void removeEvent()
    {
        Integer eventId = input.getIntInput("Enter EventID to target ");
        // Obtain and store into local variable the ID of the event to remove from the system
        
        dataBase.removeEvent(eventId);
        // Remove the event from the database
    }
    
    /**
     * Remove a show from a specific event
     */
    public void removeShowFromEvent()
    {
        Integer eventId = input.getIntInput("Enter EventID to target ");
        Integer showId = input.getIntInput("Enter ShowID to remove ");
        // Obtain and store into local variables the ID of the show to remove and what event to remove it from
        
        dataBase.removeShow(eventId,showId);
        // Remove the show from the event 
    }
    
    /**
     * Remove a promotion from a specific show
     */
    public void removePromoFromShow()
    {
        Integer showId = input.getIntInput("Enter ShowID to target ");
        Integer promoId = input.getIntInput("Enter PromomID to target ");
        // Obtain and store into local variables the ID of the promo to remove and what show to remove it from
        
        dataBase.removePromo(showId,promoId);
        // Remove the promo from the show
    }

    /**
     * Remove a promotion from the entire system
     */
    public void removePromo()
    {
        Integer promoId = input.getIntInput("Enter PromoID to target ");
        // Obtain and store into local variable the ID of the promo to remove 
        
        dataBase.removePromo(promoId);
        // Remove the show from the database
    }
    
    /**
     * Sets a new seat purchase limit for a specific show
     */
    public void setShowSeatLimit()
    {
        Integer showId = input.getIntInput("Enter ShowID to target ");
        Integer seats = input.getIntInput("Enter seat limit ");
        // Obtain and store into local variables the ID of the show modify, and the new seat limit
        
        HashMap shows = dataBase.returnShows();
        Set<Integer> sKeys = shows.keySet();
        // Gets all the shows in the database and create a set of all the keys of the show objects 
        for(Integer sKey : sKeys) {
            if(sKey == showId) {
                // If the showId found in the keyset matches the user inputted ID
                Show show = (Show) shows.get(sKey);
                // Assign a local variable to the show object
                show.updateMaxSeats(seats);   
                // Use the shows built in function to modify the seat purchase limit
            }
        }
    }
}
