import java.util.HashMap;
/**
 * The Seat class is responsible for holding information on individual seats at each show
 */
public class Seat
{
    private Integer seatId;
    private Integer seatShowId;
    private String seatState;
    // Initalise the private fields for the Input class
    
    /**
     * Constructor for objects of class Seat
     */
    public Seat(Integer seatId, Integer showId)
    {
        this.seatId = seatId;
        seatShowId = showId;
        // Give the private fields values that were passed to the object on creation
    }
}
