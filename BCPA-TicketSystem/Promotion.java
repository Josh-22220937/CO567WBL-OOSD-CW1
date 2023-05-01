/**
 * The Promotion class is responsible for holding the information about a specific promotion
 */
public class Promotion
{
    private Integer promoId;
    private Integer childDis;
    private Integer studentDis;
    private Integer adultDis;
    private Integer seniorDis;
    // Initalise the private fields for the Input class

    /**
     * Constructor for objects of class Promotion
     */
    public Promotion(Integer id, Integer chiDis, Integer stuDis, Integer aduDis, Integer senDis)
    {
        promoId = id;
        childDis = chiDis;
        studentDis = stuDis;
        adultDis = aduDis;
        seniorDis = senDis;
        // Give the private fields values that were passed to the object on creation
    }

    /**
     * Returns the promotion ID
     */
    public Integer getId()
    {
        return promoId;
        // Returns the promoId
    }
    
    /**
     * Returns the Child discount percentage
     */    
    public Integer getChildDiscount()
    {
        return childDis;
        // Returns the Child discount percentage
    }
    
    /**
     * Returns the Student discount percentage
     */
    public Integer getStudentDiscount()
    {
        return studentDis;
        // Returns the Student discount percentage
    }
    
    /**
     * Returns the Adult discount percentage
     */
    public Integer getAdultDiscount()
    {
        return adultDis;
        // Returns the Adult discount percentage
    }
    
    /**
     * Returns the Senior discount percentage
     */
    public Integer getSeniorDiscount()
    {
        return seniorDis;
        // Returns the Senior discount percentage
    }
}
